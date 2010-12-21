package org.sc.grammars;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LexedTree { 
	public LexedLine line;
	public ArrayList<LexedTree> children;
	
	public LexedTree(LexedLine ll) { 
		line = ll;
		children = new ArrayList<LexedTree>();
	}
	
	public String toString() { 
		return toString(0);
	}
	
	public String toString(int depth) { 
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < depth; i++) { sb.append("  "); } 
		sb.append(line.toString());
		sb.append("\n");
		for(LexedTree child : children) { 
			sb.append(child.toString(depth+1));
		}
		return sb.toString();
	}
}

class Treemaker implements Iterator<LexedTree> { 
	
	private PushbackIterator<LexedLine> lines;
	
	private LexedTree nextTree;
	
	public Treemaker(Iterator<LexedLine> litr) { 
		lines = new PushbackWrapper<LexedLine>(litr);
		findNextTree();
	}
	
	public void remove() { throw new UnsupportedOperationException(); }
	
	private void findNextTree() { 
		nextTree = null;
		if(lines.hasNext()) { 
			nextTree = readTree(0);
		}
	}
	
	private LexedTree readTree(int depth) {
		if(!lines.hasNext()) { 
			return null;
		}
		
		LexedLine next = lines.next();
		if(next.depth > depth) { 
			throw new IllegalStateException(
					String.format("Illegal line depth (%s)", next.toString()));
			
		} else if (next.depth < depth) { 
			lines.pushback(next);
			return null;
		}
		
		LexedTree building = new LexedTree(next);
		LexedTree subtree = null;
		while((subtree = readTree(depth+1)) != null) { 
			building.children.add(subtree);
		}

		return building;
	}

	public boolean hasNext() {
		return nextTree != null;
	}

	public LexedTree next() {
		LexedTree t = nextTree;
		findNextTree();
		return t;
	}
}

class Linebreaker implements Iterator<LexedLine> { 
	
	private PushbackIterator<Lexed> itr;
	
	private LexedLine nextLine;
	
	public Linebreaker(PushbackIterator<Lexed> i) { 
		itr = i;
		findNextLine();
	}
	
	private void findNextLine() { 
		nextLine = null;
		ArrayList<Lexed> lex = new ArrayList<Lexed>();

		do { 
			int tabCount = 0;
			Lexed next = null;

			// At this point, we're guaranteed that (if there's another token in the stream),
			// it's not a newline, but the start of a "real" line.
			while(itr.hasNext() && !(next = itr.next()).isEOF() && !next.isNewline()) {

				if(next.isTab() && lex.isEmpty()) { 
					tabCount += 1;

				} else { 
					lex.add(next);
				}
			}

			if(!lex.isEmpty()) { 
				nextLine = new LexedLine(tabCount, lex);
			}
		} while(lex.isEmpty() && itr.hasNext());
	}

	public void remove() { throw new UnsupportedOperationException(); }

	public boolean hasNext() {
		return nextLine != null;
	}

	public LexedLine next() {
		LexedLine ll = nextLine; 
		findNextLine();
		return ll;
	}
}
