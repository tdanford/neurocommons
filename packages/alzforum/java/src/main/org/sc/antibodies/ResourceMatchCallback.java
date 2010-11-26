package org.sc.antibodies;

import org.sc.annotator.adaptive.Match;

import com.hp.hpl.jena.rdf.model.Resource;

public interface ResourceMatchCallback {

	public void matchFound(Resource r, String p, Match m);
}
