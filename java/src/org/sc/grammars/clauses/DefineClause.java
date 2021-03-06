package org.sc.grammars.clauses;

import java.util.*;

import org.sc.grammars.Lexed;
import org.sc.grammars.LexedTree;
import org.sc.grammars.OntologyRenderer;

public class DefineClause extends Clause {

	private NamePhrase name;

	public DefineClause(LexedTree tree, ClauseParser parser) { 
		name = new NamePhrase(tree.line.lexed.get(1));
		
		for(LexedTree subtree : tree.children) {  
			Lexed head = subtree.line.lexed.get(0);
			if(head.isDefine() || head.isAssert() || head.isTable()) { 
				throw new IllegalArgumentException(String.format(
						"Illegal head type: %s", head.type));
			}
			
			subclauses.add(parser.parseClause(subtree));
		}
	}
	
	public String getName() { 
		return "define";
	}

	public <Cls, Prop> Cls[] render(Bindings bindings, OntologyRenderer<Cls, Prop> renderer) {
		Cls[] nameClasses = name.getClass(bindings, renderer);

		for(Cls nameClass : nameClasses) { 
			ArrayList<Cls> total = new ArrayList<Cls>();
			for(Clause subclause : subclauses) { 
				Cls[] superClasses = subclause.render(bindings, renderer);
				total.addAll(Arrays.asList(superClasses));
			}
			renderer.assertEquivalent(nameClass, total);
		}

		return nameClasses;
	}
}
