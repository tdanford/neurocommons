package org.sc.antibodies;

import java.util.*;

import org.sc.annotator.adaptive.*;
import org.sc.annotator.adaptive.exceptions.MatcherException;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class ResourceAnnotator {

	private AdaptiveMatcher matcher;
	private Context currentContext;
	private Set<Resource> avoidResources;
	
	public ResourceAnnotator(AdaptiveMatcher m) { 
		this(m, new Context());
	}
	
	public ResourceAnnotator(AdaptiveMatcher m, Context c) { 	
		this(m, c, null);
	}
	
	public ResourceAnnotator(AdaptiveMatcher m, Context c, Set<Resource> avoid, Resource... restAvoid) { 
		currentContext = c;
		matcher = m;
		avoidResources = new HashSet<Resource>();
		if(avoid != null) { avoidResources.addAll(avoid); }
		for(Resource r : restAvoid) { 
			avoidResources.add(r);
		}
	}
	
	public Collection<Match> annotateResource(Resource rec) throws MatcherException {
		
		avoidResources.add(rec);
		
		LinkedList<Match> allMatches = new LinkedList<Match>();
		
		StmtIterator stmts = rec.listProperties();
		try { 
			while(stmts.hasNext()) { 
				Statement stmt = stmts.next();
				
				Property property = stmt.getPredicate();
				String propertyName = property.getURI();
				Context ctxt = new Context(currentContext, propertyName);

				RDFNode objectNode = stmt.getObject();

				if(objectNode.isLiteral()) { 
					
					Literal objectLiteral = objectNode.as(Literal.class);
					
					if(objectLiteral.getDatatype().equals(XSDDatatype.XSDstring)) {
						
						String stringLiteral = objectLiteral.getString();
						
						Collection<Match> matches = matcher.findMatches(ctxt, stringLiteral);

						allMatches.addAll(matches);
					}

				} else { 

					Resource objectResource = objectNode.as(Resource.class);

					if(!avoidResources.contains(objectResource)) { 
						ResourceAnnotator annotator = 
							new ResourceAnnotator(matcher, ctxt, avoidResources, objectResource);

						allMatches.addAll(annotator.annotateResource(objectResource));
					}
				}
			}
			
		} finally { 
			stmts.close();
		}
		
		return allMatches;
	}
}
