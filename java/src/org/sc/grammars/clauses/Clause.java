package org.sc.grammars.clauses;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.sc.grammars.OntologyRenderer;

public abstract class Clause {

	protected ArrayList<Clause> subclauses;
	
	public Clause() { 
		subclauses = new ArrayList<Clause>();
	}

	public String toString() { 
		return toString(0);
	}
	
	public String toString(int depth) { 
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < depth; i++) { 
			sb.append("  ");
		}
		sb.append(getName()); 
		sb.append("\n");
		for(Clause sc : subclauses) { 
			sb.append(sc.toString(depth+1));
		}
		return sb.toString();
	}
	
	public abstract String getName();
	
	public abstract <Cls,Prop> Cls[] render(Bindings bindings, OntologyRenderer<Cls,Prop> renderer);
	
	public static <Cls> Cls[] asArray(Cls first, Cls... cls) { 
		Class<Cls> clazz = (Class<Cls>) first.getClass();
		int length = 1 + cls.length;
		Cls[] array = (Cls[]) Array.newInstance(clazz, length);
		array[0] = first;
		for(int i = 0; i < cls.length; i++) { 
			array[i+1] = cls[i];
		}
		return array;
	}
}
