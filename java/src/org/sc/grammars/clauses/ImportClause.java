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

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ImportClause extends Clause {

	private String name;
	private File file;

	public ImportClause(LexedTree tree, ClauseParser parser) { 
		name = tree.line.lexed.get(1).value;
		file = new File(tree.line.lexed.get(2).value);
		
		if(tree.children.size() > 0) { 
			throw new IllegalArgumentException();
		}
	}

	public String getName() {
		return "import";
	}
	
	public OntModel loadOWLModel() throws IOException { 
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		InputStream is = new FileInputStream(file);
		model.read(is, null, "RDF/XML");
		is.close();
		return model;
	}

	public <Cls, Prop> Cls[] render(Bindings bindings, OntologyRenderer<Cls, Prop> renderer) { 
		return null;
	}
}
