package org.sc.grammars.clauses;

import java.util.*;

import org.sc.grammars.Lexed;
import org.sc.grammars.LexedTree;
import org.sc.grammars.OntologyRenderer;

public class SubclassClause extends Clause {

	public static enum ClassType { 
		NAMED, RESTRICTSOME, RESTRICTONLY, RESTRICTVALUE
	};
	
	public ClassType type;
	
	public NamePhrase property;
	public NamePhrase named;
	
	public SubclassClause(LexedTree tree, ClauseParser parser) { 
		named = new NamePhrase(tree.line.lexed.get(0));
		type = ClassType.NAMED;
		
		if(tree.line.lexed.size() > 1) { 
			property = named;
			Lexed typelex = tree.line.lexed.get(1);
			Lexed valuelex = tree.line.lexed.size() > 2 ? 	
					tree.line.lexed.get(2) : null;
					
			if(typelex.isSome()) { 
				type = ClassType.RESTRICTSOME;

			} else if (typelex.isOnly()) { 
				type = ClassType.RESTRICTONLY;
				
			} else if (typelex.isValue()) { 
				type = ClassType.RESTRICTVALUE;
				
			} else { 
				throw new IllegalArgumentException(String.format(
						"Illegal restriction type %s", typelex.value));
			}
			
			if(valuelex != null) { 
				named = new NamePhrase(valuelex);
			}
		}
		
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
		return type.toString();
	}

	public <Cls, Prop> Cls[] render(Bindings bindings, OntologyRenderer<Cls, Prop> renderer) {
		Prop[] props = type.equals(ClassType.NAMED) ? null : property.getProperty(bindings, renderer);

		Cls[] classes = values(bindings, renderer);
		
		ArrayList<Cls> total = new ArrayList<Cls>();

		for(int i = 0; i < classes.length; i++) { 
			Cls cls = classes[i];

			if(type.equals(ClassType.NAMED)) { 
				total.add(cls);
			} else { 

				for(int j = 0; props != null && j < props.length; j++) {
					Prop prop = props[j];

					switch(type) { 
					case NAMED:
						break;

					case RESTRICTSOME:
						total.add(renderer.getSome(prop, cls));
						break;

					case RESTRICTONLY:
						total.add(renderer.getOnly(prop, cls));
						break;

					case RESTRICTVALUE:
						total.add(renderer.getExactly(prop, named.toString()));
						break;

					}
				}
			}
		}

		return total.toArray(renderer.createClsArray(0));
	}

	public <Cls, Prop> Cls[] values(Bindings bindings, OntologyRenderer<Cls, Prop> renderer) {
		
		Cls[] values = named != null ? named.getClass(bindings, renderer) : renderer.createClsArray(0);
		
		if(subclauses.isEmpty()) { 
			return values;
		} else { 
			for(int i = 0; i < values.length; i++) { 
				Cls value = values[i];

				ArrayList<Cls> valueIntersection = new ArrayList<Cls>();
				valueIntersection.add(value);

				for(Clause subclause : subclauses) {
					Cls[] all = subclause.render(bindings, renderer);
					valueIntersection.addAll(Arrays.asList(all));
				}
				
				values[i] = renderer.getIntersection(valueIntersection);
			}
			
			return values;
		}
	}

}
