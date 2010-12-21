package org.sc.grammars;

import java.util.ArrayList;
import java.util.Collection;

public class LexedLine { 
	
	public ArrayList<Lexed> lexed;
	public int depth;
	
	public LexedLine(int d, Collection<Lexed> ls) {   
		lexed = new ArrayList<Lexed>(ls);
		depth = d;
	}
	
	public String toString() { 
		return String.format("%d: %s", depth, lexed.toString());
	}
}