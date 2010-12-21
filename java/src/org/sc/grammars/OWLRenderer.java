package org.sc.grammars;

import java.io.PrintWriter;
import java.lang.reflect.Array;

import java.util.*;

import org.sc.GenSym;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;

public class OWLRenderer implements OntologyRenderer<OntClass,ObjectProperty> {
	
	private OntModel model;
	private Model triples;
	
	private Property rdfsLabel, rdfType;
	private Resource owlClass, owlThing, owlObjectProperty;
	
	private String prefix;
	private GenSym syms;
	
	private Map<String,OntClass> label2Class;
	private Map<String,ObjectProperty> label2Prop;
	private Map<String,Individual> label2Individual;
	
	public OWLRenderer(OntModel m, String prefix) {
		this.prefix = prefix;
		
		model = m;
		triples = ModelFactory.createDefaultModel();
		
		rdfsLabel = model.getProperty("http://www.w3.org/2000/01/rdf-schema#label");
		rdfType = model.getProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
		owlClass = model.getResource("http://www.w3.org/2002/07/owl#Class");
		owlThing = model.getResource("http://www.w3.org/2002/07/owl#Thing");
		owlObjectProperty = model.getResource("http://www.w3.org/2002/07/owl#ObjectProperty");
		
		syms = new GenSym(prefix + "#temp_");
		label2Class = new TreeMap<String,OntClass>();
		label2Prop = new TreeMap<String,ObjectProperty>();
		label2Individual = new TreeMap<String,Individual>();
	}

	public void render(PrintWriter writer) {
		model.write(writer, "RDF/XML-ABBREV", prefix);
	}

	public void assertSubclass(OntClass c1, OntClass c2) {
		c1.addSuperClass(c2);
	}

	public OntClass getClassByID(String id) {
		return model.createClass(id);
	}

	public OntClass getClassByLabel(String lbl) {
		if(!label2Class.containsKey(lbl)) { 
			OntClass cls = findLabeledClass(lbl);
			if(cls == null) { 
				cls = model.createClass(syms.nextSymbol());
				cls.addLabel(model.createLiteral(lbl));
			}
			label2Class.put(lbl, cls);
		}
		
		return label2Class.get(lbl);
	}
	
	public Individual getIndividualByLabel(String lbl) { 
		if(!label2Individual.containsKey(lbl)) { 
			Individual idv = findLabeledIndividual(lbl);
			if(idv == null) { 
				idv = model.createIndividual(syms.nextSymbol(), owlThing);
				idv.addLabel(model.createLiteral(lbl));
			}
			label2Individual.put(lbl, idv);
		}
		return label2Individual.get(lbl);
	}
	
	private Individual findLabeledIndividual(String lbl) {
		ResIterator itr = model.listSubjectsWithProperty(rdfsLabel, lbl);
		try { 
			while(itr.hasNext()) { 
				Resource res = itr.next();
				if(res.hasProperty(rdfType, owlThing)) { 
					return res.as(Individual.class);
				}
			}
		} finally { 
			itr.close();
		}

		return null;
	}
	
	private OntClass findLabeledClass(String lbl) {
		ResIterator itr = model.listSubjectsWithProperty(rdfsLabel, lbl);
		try { 
			while(itr.hasNext()) { 
				Resource res = itr.next();
				if(res.hasProperty(rdfType, owlClass)) { 
					return res.as(OntClass.class);
				}
			}
		} finally { 
			itr.close();
		}
		
		return null;
	}

	public OntClass getExactly(ObjectProperty p, String t) {
		assert p != null;
		Individual idv = getIndividualByLabel(t);
		assert idv != null;
		return model.createHasValueRestriction(null, p, idv);
	}

	public OntClass getOnly(ObjectProperty p, OntClass t) {
		return model.createAllValuesFromRestriction(null, p, t);
	}

	public ObjectProperty getPropertyByID(String id) {
		return model.createObjectProperty(id);
	}

	public ObjectProperty getPropertyByLabel(String lbl) {
		if(!label2Prop.containsKey(lbl)) { 
			ObjectProperty prop = findLabeledProperty(lbl);
			if(prop == null) { 
				prop = model.createObjectProperty(syms.nextSymbol());
				prop.addLabel(model.createLiteral(lbl));
			}
			label2Prop.put(lbl, prop);
		}
		
		return label2Prop.get(lbl);
	}

	private ObjectProperty findLabeledProperty(String lbl) {
		ResIterator itr = model.listSubjectsWithProperty(rdfsLabel, lbl);
		try { 
			while(itr.hasNext()) { 
				Resource res = itr.next();
				if(res.hasProperty(rdfType, owlObjectProperty)) { 
					return res.as(ObjectProperty.class);
				}
			}
		} finally { 
			itr.close();
		}
		
		return null;
	}

	public OntClass getSome(ObjectProperty p, OntClass t) {
		return model.createSomeValuesFromRestriction(null, p, t);
	}

	public OntClass getIntersection(Collection<OntClass> classList) {
		return model.createIntersectionClass(null, 
				model.createList(classList.toArray(new OntClass[0])));
	}
	
	public void assertEquivalent(OntClass c1, Collection<OntClass> equiv) {
		OntClass intersection = getIntersection(equiv);
		c1.addEquivalentClass(intersection);
	}	

	public OntClass getUnion(Collection<OntClass> classList) {
		return model.createUnionClass(null, 
				model.createList(classList.toArray(new OntClass[0])));
	}

	public void assertTriple(OntClass c1, String tripleProp, OntClass c2) {
		String c1URI = c1.getURI(), c2URI = c2.getURI();
		
		assert c1URI != null : String.format("Null c1URI (%s)", c1.toString());
		assert c2URI != null : String.format("Null c2URI (%s)", c2.toString());
		
		Property prop = triples.createProperty(tripleProp);
		Resource m1 = triples.createResource(c1URI);
		Resource m2 = triples.createResource(c2URI);
		
		assert prop != null;
		assert m1 != null;
		assert m2 != null;

		m1.addProperty(prop, m2);
	}

	public OntClass[] createClsArray(int length) {
		return (OntClass[])Array.newInstance(OntClass.class, length);
	}

	public ObjectProperty[] createPropArray(int length) {
		return (ObjectProperty[])Array.newInstance(ObjectProperty.class, length);
	}

}

