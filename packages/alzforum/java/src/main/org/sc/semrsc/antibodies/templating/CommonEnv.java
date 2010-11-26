package org.sc.semrsc.antibodies.templating;

import org.sc.GenSym;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Resource;

import java.util.*;

public class CommonEnv extends ModelBuilder {

	private GenSym sym;
	
	private Map<String,Resource> resourceCache;
	
	public CommonEnv(GenSym sym, OntModel m) { 
		super(m);
		this.sym = sym;
		resourceCache = new TreeMap<String,Resource>();
	}
	
	public OntModel getModel() { return model; }
	
	private OntClass createClass(String lbl, OntClass... supers) { 
		if(!resources.containsKey(lbl)) {
			OntClass cls = model.createClass(sym.nextSymbol());
			cls.addLabel(model.createLiteral(lbl));
			for(OntClass spr : supers) { 
				cls.addSuperClass(spr);
			}
			resourceCache.put(lbl, cls);
		}
		return resourceCache.get(lbl).as(OntClass.class);	
	}
	
	private Individual createIndividual(String lbl, OntClass cls) { 
		if(!resources.containsKey(lbl)) {
			Individual idv = model.createIndividual(sym.nextSymbol(), cls);
			idv.addLabel(model.createLiteral(lbl));
			resourceCache.put(lbl, idv);
		}
		return resourceCache.get(lbl).as(Individual.class);	
	}
	
	public OntClass getAntibody(String antibody) {
		return createClass(antibody);
	}

	public OntClass getAntigen(String antigen) {
		return createClass(antigen);
	}

	public Individual getCatalogNumber(String catalog) {
		return createIndividual(catalog, resources.get("ModelNumber").as(OntClass.class));
	}

	public OntClass getAntibodyOffer(String offer) {
		return createClass(offer, resources.get("AntibodyOffer").as(OntClass.class));
	}

	public OntClass getSolutionPart(String solutionPart) {
		return createClass(solutionPart);
	}

	public Resource getSolutionQuality(String solutionQuality) {
		return createClass(solutionQuality);
	}

	public Individual getOrganization(String offeringOrganization) {
		return createIndividual(offeringOrganization, resources.get("Organization").as(OntClass.class));
	}
}
