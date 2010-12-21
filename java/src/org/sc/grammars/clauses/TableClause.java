package org.sc.grammars.clauses;

import java.util.*;
import java.util.regex.*;
import java.io.*;

import org.sc.grammars.FileTable;
import org.sc.grammars.Lexed;
import org.sc.grammars.LexedTree;
import org.sc.grammars.OntologyRenderer;

public class TableClause extends Clause {
	
	private String filename;
	private ArrayList<String> vars;
	private Map<Integer,String> filterValues;
	
	private static Pattern varPattern = Pattern.compile("(\\$|@)(.*)");
	private static Pattern quotedPattern = Pattern.compile("^'(.*)'$");

	public TableClause(LexedTree tree, ClauseParser parser) { 
		filename = tree.line.lexed.get(1).value;
		vars = new ArrayList<String>();
		filterValues = new TreeMap<Integer,String>();
		
		for(int i = 2; i < tree.line.lexed.size(); i++) {
			Lexed lexed = tree.line.lexed.get(i);
			if(lexed.isVar()) { 
				vars.add(tree.line.lexed.get(i).value);				
			} else { 
				vars.add(null);
				Matcher quoted = quotedPattern.matcher(lexed.value);
				if(!quoted.matches()) { throw new IllegalArgumentException(lexed.value); }
				filterValues.put(i-2, quoted.group(1));
			}
		}
		
		for(LexedTree subtree : tree.children) {  
			Lexed head = subtree.line.lexed.get(0);
			if(!head.isDefine() && !head.isAssert() && !head.isTriples()) { 
				throw new IllegalArgumentException(String.format(
						"Unknown head type: %s", head.type));
			}
			
			subclauses.add(parser.parseClause(subtree));
		}
	}
	
	public String getName() { return "table"; }
	
	public <Cls, Prop> Cls[] render(Bindings bindings, OntologyRenderer<Cls, Prop> renderer) { 

		String dir = bindings.lookup("$dir");
		System.err.println(String.format("dir: %s", dir));

		File file = new File(new File(dir), filename);

		if(file.exists() && file.canRead()) {
			System.err.println(String.format("Exporting: %s", file.getAbsolutePath()));
			try {
				FileTable table = new FileTable(file);

				for(int i = 0; i < table.size(); i++) { 
					Bindings b = bindRow(bindings, table, i);
					if(b != null) {
						// null bindings indicates a filter failure.
						
						for(Clause c : subclauses) { 
							c.render(b, renderer);
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { 
			System.err.println(String.format("No such file: %s", file.getAbsolutePath()));
		}
		
		return null;
	}
	
	private Bindings bindRow(Bindings b, FileTable table, int r) {  
		String[] row = table.row(r);
		Bindings bind = new Bindings(b);
		
		int i = 0;
		for(String varKey : vars) {
			if(varKey != null) { 
				Matcher m = varPattern.matcher(varKey);
				if(!m.matches()) { throw new IllegalArgumentException(varKey); }

				String varName = m.group(2);
				Integer hIdx = table.headerIndices.get(varName);
				if(hIdx == null) { 
					throw new IllegalArgumentException(String.format("%s not in %s",
							varName, table.headerIndices.keySet()));
				}
				
				if(varKey.startsWith("@")) { 
					String[] valueArray = row[hIdx].split("|");
					for(String value : valueArray) { 
						bind.bind(String.format("$%s", varName), value);						
					}
				} else { 
					bind.bind(String.format("$%s", varName), row[hIdx]);
				}
				//bind.bind(varName, row[hIdx]);
			} else { 
				if(!row[i].equals(filterValues.get(i))) { 
					return null;
				}
			}
			i++;
		}
		
		return bind;
	}
}
