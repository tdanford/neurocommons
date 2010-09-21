# MGI = Mouse Genome Informatics

all: bundle

PACKAGE=mgi
-include config.mk
-include ../default.mk

MGI=ftp://ftp.informatics.jax.org/pub/reports
FETCH=../common/bin/ftp-validate
TAB2N3=$(COMMON)/python/tab2n3.py
S48FLAGS=-h 6000000

BUNDLE_FILES=$(BUNDLE)/parsed-strains.ttl \
	     $(BUNDLE)/allele.ttl \
	     $(BUNDLE)/BIB_PubMed.ttl \
	     $(BUNDLE)/imsr/1.ttl
bundle: $(BUNDLE_FILES)

$(BUNDLE)/parsed-strains.ttl: $(CACHE)/MGI_Strain.rpt parse2.sch genturtle.sch
	(echo ,set batch ;\
	 echo ,translate =common ../common/scheme ;\
	 echo ,exec ,load =common/s48config.exec ;\
	 echo ,open regexps uri rdf signals tables ;\
	 echo ,load parse2.sch genturtle.sch ;\
	 echo '(transduce-turtle "$<" "$@")' )\
	| scheme48 $(S48FLAGS)

$(BUNDLE)/allele.ttl: $(CACHE)/MGI_PhenotypicAllele.rpt alleles.sch
	(echo ,set batch ;\
	 echo ,translate =common ../common/scheme ;\
	 echo ,exec ,load =common/s48config.exec ;\
	 echo ,open regexps uri rdf signals ;\
	 echo ,load alleles.sch ;\
	 echo '(transduce-alleles "$<" "$@")' )\
	| scheme48 $(S48FLAGS)

# MGI_Strain.rpt =
# Official Strain Nomenclature (tab-delimited)
#  MGI Strain ID	Strain Name	Strain Type

$(BUNDLE)/MGI_Strain.ttl: $(CACHE)/MGI_Strain.rpt $(TAB2N3)
	(echo "@prefix : <http://sw.neurocommons.org/mgi#>" &&\
	 (echo "strain_id	strain_name	strain_type" &&\
	  cat $(CACHE)/MGI_Strain.rpt) |\
	 python $(TAB2N3)) >$@

# MGI_PhenotypicAllele.rpt = 
# List of All Mouse Phenotypic Alleles (tab-delimited)
#  	 MGI Allele Accession ID
#        Allele Symbol
#        Allele Name
#        Allele Type
#        PubMed ID for original reference
#        MGI Marker Accession ID Marker Symbol
#        Marker RefSeq ID
#        Marker Ensembl ID
#        High-level Mammalian Phenotype ID (comma-delimited)
#        Synonyms (pipe-delimited)

$(BUNDLE)/MGI_PhenotypicAllele.ttl: $(CACHE)/MGI_PhenotypicAllele.rpt \
	   			    $(TAB2N3)
	(echo "@prefix : <http://sw.neurocommons.org/mgi#>" &&\
	 (echo "hasMgiId	hasAlleleSymbol	hasAlleleName	hasAlleleType	hasAllelePmid	hasAlleleMarkerId	hasAlleleMarkerSymbol	hasAlleleMarkerRefseq	hasAlleleEnsembl	hasAlleleMps	hasAlleleSynonyms" &&\
	  grep -v "^#" $(CACHE)/MGI_PhenotypicAllele.rpt) |\
	 python $(TAB2N3)) >$@

# BIB_PubMed.rpt =
# Association of MGI Reference Accession IDs and PubMed IDs (tab-delimited)
#       MGI Reference Accession ID
#	PubMed ID
#	Alternative MGI Reference Accession ID (J:)

$(BUNDLE)/BIB_PubMed.ttl: $(CACHE)/BIB_PubMed.rpt $(TAB2N3)
	(echo "@prefix : <http://sw.neurocommons.org/mgi#>" &&\
	 (echo "hasMgiId	hasReferencePmid	hasReferenceAltId" &&\
	  cat $< ) |\
	 python $(TAB2N3)) >$@

# IMSR reports all have the same columns:
#   Strain/Stock
#   Site
#   State
#   Synonyms
#   Type
#   Chr
#   Mutation
#   Allele Symbol
#   Allele Name
#   Gene Name
# N.b. #< = first prerequisite only

$(BUNDLE)/imsr/%.ttl: $(CACHE)/imsr/%.tab $(TAB2N3)
	(echo "@prefix : <http://sw.neurocommons.org/mgi#>" &&\
	 (echo "hasStrainOrStock	hasSite	hasState	hasSynonyms	hasType	hasChr	hasMutation	hasAlleleSymbol	hasAlleleName	hasGeneName" &&\
	  tail +4 $< ) |\
	 python $(TAB2N3)) >$@
	

cache: $(CACHE)/MGI_Strain.rpt \
       $(CACHE)/BIB_PubMed.rpt \
       $(CACHE)/MGI_PhenotypicAllele.rpt \
       $(CACHE)/MGI_QTLAllele.rpt \
       $(CACHE)/MGI_PhenoGenoMP.rpt \
       $(CACHE)/imsr/12.tab
#       $(CACHE)/tab2n3.py

$(CACHE)/tab2n3.py:
	../common/bin/http-validate \
	    "http://www.w3.org/2000/10/swap/tab2n3.py" $@

$(CACHE)/%.rpt:
	$(FETCH) $(MGI)/$(@F) $@

IMSR=http://www.findmice.org/fetch?page=imsrReport&report=repository&print=data&site=

$(CACHE)/imsr/1.tab:
	../common/bin/http-validate "$(IMSR)1" $@

# Also KOMP and EUCOMM alleles

prepare:
	mkdir -p $(CACHE) $(CACHE)/imsr $(WORK) $(BUNDLE) $(BUNDLE)/imsr

include $(COMMON)/common.mk
