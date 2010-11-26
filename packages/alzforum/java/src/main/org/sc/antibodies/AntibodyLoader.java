package org.sc.antibodies;

import java.util.*;
import java.io.*;

import org.sc.annotator.adaptive.AdaptiveMatcher;
import org.sc.annotator.adaptive.Context;
import org.sc.annotator.adaptive.Match;
import org.sc.annotator.adaptive.client.WebClient;
import org.sc.annotator.adaptive.exceptions.MatcherException;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class AntibodyLoader implements ResourceMatchCallback {
	
	public static void main(String[] args) throws IOException, MatcherException { 
		AntibodyLoader loader = new AntibodyLoader(new File(args[0]));
		AdaptiveMatcher matcher = new WebClient("http://localhost:8080/matcher");

		//loader.loadMatches(matcher);
		//loader.annotate(args[1], matcher);
		
		loader.annotateAntibodies(matcher);
	}
	
	private Model model;
	private OntModel ontology;

	public AntibodyLoader(File f) throws IOException { 
		model = ModelFactory.createDefaultModel();
		String base = null;
		String lang = "RDF/XML";
		InputStream is = new FileInputStream(f);
		model.read(is, base, lang);
		is.close();

		ontology = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM);
		ontology.add(model);
	}
	
	public void loadMatches(AdaptiveMatcher matcher) throws MatcherException {
		matcher.reset();
		for(Match m : getMatches()) { 
			matcher.registerMatch(m);
			//System.out.println(m.toString());
		}
	}
	
	public static String annotationsURI = "http://mindinformatics.org/annotations/2010/1/10/annotations.owl";
	public static String antibodyIdURI = "http://neurocommons.org/antibodies/antibodyID";
	
	private Collection<Match> getMatches() { 
		Collection<OntResource> annotations = findAnnotations();
		LinkedList<Match> matches = new LinkedList<Match>();
		for(OntResource rec : annotations){ 
			matches.add(getMatch(rec));
		}
		return matches;
	}
	
	private boolean isAcceptingJudgement(OntResource rec) { 
		ObjectProperty judgedAs = ontology.getObjectProperty(annotationsURI + "#judgedAs");
		OntResource judgementValue = rec.getPropertyValue(judgedAs).as(OntResource.class);
		return judgementValue.getURI().equals(annotationsURI + "#accept");
	}
	
	private OntResource getJudgement(OntResource rec) { 
		ObjectProperty hasJudgement = ontology.getObjectProperty(annotationsURI + "#hasJudgement");
		RDFNode judgementNode = rec.getPropertyValue(hasJudgement);
		return judgementNode != null ? judgementNode.as(OntResource.class) : null;
	}
	
	private boolean isAccepted(OntResource rec) { 
		OntResource judgement = getJudgement(rec);
		return judgement != null && isAcceptingJudgement(judgement);
	}
	
	private Match getMatch(OntResource rec) { 
		return new Match(getContext(rec),
				getSourceString(rec),
				getTargetString(rec));
	}
	
	private Context getContext(OntResource annotation) { 
		DatatypeProperty prop = ontology.getDatatypeProperty(annotationsURI + "#annotatesProperty");
		RDFNode node = annotation.getPropertyValue(prop);
		Literal literal = node.as(Literal.class);
		
		ObjectProperty annotates = ontology.getObjectProperty(annotationsURI + "#annotates");
		OntResource antibody = annotation.getPropertyValue(annotates).as(OntResource.class);
		
		Property antibodyIDProp = ontology.getProperty(antibodyIdURI);
		Literal antibodyIDLiteral = antibody.getPropertyValue(antibodyIDProp).as(Literal.class);
		
		String column = literal.getString();
		String antibodyID = antibodyIDLiteral.toString();
		
		assert column != null;
		assert antibody != null;
		
		//Context c = new Context(new String[] { "antibodies", column, antibodyID });
		Context c = new Context(new String[] { "antibodies", column });
		
		return c;
	}
	
	private String getSourceString(OntResource annotation) { 
		DatatypeProperty annotatesText = ontology.getDatatypeProperty(annotationsURI + "#annotatesText");
		RDFNode node = annotation.getPropertyValue(annotatesText);
		Literal literal = node.as(Literal.class);
		return literal.getString().replace('\n', ' ');
	}
	
	private String getTargetString(OntResource annotation) { 
		DatatypeProperty indicates = ontology.getDatatypeProperty(annotationsURI + "#indicates");
		RDFNode node = annotation.getPropertyValue(indicates);
		Literal literal = node.as(Literal.class);
		return literal.getString();
	}
	
	private Collection<OntResource> findAnnotations() { 
		LinkedList<OntResource> annotationURIs = new LinkedList<OntResource>();
		
		String AnnotationURI = annotationsURI + "#Annotation";
		OntClass Annotation = ontology.getOntClass(AnnotationURI);
		
		ExtendedIterator<? extends OntResource> itr = Annotation.listInstances();
		try { 
			while(itr.hasNext()) { 
				OntResource res = itr.next();
				if(isAccepted(res)) { 
					annotationURIs.add(res);
				}
			}
		} finally { 
			itr.close();
		}
		
		return annotationURIs;
	}
	
	private boolean hasAnnotations(OntResource rec) { 
		boolean hasAnnotations = false;

		ObjectProperty hasAnnotation = ontology.getObjectProperty(annotationsURI + "#hasAnnotation");
		NodeIterator itr = rec.listPropertyValues(hasAnnotation);
		hasAnnotations = itr.hasNext();
		itr.close();

		return hasAnnotations;
	}

	private Collection<OntResource> findAntibodies() { 
		String antibodyURI = "http://neurocommons.org/antibodies/Antibody";
		OntClass Antibody = ontology.getOntClass(antibodyURI);
		ExtendedIterator<? extends OntResource> itr = Antibody.listInstances();
		LinkedList<OntResource> antibodies = new LinkedList<OntResource>();
		try { 
			while(itr.hasNext()) { 
				OntResource rec = itr.next();
				antibodies.add(rec);
			}
		} finally { 
			itr.close();
		}
		
		return antibodies;
	}
	
	public void annotateAntibodies(AdaptiveMatcher matcher) throws MatcherException { 
		Collection<OntResource> antibodies = findAntibodies();
		for(OntResource antibody : antibodies) { 
			if(!hasAnnotations(antibody)) { 
				annotate(antibody, matcher);
			}
		}
	}
	
	private void annotate(OntResource rec, AdaptiveMatcher matcher) throws MatcherException { 
		
		ResourceAnnotator annotator = new ResourceAnnotator(matcher, this);

		annotator.annotateResource(rec);
	}

	public void matchFound(Resource r, String p, Match m) {
		System.out.println(String.format("%s -> %s", r.toString(), m.toString()));
	}	
}
