package org.sc.grammars.clauses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.sc.grammars.Lexed;
import org.sc.grammars.LexedTree;
import org.sc.grammars.OntologyRenderer;
import org.sc.grammars.QuotedString;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class PrefixClause extends Clause {

	private String name;
	private String url;

	public PrefixClause(LexedTree tree, ClauseParser parser) { 
		name = tree.line.lexed.get(1).value;
		url = QuotedString.unquote(tree.line.lexed.get(2).value);
		
		if(tree.children.size() > 0) { 
			throw new IllegalArgumentException();
		}

		assert name != null : "Null name";
		assert url != null : "Null url";
	}

	public String getName() {
		return "prefix";
	}
	
	public <Cls, Prop> Cls[] render(Bindings bindings, OntologyRenderer<Cls, Prop> renderer) { 
		assert bindings != null : "Null bindings";
		assert bindings.getPrefixSet() != null : "Null bindings prefix set.";
		bindings.getPrefixSet().addPrefix(name, url);
		return null;
	}
}
