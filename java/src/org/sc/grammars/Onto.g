grammar Onto;

options { 
    output=AST; 
}

tokens { 
	DEFINE = 'define' ; 
	ASSERT = 'assert' ; 
	TRIPLES = 'triples' ; 
	SOME = 'some' ; 
	ONLY = 'only' ; 
	VALUE = 'value' ; 
	WHERE = 'where' ; 
	TABLE = 'table' ;
    ALIAS = 'alias' ; 
    PRFX = 'prefix' ; 
	IMPORT = 'import' ; 
    COLON = ':' ; 
	TAB = '\t' ; 
	QUOTE = '\'' ; 
	DOLLAR = '$' ; 
	AT = '@' ;
}

@header { 
package org.sc.grammars;
}

@lexer::header { 
package org.sc.grammars;
}

template : eol? statement* EOF ; 

statement 
    : headline eol triples? (TAB+ innerline eol)* 
    | singleline eol 
    ;

triples 
	: TAB TRIPLES eol (TAB TAB triple_entry eol)+ ; 
	
triple_entry : name WS name WS name ; 

singleline 
    : importline 
    | prefixline
    ;

headline 
    : defineline 
    | assertline 
    | tableline 
    ; 

innerline
    : defineline
    | assertline
    | classline
    | restrictline
    ;

defineline : DEFINE WS name ; 
assertline : ASSERT WS name ; 
tableline : TABLE WS LITERAL (WS (VAR | QUOTED))* ; 
importline : IMPORT WS LITERAL WS QUOTED ; 
prefixline : PRFX WS LITERAL WS QUOTED ; 

classline : name ; 
restrictline : name WS (SOME | ONLY | VALUE) (WS name)? ; 

name : PREFIXED | LITERAL | QUOTED | VAR ; 

eol : (WS? NEWLINE)+ ;

PREFIXED : PREFIX LITERAL ; 
PREFIX : LETTER (LETTER | DIGIT)* COLON ; 

LITERAL : (LETTER | '_' | '-' | '.' )+ ; 

QUOTED : QUOTE (~QUOTE)* QUOTE ; 

VAR : DOLLAR LETTER (LETTER | DIGIT)*
    | AT LETTER (LETTER | DIGIT)*
    ;

fragment DIGIT : '0'..'9' ; 
fragment LETTER : 'a'..'z' | 'A'..'Z' ; 

NEWLINE : '\n' ; 
WS : (' ' | '\u000C' )+ { };

