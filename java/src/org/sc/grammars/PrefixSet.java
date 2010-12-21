package org.sc.grammars;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrefixSet { 
	
	public static PrefixSet createStandardPrefixSet() { 
		PrefixSet set = new PrefixSet();
		
		set.addPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		set.addPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		set.addPrefix("foaf", "http://xmlns.com/foaf/0.1/");
		set.addPrefix("dc", "http://purl.org/dc/elements/1.1/");
		set.addPrefix("owl", "http://www.w3.org/2002/07/owl#");
		set.addPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
		
		return set;
	}
	
	public static Pattern prefixed = Pattern.compile("([^:]+):(.+)");
	
	private Map<String,String> key2Prefix;
	private Trie<String> prefixTree;
	
	public PrefixSet() { 
		key2Prefix = new TreeMap<String,String>();
		prefixTree = new Trie<String>();
	}
	
	public void addPrefix(String key, String prefix) { 
		key2Prefix.put(key, prefix);
		prefixTree.insert(prefix, key);
	}
	
	public String expand(String contracted) { 
		Matcher m = prefixed.matcher(contracted);
		if(!m.matches()) { return contracted; }
		String key = m.group(1), suffix = m.group(2);
		if(key2Prefix.containsKey(key)) { 
			return key2Prefix.get(key) + suffix;
		} else { 
			return contracted;
		}
	}
	
	public String contract(String expanded) { 
		Trie<String> prefixMatch = prefixTree.findPrefixMatch(expanded);
		if(prefixMatch==null) { return expanded; }
		Set<String> keys = prefixMatch.allValues();
		
		Iterator<String> keyitr = keys.iterator();
		while(keyitr.hasNext()) { 
			String key = keyitr.next();
			String prefix = key2Prefix.get(key);
			if(expanded.startsWith(prefix)) { 
				return String.format("%s:%s",
						key, 
						expanded.substring(prefix.length(), expanded.length()));
			}
		}
		return expanded;
	}
	
	public static int commonPrefixLength(String s1, String s2) { 
		int i = 0;
		while(i < s1.length() && i < s2.length() && s1.charAt(i) == s2.charAt(i)) { 
			i++;
		}
		return i;
	}
}
