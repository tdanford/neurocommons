
; ,translate =common ../common/scheme
; ,exec ,load =common/s48config.exec
; ,open uri rdf signals

(define foo-namespace
  (make-namespace "" "http://sw.neurocommons.org/mgi#"))

(define strain-namespace
  (make-namespace "s" "http://sw.neurocommons.org/mgi/strain/"))

(define has-label
  (make-uri rdfs-namespace "label"))

; (transduce-turtle "s-sample.rpt" "/tmp/s-sample.ttl")
; (transduce-turtle "~/ncbuild/cache/mgi/MGI_Strain.rpt" "~/Scratch/strain.ttl")

(define (transduce-turtle ifile ofile)
  (call-with-input-file ifile
    (lambda (iport)
      (call-with-output-file ofile
	(lambda (oport)
	  (process-rows-turtle iport oport))))))

(define (process-rows-turtle iport oport)
  (with-turtle-output
   (list rdf-namespace
	 rdfs-namespace
	 foo-namespace
	 strain-namespace)
   oport
   (lambda (emit)
     (set! *glosses* '())
     (set! *uid* 0)
     (set! uri-table (make-table))
     (set! intern-table (make-silly-table))
     (let loop ((i 0) (rows '()))
       (let ((line (read-line iport)))
	 (if (eof-object? line)
	     (begin (write `(,(length rows) ,(length *glosses*))) (newline)
		    (for-each (lambda (row)
				(apply process-row-turtle
				       emit
				       row))
			      (reverse rows))
		    (for-each (lambda (uid+gloss)
				(emit `(,(tree->term (car uid+gloss))
					,@(tree->properties (cdr uid+gloss)))))
			      *glosses*)
		    i)
	     (let* ((line (list->string line))
		    (fields (split-three-columns line)))
	       (if fields
		   (let* ((name (cadr fields))
			  (tree (parse-strain-name name)))
		     (if tree
			 (table-set! uri-table
				     (intern-tree tree)
				     (get-strain-uri (car fields)))
			 (begin
			   (display "!! Mismatch: ")
			   (write fields)
			   (newline)))
		     (loop (+ i 1)
			   (cons (list (car fields) ;MGI id
				       name
				       (caddr fields)) ;type
				 rows)))
		   (loop (- i 1) rows)))))))))

(define (process-row-turtle emit mgi-id name type)
  (emit `(,(get-strain-uri mgi-id)
	  (,foo:hasMgiId ,mgi-id)
	  (,foo:hasStrainName ,name)
	  (,foo:hasStrainType ,type))))

(define (get-strain-uri mgi-id)
  (make-uri strain-namespace
	    (string-append "MGI_"
			   (substring mgi-id 4 (string-length mgi-id)))))

;unused
(define (term->statement term)
  (if (and (pair? term)
	   (eq? (car term) '*blank*))
      `((*blank*) ,@(cdr term))
      (error "troublesome Turtle term" term)))

; Intern a tree

(define (silly-hash x)
  (cond ((pair? x)
	 (+ (silly-hash (car x))
	    1
	    (* (silly-hash (cdr x)) 23)))
	((string? x)
	 (string-hash x))
	(else
	 (default-hash-function x))))

; (define my-table-maker (make-table-maker compare-proc hash-proc))

(define make-silly-table (make-table-maker equal? silly-hash))

(define intern-table (make-silly-table))

(define uri-table (make-table))

(define *uid* 0)

(define *glosses* '())    ;List of (uid . gloss)

; Returns default node id as an integer

(define (intern-tree tree)
  (let ((gloss (map (lambda (sub)
		      (if (and (pair? sub)
			       (not (memq (car sub) '(cg heterozygous alleles))))
			  (intern-tree sub)
			  sub))
		    tree)))
    (or (table-ref intern-table gloss)
	(let ((new *uid*))
	  (set! *uid* (+ *uid* 1))
	  (table-set! intern-table gloss new)
	  (set! *glosses* (cons (cons new gloss) *glosses*))
	  new))))

; Returns a Turtle term (*blank* (verb object) (verb object) ...)

(define _ (make-namespace "_" "blanknode:"))

(define (tree->term tree)
  (if (integer? tree)
      (or (table-ref uri-table tree)
	  (make-uri _ (string-append "s" (number->string tree))))
      (cons '*blank* (tree->properties tree))))

(define (tree->properties tree)
  (case (car tree)
    ((custody)
     (custody->properties (cadr tree) (caddr tree) #f))

    ((custody+serial)
     (custody->properties (cadr tree) (caddr tree) (cadddr tree)))

    ((coisogenic)
     `((,has-type ,foo:Coisogenic)
       (,foo:hasBackground ,(tree->term (cadr tree)))
       ,@(alleles-in-rdf (caddr tree))))
      
    ((mixed-inbred)
     `((,has-type ,foo:MixedInbred)
       (,foo:hasBackground ,(tree->term (cadr tree)))
       ,@(alleles-in-rdf (caddr tree))))

    ((congenic)
     (congenic->properties foo:Congenic tree))

    ((inbred)
     `((,has-type ,foo:Inbred)
       (,foo:hasStrainName ,(cadr tree))))

    ;; B6.Cg/NTac-Foxn1<nu>
    ((cg)
     `((,has-type ,foo:CgLoser)))

    (else
     (error "untranslatable tree" tree))))

(define (custody->properties super lab-code serial-number)
  `((,has-type ,foo:InCustody)
    (,foo:isSubstrainOf ,(tree->term super))
    (,foo:hasLabCode ,lab-code)
    ,@(if serial-number
	  `((,foo:hasSerialNumber ,serial-number))
	  '())
    ;; Inherit all properties of the superstrain ???
    ))

(define (congenic->properties type tree)
  `((,has-type ,type)
    (,foo:hasHost ,(tree->term (cadr tree)))
    ,@(let ((strain (caddr tree)))
	(if strain
	    (if (equal? strain '(cg))
		`((,has-type ,foo:HasCgDonor))
		`((,foo:hasDonor ,(tree->term strain))))
	    `()))
    ,@(let ((strain (cadddr tree)))
	(if strain
	    (if (equal? strain '(cg))
		`((,has-type ,foo:HasCgHelper))
		`((,foo:hasHelper ,(tree->term strain))))
	    `()))
    ,@(alleles-in-rdf (car (cddddr tree)))))

; (alleles a ...) or (heterozygous (alleles a ...) (alleles a ...))

(define (alleles-in-rdf alleles)
  (if alleles
      (case (car alleles)
	((alleles) (alleles-list-in-rdf (cdr alleles)))
	((heterozygous) (append (alleles-in-rdf (cadr alleles))
				(alleles-in-rdf (caddr alleles))))
	(else (error "screwup" alleles)))
      '()))

(define (alleles-list-in-rdf alleles-list)
  (map (lambda (allele)
	 `(,foo:hasAllele ,allele))
       alleles-list))

(define foo:hasStrainType (make-uri foo-namespace "hasStrainType"))
(define foo:hasStrainName (make-uri foo-namespace "hasStrainName"))
(define foo:hasMgiId (make-uri foo-namespace "hasMgiId"))

(define foo:InCustody (make-uri foo-namespace "InCustody"))
(define foo:isSubstrainOf (make-uri foo-namespace "isSubstrainOf"))
(define foo:hasLabCode (make-uri foo-namespace "hasLabCode"))

(define foo:HasSerial (make-uri foo-namespace "HasSerial"))
(define foo:isSubstrainOf (make-uri foo-namespace "isSubstrainOf"))
(define foo:hasSerialNumber (make-uri foo-namespace "hasSerialNumber"))

(define foo:Coisogenic (make-uri foo-namespace "Coisogenic"))
(define foo:MixedInbred (make-uri foo-namespace "MixedInbred"))
(define foo:hasBackground (make-uri foo-namespace "hasBackground"))

(define foo:Congenic (make-uri foo-namespace "Congenic"))
(define foo:hasHost (make-uri foo-namespace "hasHost"))
(define foo:hasDonor (make-uri foo-namespace "hasDonor"))
(define foo:hasHelper (make-uri foo-namespace "hasHelper"))

(define foo:HasCgDonor (make-uri foo-namespace "HasCgDonor"))   ;FOO.Cg
(define foo:HasCgHelper (make-uri foo-namespace "HasCgHelper")) ;FOO(Cg)

(define foo:Inbred (make-uri foo-namespace "Inbred"))

(define foo:hasAllele (make-uri foo-namespace "hasAllele"))
(define foo:CgLoser (make-uri foo-namespace "CgLoser"))
