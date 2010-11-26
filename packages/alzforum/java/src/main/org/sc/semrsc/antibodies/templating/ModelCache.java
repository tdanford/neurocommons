package org.sc.semrsc.antibodies.templating;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.TreeMap;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

public class ModelCache {

	protected static String OBIuri = "http://purl.obolibrary.org/obo/";
	protected static String ROuri = "http://www.obofoundry.org/ro/ro.owl#";
	protected static String OBORELuri = "http://purl.org/obo/owl/OBO_REL#";
	protected static String Antibodyuri = "http://purl.org/tdanford/antibodies/";

	protected static String DescriptionOfAntibodyOffer = Antibodyuri + "description_of_antibody_offer";
	protected static String SuppliedMaterialRole = Antibodyuri + "supplied_material_role";
	protected static String AntibodySolution = Antibodyuri + "antibody_solution";
	protected static String AntibodyOffer = Antibodyuri + "antibody_offer";

	protected static String InformationContentEntity = OBIuri + "IAO_0000030";
	protected static String ModelNumber = OBIuri + "IAO_0000017";
	protected static String ObjectAcquisition = OBIuri + "OBI_0600010";
	protected static String SupplierRole = OBIuri + "OBI_0000018";
	protected static String Organization = OBIuri + "OBI_0000245";
	protected static String Plan = OBIuri + "OBI_0000260";
	protected static String Epitope = OBIuri + "OBI_1110001";
	protected static String DispositionToBind = OBIuri + "OBI_1110045";
	protected static String EpitopeBinding = OBIuri + "OBI_1110014";

	protected static String has_grain = OBIuri + "OBI_0000643";
	protected static String inheres_in = OBORELuri + "inheres_in";
	protected static String realizes = OBIuri + "OBI_0000308";
	protected static String has_part = ROuri + "has_part";
	protected static String bearer_of = OBORELuri + "bearer_of";
	protected static String is_realized_by = OBIuri + "OBI_0000300";
	protected static String has_participant = ROuri + "has_participant";
	
	protected OntModel model;
	protected Map<String,Resource> resources;
	protected Map<String,Property> properties;
	
	public ModelCache(OntModel m) { 
		model = m;
		resources = new TreeMap<String,Resource>();
		properties = new TreeMap<String,Property>();	
		
		for(Field field : getClass().getFields()) { 
			int mod = field.getModifiers();
			if(Modifier.isStatic(mod) && field.getType().equals(String.class) && !field.getName().endsWith("uri")) {
				String name = field.getName();
				try {
					String uri = (String)field.get(this);
					if(Character.isUpperCase(name.charAt(0))) {
						Resource r = model.getResource(uri);
						resources.put(field.getName(), r);
						resources.put(uri, r);
					} else { 
						Property p = model.getProperty(uri);
						properties.put(field.getName(), p);	
						properties.put(uri, p);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	protected OntClass hasValue(String p, Resource r) { 
		return hasValue(properties.get(p), r);
	}
	
	protected OntClass hasValue(Property p, Resource r) { 
		return model.createHasValueRestriction(null, p, r);
	}
	
	protected OntClass someValues(String p, Resource r) { 
		return someValues(properties.get(p), r);
	}
	
	protected OntClass someValues(Property p, Resource r) { 
		return model.createSomeValuesFromRestriction(null, p, r);
	}
	
	protected OntClass allValues(String p, Resource r) { 
		return allValues(properties.get(p), r);
	}
	
	protected OntClass allValues(Property p, Resource r) { 
		return model.createAllValuesFromRestriction(null, p, r);
	}
	
	protected OntClass intersection(RDFNode... nodes) { 
		return model.createIntersectionClass(null, model.createList(nodes));
	}
}
