package org.sc.grammars;

import java.io.PrintWriter;
import java.util.*;
import com.hp.hpl.jena.ontology.*;

public interface OntologyRenderer<Cls,Prop> {
	
	public void assertTriple(Cls c1, String tripleProp, Cls c2);

	public Cls getClassByLabel(String lbl);
	public Cls getClassByID(String id);
	
	public Cls getSome(Prop p, Cls t);
	public Cls getOnly(Prop p, Cls t);
	public Cls getExactly(Prop p, String t);
	
	public Cls getIntersection(Collection<Cls> classList);
	public Cls getUnion(Collection<Cls> classList);
	
	public Prop getPropertyByLabel(String lbl);
	public Prop getPropertyByID(String id);
	
	public void assertSubclass(Cls c1, Cls c2);
	public void assertEquivalent(Cls c1, Collection<Cls> equiv);
	
	public void render(PrintWriter writer);
	
	public Prop[] createPropArray(int length);
	public Cls[] createClsArray(int length);
}

