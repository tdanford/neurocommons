# Useful things to 'include' into a Makefile.

#  $^ = all prerequisites
#  $< = first prerequisite
#  $@ = file name of target

# Assumed from the including Makefile:
#   PACKAGE       [needs a longer name]
#   CACHE
#   WORK
#   BUNDLE
#   AUTHORITY_URI

ifndef PACKAGE
  $(error PACKAGE is undefined)
endif

export PATH:=$(COMMON)/bin:$(PATH)

notarget:
	$(error No target specified)

# Finish up the version currently in development, forcing advance to a new
# version as soon as anything changes.
# This should be done whenever the bundle gets copied or otherwise used
# outside its own development process.

snapshot: reference/bundle-signature.txt reference/Config.pl

reference/bundle-signature.txt: $(WORK)/bundle-signature.txt
	@mkdir -p reference
	cp -f $< $@

reference/Config.pl: $(BUNDLE)/Config.pl
	@mkdir -p reference
	cp -f $< $@

# Create the bundle.
# Ordinarily the files are listed explicitly, but sometimes (goa) this
# isn't possible.

BUNDLE_WILDCARD=$(shell find $(BUNDLE) -name *.gz -o -name *.rdf -o -name *.ttl  -o -name *.owl -o -name *.n3)

BUNDLE_FILES?=$(BUNDLE_WILDCARD)

bundle: $(BUNDLE_FILES) $(BUNDLE)/Config.pl

# Arrange things so that the *next* 'make' command will ensure
# that the cache contents are fresh.

validate: force-validate
	$(MAKE) prepare
	$(MAKE) cache

force-validate:
	$(COMMON)/bin/make-cache-old $(CACHE)

# Cache is preserved because in some sense it's part of the sources.
# Bundle is preserved because it's the final product, and 'make clean'
# isn't supposed to delete the produce.

clean:
	rm -rf $(WORK)

# Authority and version in ./Config-template.pl are
# null values.

# If after making bundle, 
# $(WORK)/bundle-signature.txt == reference/bundle-signature.txt, 
# then set $(BUNDLE)/Config.pl = reference/Config.pl.
#
# Otherwise, set version number = cached version number + 1,
#  	     	 authority = local authority,
# and store signature and new Config.pl for use next time.

AUTHORITY_URI?=$(shell cat $(AUTHORITY))

$(BUNDLE)/Config.pl: Config-template.pl $(WORK)/bundle-signature.txt
	$(COMMON)/bin/set-version reference/Config.pl \
				  reference/bundle-signature.txt \
				  $(WORK)/bundle-signature.txt \
				  Config-template.pl \
				  "$(AUTHORITY_URI)" >$@.new
	mv $@.new $@

# ????  md5sum /dev/null | grep --only-matching "[0-9a-f]*"

# N.b. BUNDLE_FILES is not in scope for dependencies, only for
# commands.  So we can't make this dependency-based.
# Just regenerate it every time, I guess.

bundle-signature: $(WORK)/bundle-signature.txt

# This rule doesn't work if common.mk is included at the top...

$(WORK)/bundle-signature.txt: Config-template.pl $(BUNDLE_FILES)
	@mkdir -p $(WORK)
	@echo Getting signature for bundle $(BUNDLE)
	@get-signature Config-template.pl $(BUNDLE) $(BUNDLE_FILES) >$@.new ; true
	@mv $@.new $@

# Foo

AUTO_CONFIG_TEMPLATE?=Config-template.pl

$(AUTO_CONFIG_TEMPLATE):
	(echo '{' ;\
	 echo '    class => "RDF Bundle",' ;\
	 echo '    name => "$(PACKAGE)",' ;\
	 echo '    version => 0,' ;\
	 echo '    authority => "uninitialized bundle authority",' ;\
	 echo '    no_need_to_clear_version => 0,' ;\
	 echo '    graph => "http://purl.org/science/graph/$(PACKAGE)",' ;\
	 echo '}') >$@


# 'Export' in our case means push out to a place from which rdfherd
# can pick it up.
# Assumes GNU 'cp'.

export: bundle snapshot
	@if [ "x$(EXPORT_ROOT)" = "x" ]; then echo "EXPORT_ROOT is not defined"; exit 1; fi
	@export-bundle $(EXPORT_ROOT)/$(PACKAGE) $(BUNDLE)/Config.pl $(BUNDLE_FILES)

# ??? Consider something similar to the following:

load: export
	rdfherd $(RDFHERD_ROOT) bundle_update $(PACKAGE)

.PHONY: bundle prebundle snapshot validate bundle-signature clean load
