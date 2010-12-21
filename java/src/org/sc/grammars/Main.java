package org.sc.grammars;

import java.io.*;

import java.util.*;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RuleReturnScope;
import org.antlr.runtime.debug.ParseTreeBuilder;
import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.CommonTree;

import org.sc.grammars.clauses.*;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;


public class Main {

	/* 
	 * Need to compile LP.g with the -debug flag.  
	 * 
	 * (Can I rewrite this in such a way that that's not required?) 
	 */
	public static void main(String[] args) throws IOException, RecognitionException {
		System.out.println("Parsing...");
		File dir = new File("/Users/tdanford/Desktop/annotations/");
		
        OntoLexer lex = new OntoLexer(new ANTLRFileStream("grammars/antibodies.ont"));
       	CommonTokenStream tokens = new CommonTokenStream(lex);
       	
        ParseTreeBuilder builder = new ParseTreeBuilder("ontology");
        OntoParser parser = new OntoParser(tokens, builder);
        //OntologyParser parser = new OntologyParser(tokens);
        
        System.out.println("Getting ontology...");
        OntoParser.template_return ret = parser.template();

        System.out.println("Lexing...");
        PushbackIterator<Lexed> lexitr = lexed(ret);
        
        //while(lexitr.hasNext()) { System.out.println(lexitr.next()); }

        System.out.println("Linebreaking...");
        Iterator<LexedLine> lineitr = new Linebreaker(lexitr);    
        //while(lineitr.hasNext()) {  System.out.println(lineitr.next()); }

        Iterator<LexedTree> treeitr = new Treemaker(lineitr);
        //while(treeitr.hasNext()) { System.out.println(treeitr.next()); }
        
        ClauseParser clauser = new ClauseParser();
        
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        String prefix = "http://purl.org/tdanford/neurocommons";
        
        OntologyRenderer<OntClass,ObjectProperty> renderer = new OWLRenderer(model, prefix);
        
        Bindings binds = new Bindings("$dir", dir.getAbsolutePath());
     
        while(treeitr.hasNext()) { 
        	Clause c = clauser.parseClause(treeitr.next());
        	
        	System.out.println(c.toString());
        	
        	c.render(binds, renderer);
        }
        
        renderer.render(new PrintWriter(System.out));
	}
	
	public static PushbackIterator<Lexed> lexed(ParserRuleReturnScope ret) {
		CommonTree tree = (CommonTree)ret.getTree();
		LinkedList<Lexed> list = new LinkedList<Lexed>();
		for(Object child : tree.getChildren()) { 
			CommonTree childTree = (CommonTree)child;
			list.add(new Lexed(childTree));
		}
		return new PushbackWrapper<Lexed>(new FilterWhitespace(list.iterator()));
		//return new PushbackWrapper<Lexed>(list.iterator());
	}
}

class FilterWhitespace implements Iterator<Lexed> { 
	
	private Iterator<Lexed> itr;
	
	private Lexed next;
	
	public FilterWhitespace(Iterator<Lexed> i) { 
		itr = i;
		findNextLexed();
	}
	
	public void remove() { throw new UnsupportedOperationException(); } 
	
	public Lexed next() { 
		Lexed l = next;
		findNextLexed();
		return l;
	}
	
	public boolean hasNext() { 
		return next != null;
	}
	
	private void findNextLexed() { 
		next = null;
		while(itr.hasNext() && canSkip(next = itr.next())) { 
			next = null;
		}
	}
	
	private boolean canSkip(Lexed lex) { 
		return lex.isWhitespace(); 
	}
}
