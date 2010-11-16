package org.sc.semrsc.antibodies.templating;

import java.util.*;
import java.lang.reflect.*;

import org.sc.GenSym;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

public class SpecificityTemplater extends ModelCache {
	
	private CommonEnv env;
	
	public SpecificityTemplater(CommonEnv env) {
		super(env.getModel());
		this.env = env;
	}
	
	public void template(String antibody, String antigen) { 
		OntClass AntibodyClass = env.getAntibody(antibody);
		OntClass AntigenClass = env.getAntigen(antigen);
		
		OntClass cls = AntibodyClass;
		
		cls = intersection(resources.get("EpitopeBinding"), someValues("has_participant", cls));
		cls = intersection(resources.get("DispositionToBind"), someValues("is_realized_by", cls));
		cls = intersection(resources.get("Epitope"), someValues("bearer_of", cls));
		
		cls = someValues("has_part", cls);
		
		AntigenClass.addSuperClass(cls);
	}
}
