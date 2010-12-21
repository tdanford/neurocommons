package org.sc.grammars.clauses;

import java.util.*;

import org.sc.grammars.Lexed;
import org.sc.grammars.OntologyRenderer;
import org.sc.grammars.QuotedString;

public class NamePhrase {
	
	private QuotedString quoted;
	private String var;
	private String prefixed;
	private String literal;

	public NamePhrase(Lexed l) { 
		if(l.isQuoted()) { 
			quoted = new QuotedString(l.value);
		} else if (l.isVar()) { 
			var = l.value;
		} else if (l.isPrefixed()) { 
			prefixed = l.value;
		} else if (l.isLiteral()) {
			literal = l.value;
		} else { 
			throw new IllegalArgumentException(l.type);
		}
	}
	
	public String toString() {
		if(quoted != null) { 
			return quoted.toString();
		} else if (var != null) { 
			return var;
		} else if (prefixed != null) { 
			return prefixed;
		} else if (literal != null) { 
			return literal;
		} else { 
			return null;
		}
	}
	
	public String[] expand(Bindings bindings) { 
		if(quoted != null) { 
			return new String[] { quoted.toString(bindings) }; 
			
		} else if (var != null) { 
			return bindings.lookupAllValues(var);
			
		} else if (prefixed != null) { 
			return new String[]{ bindings.getPrefixSet().expand(prefixed) };
			
		} else if (literal != null) { 
			return new String[] { literal };
			
		} else { 
			return null;
		}		
	}
	
	public <Cls, Prop> Cls[] getClass(Bindings bindings, OntologyRenderer<Cls, Prop> renderer) {
		
		if(quoted != null) { 
			return Clause.asArray(renderer.getClassByLabel(expand(bindings)[0]));
			
		} else if(var != null) {
			String[] expanded = expand(bindings);
			Cls[] array = renderer.createClsArray(expanded.length);
			for(int i = 0; i < array.length; i++) { 
				array[i] = renderer.getClassByID(expanded[i]);
			}
			
			return array;
			
		} else if(prefixed != null) {  
			return Clause.asArray(renderer.getClassByID(expand(bindings)[0]));
		
		} else { 
			return Clause.asArray(renderer.getClassByID(literal));
		}
	}

	public <Cls, Prop> Prop[] getProperty(Bindings bindings, OntologyRenderer<Cls, Prop> renderer) {
		
		if(quoted != null) { 
			return Clause.asArray(renderer.getPropertyByLabel(expand(bindings)[0]));
			
		} else if(var != null) {
			String[] expanded = expand(bindings);
			Prop[] array = renderer.createPropArray(expanded.length);
			for(int i = 0; i < array.length; i++) { 
				array[i] = renderer.getPropertyByID(expanded[i]);
			}
			
			return array;
			
		} else if(prefixed != null) {  
			return Clause.asArray(renderer.getPropertyByID(expand(bindings)[0]));
		
		} else { 
			return Clause.asArray(renderer.getPropertyByID(literal));
		}
	}

}
