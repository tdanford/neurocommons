package org.sc.grammars.clauses;

import java.util.*;

import org.sc.grammars.Lexed;
import org.sc.grammars.LexedTree;
import org.sc.grammars.OntologyRenderer;

public class TriplesClause extends Clause {

	public TriplesClause(LexedTree tree, ClauseParser parser) { 
		for(LexedTree subtree : tree.children) {  
			subclauses.add(new TripleEntry(subtree));
		}
	}

	public String getName() {
		return "triples";
	}

	public <Cls, Prop> Cls[] render(Bindings bindings, OntologyRenderer<Cls, Prop> renderer) {		
		for(Clause subclause : subclauses) {
			subclause.render(bindings, renderer);
		}
		return null;
	}
}
