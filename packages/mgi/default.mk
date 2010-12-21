# In order to use the package-building framework (the Makefiles in all
# the subdirectories of packages/), a developer will need to create a
# private 'default.mk' file in his/her own packages/ svn working copy
# that defines variables appropriate to his/her local configuration.
# (That is, 'default.mk' is not under source control, but needs to be
# there.)  The private default.mk file will be 'included' by every
# Makefile in every subdirectory of packages/.

# Start by copying this file, and modify it as documented below.

# Any variable defined herein can be overridden on a per-bundle basis
# by redefining it in 'config.mk' in the bundle's source directory
# packages/whatever/.

# See also http://neurocommons.org/page/Package_Makefile_conventions

# AUTHORITY_URI is used to distinguish bundle versions made by
# different developers but that happen to have the same version number
# (e.g. Jonathan's version 7 of bundle B from Dan's version 7 of
# bundle B).  This design is half-baked and I have my doubts about it.
# In any case, set it to some URI that is unique to the development
# process in which *you* generate new version numbers.  You could use
# a tag: URI ( http://www.ietf.org/rfc/rfc4151.txt ) or a mailto: URI.

AUTHORITY_URI?="mailto:tdanford@gmail.com"

# The following should be the absolute pathname to the packages/common
# directory.
#
# COMMON?=/home/username/nc/trunk/packages/common

COMMON?=/Users/tdanford/Documents/eclipse-workspace/nc_packages/common

# If you will be doing 'make export' this should be set to the place
# where 'rdfherd bundle_update' will be looking for bundles.
#
# EXPORT_ROOT?=/raid/not_backed_up/development-bundles

EXPORT_ROOT?=$(error "rdfherd bundles directory")

# BUILD_ROOT is not used in Makefile, but can be defined locally to
# this file in the case that your build, work, and/or cache
# directories are all in the same place.
#
# BUILD_ROOT=/raid/username
#
# BUILD_ROOT?=$(error "If desired, define this to be the common parent of build, work, cache")

# The default location of the primary source cache directory for the
# bundle being built.
#
# CACHE?=$(BUILD_ROOT)/cache/$(PACKAGE)

CACHE?=/Users/tdanford/Documents/Programming/github/neurocommons/packages/mgi/cache

# The default location of the intermediate file directory.
#
# WORK?=$(BUILD_ROOT)/work/$(PACKAGE)

WORK?=/Users/tdanford/Documents/Programming/github/neurocommons/packages/mgi/work
 
# The default location of the bundle target directory, where the new
# version of the bundle will be constructed.
#
# BUNDLE?=$(BUILD_ROOT)/bundles/$(PACKAGE)

BUNDLE?=/Users/tdanford/Documents/Programming/github/neurocommons/packages/mgi/bundle

# -- Cross-references needed for particular packages --

# These things don't really belong here, but I haven't figured out how
# to organize them properly.  Uncomment as needed.

# Needed for using the Medline cache.  You may choose to use another
# developer's cache, to avoid having to fetch and store multiple
# copies of Medline, which is pretty darn big.
#
# MEDLINE_CACHE?=$(BUILD_ROOT)/cache/medline

# Needed for using the MeSH digest (e.g. medline/subject-headings)
#
# MESH_STUFF?=$(BUILD_ROOT)/work/mesh
# MESH_DIGEST?=$(MESH_STUFF)/mesh-digest.lisp

# Needed for using the MeSH cache
#
# MESH_CACHE?=$(BUILD_ROOT)/cache/mesh


# -- Things needed for particular packages --

# Not sure why these aren't in the various Makefiles.

# Needed for caching Medline.  NLM does not publicize these locations,
# although there's no reason to keep them secret since access is limited 
# by client IP address anyhow.
# MEDLINE_FTP_1?="ftp://ftp.nlm.nih.gov/nlmdata/.medleasebaseline/gz"
# MEDLINE_FTP_2?="ftp://ftp.nlm.nih.gov/nlmdata/.medlease/gz"

# Needed for caching MeSH
# MESH_YEAR?=$(shell date | grep -o -E "[0-9]{4}")
# MESH_FTP?=ftp://nlmpubs.nlm.nih.gov/online/mesh/.xmlmesh

# For mesh-skos.
# SWI_PROLOG?=/usr/local/bin/pl
#  or
# SWI_PROLOG?=pl

