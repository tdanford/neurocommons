package org.sc.grammars.clauses;

import org.sc.grammars.LexedTree;
import org.sc.grammars.OntologyRenderer;

public class TripleEntry extends Clause {
	
	private NamePhrase subject, predicate, object;

	public TripleEntry(LexedTree t) { 
		subject = new NamePhrase(t.line.lexed.get(0));
		predicate = new NamePhrase(t.line.lexed.get(1));
		object = new NamePhrase(t.line.lexed.get(2));
	}

	public String getName() {
		return String.format("%s\t%s\t%s", subject.toString(), predicate.toString(), object.toString());
	}

	public <Cls, Prop> Cls[] render(Bindings bindings, OntologyRenderer<Cls, Prop> renderer) {
		
		String[] propNames = predicate.expand(bindings);
		
		for(String propName : propNames) { 
			Cls[] c1s = subject.getClass(bindings, renderer), c2s = object.getClass(bindings, renderer);

			for(Cls c1 : c1s) { 
				for(Cls c2 : c2s) { 
					renderer.assertTriple(c1, propName, c2);					
				}
			}
		}

		return null;
	}
}
