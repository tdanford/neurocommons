package org.sc.semrsc.antibodies.templating;

import java.util.*;

import org.sc.GenSym;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;

public class ModelBuilder extends ModelCache {
	
	public ModelBuilder(OntModel m) {
		super(m);
	}
	
	public OntClass namedClass(String name) { 
		return resources.get(name).as(OntClass.class);
	}
	

}
