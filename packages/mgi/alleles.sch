; Process the MGI phenotypic alleles report, generating turtle.

(define (alfu)
  ;; (transduce-alleles "~/ncbuild/cache/mgi/MGI_PhenotypicAllele.rpt" "/tmp/allele.ttl")
  (transduce-alleles "a-sample.rpt" "/tmp/allele.ttl"))

(define (transduce-alleles ifile ofile)
  (call-with-input-file ifile
    (lambda (iport)
      (call-with-output-file ofile
	(lambda (oport)
	  (process-alleles-rows iport oport))))))

(define (process-alleles-rows iport oport)
  (with-turtle-output
   (list rdf-namespace
	 rdfs-namespace
	 foo-namespace
	 allele-namespace
	 mp-namespace)
   oport
   (lambda (emit)
     (let loop ((i 0))
       (let ((line (read-line iport)))
	 (if (eof-object? line)
	     i
	     (if (or (null? line)
		     (eq? (car line) #\#))
		 ;; Flush leading comment lines
		 (loop i)
		 (let ((fields (split line tab-char)))
		   (process-allele-row fields emit)
		   (loop (+ i 1))))))))))

(define foo-namespace
  (make-namespace "" "http://sw.neurocommons.org/mgi#"))

(define allele-namespace
  (make-namespace "a" "http://sw.neurocommons.org/mgi/allele/"))

(define mp-namespace
  (make-namespace "mp" "http://purl.org/obo/owl/MP#"))  ;mp:MP_0000454

(define (process-allele-row fields emit)
  (apply (lambda (mgi-id symbol
			 name
			 type
			 pmid
			 marker-id
			 marker-symbol
			 marker-refseq
			 ensembl
			 phenotypes
			 . maybe-synonyms)
	   (let ((synonyms (if (null? maybe-synonyms)
			       '()
			       (car maybe-synonyms))))
	     (emit `(,(get-allele-uri fields)
		     (,foo:hasMgiId ,(list->string mgi-id))
		     ,@(do-allele-field foo:hasAlleleSymbol symbol)
		     ,@(do-allele-field foo:hasAlleleName name)
		     ,@(do-allele-field foo:hasAlleleType type)
		     ;; 
		     ,@(if (null? pmid)
			   '()
			   `((,foo:hasAlleleArticle
			      ,(string->uri (string-append "http://purl.org/science/article/pmid/"
							   (list->string pmid))))))
		     ,@(do-allele-field foo:hasAlleleMarkerId marker-id)
		     ,@(do-allele-field foo:hasAlleleMarkerSymbol marker-symbol)
		     ,@(do-allele-field foo:hasAlleleMarkerRefseq marker-refseq)
		     ,@(do-allele-field foo:hasAlleleEnsembl ensembl)
		     ,@(do-allele-phenotypes phenotypes)
		     ,@(do-allele-synonyms synonyms)))))
	 fields))

(define (do-allele-field uri field)
  (if (null? field)
      '()
      (list (list uri (list->string field)))))

(define (do-allele-synonyms synonyms)
  (if (null? synonyms)
      '()
      (map (lambda (syn)
	     `(,foo:hasAlleleSynonym ,(list->string syn)))
	   (split synonyms #\|))))

(define (do-allele-phenotypes phenotypes)
  (if (null? phenotypes)
      '()
      (map (lambda (phe)
	     `(,foo:hasAllelePhenotype
	       ,(make-uri mp-namespace (string-append "MP_"
						      (list->string (cdddr phe))))))
	   (split phenotypes #\,))))

(define (get-allele-uri fields)
  (let ((mgi-id (car fields)))
    (make-uri allele-namespace (string-append "MGI_" (list->string (cddddr mgi-id))))))

(define foo:hasMgiId (make-uri foo-namespace "hasMgiId"))
(define foo:hasAlleleSymbol (make-uri foo-namespace "hasAlleleSymbol"))
(define foo:hasAlleleName (make-uri foo-namespace "hasAlleleName"))
(define foo:hasAlleleType (make-uri foo-namespace "hasAlleleType"))
(define foo:hasAllelePmid (make-uri foo-namespace "hasAllelePmid"))
(define foo:hasAlleleArticle (make-uri foo-namespace "hasAlleleArticle"))
(define foo:hasAlleleMarkerId (make-uri foo-namespace "hasAlleleMarkerId"))
(define foo:hasAlleleMarkerSymbol (make-uri foo-namespace "hasAlleleMarkerSymbol"))
(define foo:hasAlleleMarkerRefseq (make-uri foo-namespace "hasAlleleMarkerRefseq"))
(define foo:hasAlleleEnsembl (make-uri foo-namespace "hasAlleleEnsembl"))
(define foo:hasAlleleMps (make-uri foo-namespace "hasAlleleMps"))
(define foo:hasAlleleSynonyms (make-uri foo-namespace "hasAlleleSynonyms"))
(define foo:hasAllelePhenotype (make-uri foo-namespace "hasAllelePhenotype"))
(define foo:hasAlleleSynonym (make-uri foo-namespace "hasAlleleSynonym"))

(define all-allele-fields
  (list foo:hasMgiId
	foo:hasAlleleSymbol
	foo:hasAlleleName
	foo:hasAlleleType
	foo:hasAllelePmid
	foo:hasAlleleMarkerId
	foo:hasAlleleMarkerSymbol
	foo:hasAlleleMarkerRefseq
	foo:hasAlleleEnsembl
	foo:hasAlleleMps
	foo:hasAlleleSynonyms))

; -----------------------------------------------------------------------------

; read-line, split, and tab-char copied from gene-backbone.sch, gene-names, addgene.sch, etc.
;  ugh.

(define (read-line port)
  (let loop ()
    (let ((c (read-char port)))
      (if (eof-object? c)
          c
          (if (char=? c #\newline)
              '()
              (cons c (loop)))))))

(define (split l c)
  (call-with-values
      (lambda ()
	(let loop ((l l))
	  (cond ((null? l)
		 (values '() '()))
		((eq? (car l) c)
		 (call-with-values (lambda () (loop (cdr l)))
		   (lambda (cs parts)
		     (values '() (cons cs parts)))))
		(else
		 (call-with-values (lambda () (loop (cdr l)))
		   (lambda (cs parts)
		     (values (cons (car l) cs) parts)))))))
    cons))

(define tab-char (integer->char (- (char->integer #\newline) 1)))