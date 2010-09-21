; Process the MGI strains file, generating... tab-delimited? RDF?

; See http://s48.org/1.8/manual/manual-Z-H-6.html#node_sec_5.20
; ,open regexps

; TBD:  B6Ei should parse like B6/Ei, C3Sn like C3/Sn

; (current-input-port)

(define (transduce ifile ofile)
  (call-with-input-file ifile
    (lambda (iport)
      (call-with-output-file ofile
	(lambda (oport)
	  (process-rows iport oport))))))

(define (dofu)
  (transduce "~/ncbuild/cache/mgi/MGI_Strain.rpt" "/tmp/parse2.txt"))

(define (doit)
  (process-rows (current-input-port) (current-output-port)))

(define (process-strain-file filename)
  (call-with-input-file filename
    (lambda (iport)
      (process-rows iport (current-output-port)))))

(define (process-rows iport oport)
  (let loop ((i 0))
    (let ((line (read-line iport)))
      (if (eof-object? line)
	  i
	  (let* ((line (list->string line))
		 (fields (split-three-columns line)))
	    (process-row fields oport)
	    (loop (+ i 1)))))))

(define tab-char (integer->char (- (char->integer #\newline) 1)))

(define split-three-columns
  (let* ((field (repeat (union printing (set " "))))
	 (tab (text (string tab-char)))
	 (pat (sequence (string-start)
			 (submatch 'a field)
			 tab
			 (submatch 'b field)
			 tab
			 (submatch 'c field)
			 (string-end))))
    (lambda (line)
      (let ((m (match pat line)))
	(if m
	    (let ((subs (match-submatches m)))
	      (list (get-submatch subs 'a line)
		    (get-submatch subs 'b line)
		    (get-submatch subs 'c line)))
	    #f)))))

(define (get-submatch submatches which subject)
  (let ((foo (assq which submatches)))
    (if foo
	(substring subject
		   (match-start (cdr foo))
		   (match-end (cdr foo)))
	#f)))

(define (read-line port)
  (let loop ()
    (let ((c (read-char port)))
      (if (eof-object? c)
          c
          (if (char=? c #\newline)
              '()
              (cons c (loop)))))))

(define serial-number
  (one-of (sequence (set "123456789")
		    (repeat numeric))
	  (sequence (text "(")
		    (repeat (union upper-case numeric))
		    (text ")"))
	  lower-case))    ;e.g. C3.K-H1<b>/nSnJ

(define lab-code
  (sequence upper-case (repeat lower-case)))

(define lab-codes
  (sequence (repeat 0 1 serial-number)
	    lab-code
	    (repeat lab-code)))

; ------------------------------------------------------------
; Exploration 3

; Perform the match, obtain the matched segments, and put the results
; into columns.

(define (process-row fields oport)
  (let ((tree (parse-strain-name (cadr fields))))
    (if tree
	(begin 
	  (display (car fields) oport)
	  (display tab-char oport)
	  (display (caddr fields) oport)
	  (display tab-char oport)
	  (write tree oport)
	  (newline oport))
	(begin
	  (display "!! Mismatch: ")
	  (write fields)
	  (newline)))))

(define (parse-strain-name strain-name)
  (let* ((strain-name (preprocess-strain-name strain-name))
	 (l (match lab-codes-detector strain-name)))
    (if l
	(let* ((subs (match-submatches l))
	       (subject (get-submatch subs 'subject strain-name))
	       (codes (get-submatch subs 'codes strain-name))
	       (tree (parse-strain-name-2 subject)))
	  (if tree
	      (parse-codes codes tree)
	      #f))
	(parse-strain-name-2 strain-name))))

(define Chr-detector (text "Chr "))

(define (preprocess-strain-name strain-name)
  (let ((m (match Chr-detector strain-name)))
    (if m
	(preprocess-strain-name
	 (string-append (substring strain-name
				   0
				   (match-start m))
			"Chr="
			(substring strain-name
				   (match-end m)
				   (string-length strain-name))))
	strain-name)))

(define postprocess-allele
  (let ((Chr=-detector (text "Chr=")))
    (lambda (subject)
      (let ((m (match Chr=-detector subject)))
	(if m
	    (postprocess-allele
	     (string-append (substring subject
				       0
				       (match-start m))
			    "Chr "
			    (substring subject
				       (match-end m)
				       (string-length subject))))
	    subject)))))

(define (parse-strain-name-2 subject)
  (let ((m (match strain-parser subject)))
    (if m
	(let* ((subs (match-submatches m))
	       (host (get-submatch subs 'host subject))
	       (donor (get-submatch subs 'donor subject))
	       (helper (get-submatch subs 'helper subject))
	       (delim (get-submatch subs 'delimiter subject))
	       (alleles (get-submatch subs 'alleles subject)))
	  (if (or donor helper delim alleles)
	      (synthesize (sub-parse-strain-name host)
			  (sub-parse-strain-name donor)
			  (sub-parse-strain-name helper)
			  delim
			  (sub-parse-alleles alleles))
	      (inbred host)))
	;; Syntax not understood
	#f)))

(define (synthesize host donor helper delim alleles)
  (cond ((equal? delim " ")
	 (if (or donor helper)
	     #f  ; (strange1 host donor helper alleles)
	     (mixed-inbred host alleles)))
	((or donor helper)
	 (congenic host donor helper alleles))
	(else
	 (coisogenic host alleles))))

(define (sub-parse-strain-name strain)
  (if strain
      (let ((probe (table-ref abbreviations strain)))
	(if probe
	    (inbred probe)
	    (parse-strain-name strain)))
      #f))

; Split up the lab codes

(define (parse-codes codes tree)
  (let ((m (match code-list-regexp codes)))
    (if m
	(let* ((subs (match-submatches m))
	       (sn (get-submatch subs 'sn codes)))
	  (let ((yow (lambda (letter tree)
		       (let ((code (get-submatch subs letter codes)))
			 (if code
			     (custody tree code)
			     tree)))))
	    (yow 'e
		 (yow 'd
		      (yow 'c
			   (yow 'b
				(apply-serial-number (yow 'a tree)
						     sn))))))))))

(define (optional x)
  (repeat 0 1 x))

; Remember:
;(define lab-codes
;  (sequence (repeat 0 1 serial-number)
;	    lab-code
;	    (repeat lab-code)))

(define code-list-regexp
  (sequence (optional (submatch 'sn serial-number))
	    (submatch 'a lab-code)
	    (optional
	     (sequence (submatch 'b lab-code)
		       (optional
			(sequence (submatch 'c lab-code)
				  (optional
				   (sequence (submatch 'd lab-code)
					     (optional
					      (submatch 'e lab-code))))))))))
		       

; Split alleles into numerator and denominator,
; then each of those into factors.

(define (sub-parse-alleles alleles)
  (if alleles
      (let ((m (match alleles-recognizer alleles)))
	(if m
	    (let* ((subs (match-submatches m))
		   (up   (get-submatch subs 'up alleles))
		   (down (get-submatch subs 'down alleles)))
	      (if down
		  (heterozygous (sub-parse-allele-list up)
				(sub-parse-allele-list down))
		  (sub-parse-allele-list up)))
	    (begin (display "!! Allele problem: ")
		   (write alleles)
		   (newline))))
      #f))

(define (sub-parse-allele-list z)
  (let ((m (match allele-list-regexp z)))
    (if m 
	(let* ((subs (match-submatches m))
	       (a (get-submatch subs 'a z))
	       (b (get-submatch subs 'b z))
	       (c (get-submatch subs 'c z))
	       (d (get-submatch subs 'd z))
	       (e (get-submatch subs 'e z)))
	  (alleles-sequence
	   (append (if a (list a) '())
		   (if b (list b) '())
		   (if c (list c) '())
		   (if d (list d) '())
		   (if e (list e) '()))))
	(begin (display "!! Allele difficulty: ")
	       (write z
	       (newline))))))

(define allele-regexp 
  (let* ((general (union (union alphabetic numeric)
			 (set ":;.#?,*+=-")))
	 (thing (one-of (text "Chr ")
			(sequence (text "<")
				  (repeat (union general (set "()/- ")))
				  (text ">"))
			(sequence (text "(")
				  (repeat (union general (set "<>/- ")))
				  (text ")"))
			general)))
    (sequence thing
	      (repeat thing))))

(define alleles-recognizer
  (let ((allele-sequence (sequence allele-regexp
				   (repeat (sequence (text " ")
						     allele-regexp)))))
    (sequence (submatch 'up allele-sequence)
	      (optional
	       (sequence (text "/")
			 (submatch 'down allele-sequence))))))

(define allele-list-regexp
  (sequence (submatch 'a allele-regexp)
	    (optional
	     (sequence (text " ")
		       (submatch 'b allele-regexp)
		       (optional
			(sequence (text " ")
				  (submatch 'c allele-regexp)
				  (optional
				   (sequence (text " ")
					     (submatch 'd allele-regexp)
					     (optional
					      (sequence (text " ")
							(submatch 'e allele-regexp)))))))))))

; Operators

(define (custody strain lab-code)
  (if lab-code
      `(custody ,strain ,lab-code)
      strain))

(define (apply-serial-number strain sn)
  (if sn
      (if (and (pair? strain)
	       (eq? (car strain) 'custody))
	  `(custody+serial ,(cadr strain)
			   ,(caddr strain)  ;lab code
			   ,sn)
	  (error "bogus serial number" strain sn))
      strain))

(define (strange1 host donor helper alleles)
  `(strange1 ,host ,donor ,helper ,alleles))

(define (mixed-inbred host alleles)
  `(mixed-inbred ,host ,alleles))

(define (congenic host donor helper alleles)
  `(congenic ,host ,donor ,helper ,alleles))

(define (coisogenic host alleles)
  `(coisogenic ,host ,alleles))

(define (heterozygous alleles1 alleles2)
  `(heterozygous ,alleles1 ,alleles2))

(define (alleles-sequence alleles-strings)
  `(alleles ,@(map postprocess-allele alleles-strings)))

; Base case

(define (inbred host)
  (if (equal? host "Cg")
      `(cg)
      `(inbred ,host)))

(define abbreviations
  (let ((abbreviations (make-string-table)))
    (for-each (lambda (x)
		(table-set! abbreviations (car x) (cadr x)))
	      '(("AK" "AKR")  ;strains
		("B" "C57BL")
		("B6" "C57BL/6") ;strains
		("B10" "C57BL/10") ;strains
		("BR" "C57BR/CD")
		("C" "BALB/c") ;strains
		("C3" "C3H") ;strains
		("CB" "CBA")
		("D1" "DBA/1") ;strains
		("D2" "DBA/2") ;strains
		("HR" "HRS/J")  ;     !!! not the same
		("L" "C57L/J")  ;    !!! not the same
		("R3" "RIIIS/J")
		("J" "SJL")
		("SW" "SWR")))
    abbreviations))    ;     !!! not the same


; For the following, the lab code does not need a / separator.

(define grandfathered (one-of (text "C57BL/6")
			      (text "C57BL/10")
			      (text "C57BR/a")
			      (text "C57BR/c")
			      (text "C57BR/cd")
			      (text "BALB/c")
			      (text "DBA/1")
			      (text "DBA/2")
			      (text "ST/a")
			      (text "ST/b")
			      (text "BN/a")
			      (text "BN/b")))

; Match whole string (from which lab code has been removed),
; separating the host.donor(other) part from the alleles part

; host [ [ . donor ] [ ( helper ) ] [ - alleles ] ]

(define strain-parser
  (let* ((general (union (union alphabetic numeric) (set ":;")))
	 (participant
	  (one-of (sequence grandfathered
			    (optional lab-codes))
		  (sequence general
			    (repeat general)
			    (optional (sequence (text "/")
						lab-codes))))))
    (sequence (string-start)
	      (submatch 'host participant)
	      (optional (sequence (text ".")
				  (submatch 'donor
					    participant)))
	      (optional (sequence (text "(")
				  (submatch 'helper participant)
				  (text ")")))
	      (optional (sequence
			  (submatch 'delimiter (set "- "))
			  (submatch 'alleles
				    (repeat
				     (union general
					    (set "/.#?,*+= <>()- "))))))
	      (string-end))))

(define lab-codes-detector
  (one-of (sequence (string-start)
		    (submatch 'subject grandfathered)
		    (submatch 'codes lab-codes)
		    (string-end))
	  (sequence (string-start)
		    (submatch 'subject (repeat printing))
		    (text "/")
		    (submatch 'codes lab-codes)
		    (string-end))))

; ------------------------------------------------------------
; Sanity checker... exceptions listed in grammar.txt
; Matched part includes lab code

(define alleles-part
  (let* ((general (union (union alphabetic numeric)
			 (set ":;#?,*+= /")))
	 (parenthesized (sequence (text "(")
				  (repeat (union general (set "<>-. ")))
				  (text ")")))
	 (bracketed (sequence (text "<")
			      (repeat (union general (set "()-. ")))
			      (text ">"))))
    (repeat (one-of (text "H2-")
		    (text "Igh-")
		    (text "Hbb-")
		    (text "Tcra-")
		    (text "-rs")
		    (text "tf-")
		    (text "Igk-")
		    (sequence numeric (text "-") numeric)
		    (sequence numeric (text ".") numeric)
		    parenthesized
		    bracketed
		    general))))

; ------------------------------------------------------------
; Exploration 2

; careful with 129, 615, etc


(define master-regexp-1
  (repeat (union printing whitespace)))

(define master-regexp
  (let* ((mgi-id
	  (submatch 'mgi-id
		    (sequence (text "MGI:") (repeat numeric))))
	 (kind (sequence (repeat (union alphabetic (set " ")))
			 (repeat 0 1 (sequence (text "(")
					       (repeat alphabetic)
					       (text ")")))))
	 (column-separator (text (string tab-char)))
	 (lab-suffix (sequence (text "/") lab-codes))
	 (anyc (union alphabetic
		      (union numeric
			     (set "-+=.#*;,?: "))))
	 (token1 (one-of anyc lab-suffix))
	 (rounded (sequence (text "(")
			    (repeat token1)
			    (text ")")))
	 (allele (repeat (one-of token1
				 rounded)))
	 (gene (sequence upper-case
			 (repeat (union lower-case numeric))))
	 (angled (sequence (text "<")
			   allele
			   (text ">")))
	 (token (one-of token1
			(text "+/+")
			(text "/+ +")
			(sequence gene angled)
			rounded))
	 (strain-name (sequence (repeat 0 1 grandfathered)
				(repeat token)
				(one-of lab-suffix (text ""))))
	 )
    (submatch 'all (sequence (string-start)
			     mgi-id
			     column-separator
			     strain-name
			     column-separator
			     kind
			     (string-end)))))

; Line is a list of characters

(define superscript-detector
  (let* ((truff (repeat (union (union alphabetic numeric)
			       (set "-.,;:#?()/+=* "))))
	 (superscript truff)
	 (super (lambda (key)
		  (sequence truff
			    (text "<")
			    (submatch key superscript)
			    (text ">"))))
	 (grumble (lambda (key re)
		    (sequence (super key)
			      (repeat 0 1 re)))))
    (sequence (string-start)
	      (grumble 'a (grumble 'b (super 'c)))
	      truff
	      (string-end))))

(define (process-row-2 line oport)
  (let* ((fields (split-three-columns line))
	 (strain-name (cadr fields)))
    (if (any-match? Chr-detector strain-name)
	(begin (write `(chr ,strain-name)) (newline))
	(let ((m (match superscript-detector strain-name)))
	  (if m
	      (begin (for-each (lambda (submatch)
				 (process-superscript (substring strain-name
								 (match-start (cdr submatch))
								 (match-end (cdr submatch)))
						      oport))
			       (match-submatches m))
		     'win)
	      ;; (begin (write `(no-matches ,strain-name)) (newline))
	      'no-match
	      )))))

(define (process-superscript sup oport)
  (display sup oport)
  (newline oport))

; ------------------------------------------------------------
; Exploration 1

(define (process-row-1 line oport)
  (let ((the-match (match master-regexp line)))
    (if the-match
	'ok
	;; (match-submatches ...)
	(begin (display line oport)
	       (newline oport)))))
