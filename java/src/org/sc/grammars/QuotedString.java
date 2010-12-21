package org.sc.grammars;

import java.util.*;
import java.util.regex.*;

import org.sc.grammars.clauses.Bindings;

public class QuotedString {

	private static Pattern var = Pattern.compile("(\\$[\\w\\d]+)");
	private static Pattern quotedPattern = Pattern.compile("^'(.*)'$");
	
	private String string;
	
	private ArrayList<String> vars;
	private ArrayList<int[]> slots;
	
	public static String unquote(String quoted) { 
		Matcher m = quotedPattern.matcher(quoted);
		if(m.matches()) { 
			return m.group(1);
		} else { 
			return quoted;
		}
	}
	
	public QuotedString(String str) { 
		string = str;

		vars = new ArrayList<String>();
		slots = new ArrayList<int[]>();
		
		Matcher vm = var.matcher(str);
		while(vm.find()) { 
			int[] p = new int[] { vm.start() + 1, vm.end() };
			String varName = vm.group(1);

			// no duplicate var names.
			if(vars.contains(varName)) { throw new IllegalArgumentException(varName); }
			
			vars.add(varName);
			slots.add(p);
		}
	}
	
	public Pattern pattern() {
		String patt = string;
		
		patt = patt.replaceAll("\\(", "\\\\(");
		patt = patt.replaceAll("\\)", "\\\\)");
		
		patt = patt.replaceAll("\\[", "\\\\[");
		patt = patt.replaceAll("\\]", "\\\\]");
		
		patt = patt.replaceAll("\\{", "\\\\{");
		patt = patt.replaceAll("\\}", "\\\\}");
		
		patt = patt.replaceAll("\\.", "\\\\.");
		patt = patt.replaceAll("\\?", "\\\\?");
		patt = patt.replaceAll("\\+", "\\\\+");
		
		patt = patt.replaceAll("^\'", "\'?");
		patt = patt.replaceAll("\"$", "\'?");
		
		patt = patt.replaceAll("\\$[\\w\\d]+", "(.+)");
		
		System.out.println(patt);
		
		return Pattern.compile(patt);
	}
	
	public int length() { return vars.size(); }
	public String var(int i) { return vars.get(i); }
	
	public Set<String> vars() { return new TreeSet<String>(vars); } 
	
	public String[] varNames() { return new TreeSet<String>(vars).toArray(new String[0]); }
	
	public String toString() { return String.format("%s", string); }
	
	
	
	public String toString(Bindings b) { 
		String str = string;
		String original = str;
		Pattern p = Pattern.compile("(^|[^\\\\])\\$");
		
		for(String var : varNames()) {
			if(b.hasValue(var)) { 
				String value = b.lookup(var);
				Matcher m = p.matcher(value);
				if(m.find()) { throw new IllegalArgumentException(value); }
				
				str = str.replaceAll("\\" + var, value);
				//System.err.println(String.format("\tBound '%s' to '%s'", var, value));
			} else { 
				System.err.println(String.format("No binding for \"%s\"", var));
			}
		}
		
		Matcher quoted = quotedPattern.matcher(str);
		if(quoted.matches()) { str = quoted.group(1); }
		
		//System.err.println(String.format("%s -> %s\n\t(%s)", original, str, b.toString()));
		
		return str;
	}
}
