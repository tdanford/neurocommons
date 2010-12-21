package org.sc.grammars.clauses;

import org.sc.grammars.Lexed;
import org.sc.grammars.LexedTree;

public class ClauseParser {

	public Clause parseClause(LexedTree tree) { 
		Lexed first = tree.line.lexed.get(0);
		
		if(first.isDefine()) { 
			return new DefineClause(tree, this);
		
		} else if (first.isAssert()) { 
			return new AssertClause(tree, this);
		
		} else if (first.isTable()) { 
			return new TableClause(tree, this);
			
		} else if (first.isTriples()) { 
			return new TriplesClause(tree, this);
			
		} else if (first.isPrfx()) { 
			return new PrefixClause(tree, this);
		
		} else if (first.isImport()) { 
			return new ImportClause(tree, this);
		
		} else { 
			return new SubclassClause(tree, this);
		}
	}
}
