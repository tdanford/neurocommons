package org.sc.semrsc.antibodies.templating;

import java.util.*;
import java.lang.reflect.*;

import org.sc.GenSym;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

public class AntibodyCreationTemplater extends ModelCache {
	
	private CommonEnv env;
	
	public AntibodyCreationTemplater(CommonEnv env) {
		super(env.getModel());
		this.env = env;
	}
	
	public void template(
			String antibody, 
			String isotype, 
			String host, 
			String immunogen, 
			String clonality, 
			String clone_number) { 
	}

	public void templateMonoclonalAntibody(
			String antibody, 
			String isotype, 
			String host, 
			String immunogen, 
			String clone_number) { 
	}

	public void templatePolyclonalAntibody(
			String antibody, 
			String isotype, 
			String host) { 
	}
}
