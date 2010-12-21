package org.sc.grammars.clauses;

import java.util.*;
import java.util.regex.*;

import org.sc.grammars.PrefixSet;
import org.sc.grammars.QuotedString;

public class Bindings {

	private Bindings parent;
	private Map<String,TreeSet<String>> values;
	private PrefixSet set;
	
	public Bindings(String... vs) { 
		this(null, vs);
	}
	
	public Bindings(QuotedString str, String value) { 
		Pattern p = str.pattern();
		Matcher m = p.matcher(value);
		if(m.find()) { 
			parent = null;
			values = new TreeMap<String,TreeSet<String>>();
			for(int i = 0; i < str.length(); i++) { 
				String var = str.var(i);
				String val = m.group(i+1);
				bind(var, val);
			}
		} else { 
			throw new IllegalArgumentException(str + "," + value);
		}
		set = PrefixSet.createStandardPrefixSet();
	}
	
	public Bindings(Bindings b, String... vs) {
		if(!(vs.length % 2 == 0)) { throw new IllegalArgumentException(String.valueOf(vs.length)); }
		parent = b;
		if(parent != null) { set = parent.set; }
		values = new TreeMap<String,TreeSet<String>>();
		for(int i = 0 ; i < vs.length; i += 2) {
			bind(vs[i], vs[i+1]);
		}
		set = PrefixSet.createStandardPrefixSet();
	}

	public PrefixSet getPrefixSet() {
		return set;
	}

	public void bind(String key, String value) { 
		if(!values.containsKey(key)) { 
			values.put(key, new TreeSet<String>());
		}
		values.get(key).add(value);
	}
	
	public String lookup(String key) { 
		if(values.containsKey(key)) { return values.get(key).first(); } 
		else if(parent != null) { return parent.lookup(key); } 
		else { return null; }
	}
	
	public String[] lookupAllValues(String key) { 
		if(values.containsKey(key)) { 
			return values.get(key).toArray(new String[0]);
		} else if (parent != null) { 
			return parent.lookupAllValues(key);
		} else { 
			return new String[0];
		}
	}
	
	public boolean isMultiple(String key) { 
		if(values.containsKey(key)) { 
			return values.get(key).size() > 1;
		} else if (parent != null) { 
			return parent.isMultiple(key);
		} else { 
			return false;
		}
	}
	
	public boolean hasValue(String key) { 
		return values.containsKey(key) || (parent != null && parent.hasValue(key));
	}
	
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		String[] keys = keys();
		for(String k : keys) { 
			String v = isMultiple(k) ? lookupAllValues(k).toString() : lookup(k);
			if(sb.length() > 0) { sb.append("|"); }
			sb.append(String.format("%s=%s", k, v));
		}
		return sb.toString();
	}
	
	public String[] keys() { 
		TreeSet<String> ks = new TreeSet<String>(values.keySet());
		if(parent != null) { ks.addAll(Arrays.asList(parent.keys())); }
		return ks.toArray(new String[0]);
	}
	
	public Bindings parent() { return parent; }

}
