package org.sc.grammars;

import org.antlr.runtime.tree.CommonTree;

import antlr.Token;

public class Lexed {

	public int typeIdx;
	public String type;
	public String value;
	public int line;
	public int start, end;
	
	public Lexed(CommonTree t) { 
		typeIdx = t.getType();
		if(typeIdx >= 0 && typeIdx < OntoParser.tokenNames.length) { 
			type = OntoParser.tokenNames[typeIdx];
		} else { 
			type = "EOF";
		}
		line = t.getLine();
		start = t.getTokenStartIndex();
		end = t.getTokenStopIndex();
		value = t.getText();
	}
	
	public boolean isDefine() { return type.equals("DEFINE"); }
	public boolean isQuoted() { return type.equals("QUOTED"); }
	public boolean isLiteral() { return typeIdx == OntoParser.LITERAL; }
	
	public String toString() { 
		return !isNewline() && !isTab() ? String.format("%s (%s)", value, type) : 
				String.format("(%s)", type);
	}
	
	public boolean isPrefix() { 
		return isTab() || isEquals() || isPlus();
	}
	
	public boolean canSkip() { return isNewline() || isWhitespace(); }
	
	public boolean isTab() { return type.equals("TAB"); }
	public boolean isEquals() { return type.equals("EQUALS"); }
	public boolean isPlus() { return type.equals("PLUS"); }
	public boolean isWhitespace() { return type.equals("WS"); }
	public boolean isNewline() { return typeIdx == OntoParser.NEWLINE; }
	
	public boolean isEOF() { return type.equals("EOF"); }
	public boolean isAssert() { return typeIdx == OntoParser.ASSERT; }
	public boolean isVar() { return typeIdx == OntoParser.VAR; }
	public boolean isPrefixed() { return typeIdx == OntoParser.PREFIXED; }
	public boolean isTable() { return typeIdx == OntoParser.TABLE; }

	public boolean isSome() { return typeIdx == OntoParser.SOME; }
	public boolean isOnly() { return typeIdx == OntoParser.ONLY; }
	public boolean isValue() { return typeIdx == OntoParser.VALUE; }

	public boolean isTriples() { return typeIdx == OntoParser.TRIPLES; }
	public boolean isImport() { return typeIdx == OntoParser.IMPORT; }
	public boolean isPrfx() { return typeIdx == OntoParser.PRFX; }
}
