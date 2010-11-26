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
	
	private ResourceMatchCallback callback;
	
	public ResourceAnnotator(AdaptiveMatcher m, ResourceMatchCallback cb) { 
		this(m, cb, new Context());
	}
	
	public ResourceAnnotator(AdaptiveMatcher m, ResourceMatchCallback cb, Context c) { 	
		this(m, cb, c, null);
	}
	
	public ResourceAnnotator(AdaptiveMatcher m, ResourceMatchCallback cb, Context c, Set<Resource> avoid, Resource... restAvoid) { 
		currentContext = c;
		matcher = m;
		callback = cb;
		avoidResources = new HashSet<Resource>();
		if(avoid != null) { avoidResources.addAll(avoid); }
		for(Resource r : restAvoid) { 
			avoidResources.add(r);
		}
	}
	
	public void registerResourceAnnotations(Resource rec) throws MatcherException { 
		
	}
	
	public void annotateResource(Resource rec) throws MatcherException {
		
		avoidResources.add(rec);
		
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
						
						for(Match m : matches) { 
							callback.matchFound(rec, propertyName, m);
						}
					}

				} else { 

					Resource objectResource = objectNode.as(Resource.class);

					if(!avoidResources.contains(objectResource)) { 
						ResourceAnnotator annotator = 
							new ResourceAnnotator(matcher, callback, ctxt, avoidResources, objectResource);

						annotator.annotateResource(objectResource);
					}
				}
			}
			
		} finally { 
			stmts.close();
		}
	}
}
