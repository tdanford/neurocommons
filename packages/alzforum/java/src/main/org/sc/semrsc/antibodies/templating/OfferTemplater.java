package org.sc.semrsc.antibodies.templating;

import java.util.*;
import java.lang.reflect.*;

import org.sc.GenSym;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

public class OfferTemplater extends ModelCache {
	
	private CommonEnv env;
	
	public OfferTemplater(CommonEnv env) {
		super(env.getModel());
		this.env = env;
	}
	
	public void template(
			String offer, 
			String antibody, 
			String offeringOrganization, 
			String catalog, 
			String[] solutionParts, 
			String[] solutionQualities) {
		
		Individual catalogNumber = env.getCatalogNumber(catalog);
		
		OntClass antibodyOffer = env.getAntibodyOffer(offer);
		OntClass antibodySolution = resources.get("AntibodySolution").as(OntClass.class);
		OntClass antibodyCls = env.getAntibody(antibody);
		
		antibodyOffer.addSuperClass(hasValue("has_part", catalogNumber));

		OntClass cls = intersection(antibodySolution, someValues("has_grain", antibodyCls));
		for(String solutionPart : solutionParts) { 
			cls = intersection(cls, someValues("has_part", env.getSolutionPart(solutionPart)));
		}
		for(String solutionQuality : solutionQualities) { 
			cls = intersection(cls, someValues("bearer_of", env.getSolutionQuality(solutionQuality)));
		}
		
		cls = intersection(resources.get("SuppliedMaterialRole").as(OntClass.class),
				someValues("inheres_in", cls));
		
		OntClass cls2 = intersection(resources.get("SupplierRole").as(OntClass.class),
				hasValue("inheres_in", env.getOrganization(offeringOrganization)));
		
		cls = intersection(
				resources.get("ObjectAcquisition").as(OntClass.class),
				someValues("realizes", cls), 
				someValues("realizes", cls2));
		
		cls = intersection(resources.get("Plan").as(OntClass.class),
				allValues("is_realized_by", cls));
		
		antibodyOffer.addSuperClass(someValues("is_about", cls));		
	}
}
