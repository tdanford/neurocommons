package org.sc.grammars;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.regex.*;

public class Trie<E> {
	
	public static void main(String[] args) { 
		Trie<Integer> tree = new Trie<Integer>();
		
		String[] keys = new String[]  { 
			"aaaab",
			"aaaabb",
			"aabbaa",
			"aaabbaa",
		};

		for(int i = 0; i < keys.length; i++) {
			//System.out.println(String.format("Inserting \"%s\"", keys[i]));
			tree.insert(keys[i], i);
			//System.out.println(tree);
		}
		
		System.out.println(tree);
		
		System.out.println(tree.findMatch("aaaabb").text());
		System.out.println(tree.longestPrefix("aaaaaaaaaaaaaaaaaccdd"));
	}

	private Trie<E> parent;
	private String nodeText;
	private Map<Character,Trie<E>> subTries;
	private Set<E> values;

	public Trie() { 
		this("");
	}
	
	public Trie(String txt) {
		parent = null;
		nodeText = txt;
		subTries = new TreeMap<Character,Trie<E>>();
		values = new HashSet<E>();		
	}
	
	public Trie(Trie<E> p, String txt) { 
		this(txt);
		parent = p;
	}
	
	public Trie(Trie<E> p, String txt, E k) {
		this(txt);
		parent = p;
		values.add(k);
	}
	
	public Set<E> values() { return values; }
	
	public Set<E> allValues() { 
		HashSet<E> ks = new HashSet<E>(values);
		for(Character c : subTries.keySet()) { 
			ks.addAll(subTries.get(c).allValues());
		}
		return ks;
	}	
	
	public Trie<E> findMatch(String search) { return findMatch(search, 0); }
	
	private Trie<E> findMatch(String search, int offset) {
		int matchLength = findMatchLength(nodeText, 0, search, offset);
		
		if(matchLength == nodeText.length()) { 
			if((offset+matchLength) == search.length()) {  
				return this;
			} else { 
				char next = search.charAt(offset+matchLength);
				if(subTries.containsKey(next)) {
					return subTries.get(next).findMatch(search, offset+matchLength);
				} else { 
					return null;
				}
			}
		} else { 
			return null;
		}
	}
	
	public boolean contains(String search) { 
		return findMatch(search) != null;
	}
	
	public String longestPrefix(String search) { 
		Trie<E> prefixMatch = findPrefixMatch(search);
		if(prefixMatch==null) { return ""; }
		return findLongestPrefix(prefixMatch.text(), search);
	}
	
	public Set<E> hasLongestPrefix(String search) { 
		Trie<E> prefixMatch = findPrefixMatch(search);
		if(prefixMatch == null) { 
			return new HashSet<E>();
		} else { 
			return prefixMatch.allValues();
		}
	}
	
	public Trie<E> findPrefixMatch(String search) { return findPrefixMatch(search, 0); }
	
	private Trie<E> findPrefixMatch(String search, int offset) {
		int matchLength = findMatchLength(nodeText, 0, search, offset);
		
		if(matchLength == nodeText.length()) { 
			if((offset+matchLength) == search.length()) {  
				return this;
			} else { 
				char next = search.charAt(offset+matchLength);
				if(subTries.containsKey(next)) {
					return subTries.get(next).findPrefixMatch(search, offset+matchLength);
				} else { 
					return this;
				}
			}
		} else { 
			return this;
		}
	}
	
	public String nodeText() { return nodeText; }
	
	public String text() { 
		if(parent != null) { 
			return parent.text() + nodeText;
		} else { 
			return nodeText;
		}
	}
	
	public void insert(String key, E value) { 
		insert(key, 0, value);
	}
	
	private void insert(String key, int offset, E value) { 
		String keyText = key.substring(offset, key.length());
		String matching = findLongestPrefix(nodeText, keyText);
		
		boolean fullKeyMatch = matching.length() == keyText.length();
		boolean fullNodeMatch = matching.length() == nodeText.length();
		
		if(fullKeyMatch && fullNodeMatch) {
			
			// if the full length of the key matches the full length of the 
			// node text, then this is the node where the value should live. 
			// add a value to the local set, and we're done.
			values.add(value);
			
		} else if (fullKeyMatch) {

			// if the full key matches only a part of the current node, then 
			// we need to splice in a new node between this one and its parent.
			Trie<E> newParent = new Trie<E>(parent, matching, value);
			parent.subTries.put(matching.charAt(0), newParent);
			parent = newParent;
			nodeText = nodeText.substring(matching.length(), nodeText.length());
			
		} else if (fullNodeMatch) {
	
			// if we've exhausted the length of the text within this node, but not 
			// the length of the text in the key, we look for the sub-trie with the 
			// next matching letter...
			char c = keyText.charAt(matching.length());
			if(subTries.containsKey(c)) {
				
				// ... if it exists, we insert the rest of the key+value there.
				subTries.get(c).insert(key, offset+matching.length(), value);
				
			} else { 
				// .. if it doesn't, we insert it and we're done.
				subTries.put(c, new Trie<E>(this, keyText.substring(matching.length(), keyText.length()), value));
			}
			
		} else { 
			// if the match doesn't exhaust *either* the key, *or* the text of the current node,
			// then we need to splice in a new parent node (with the text of the match), and then 
			// add two new children to that parent.

			Trie<E> newParent = new Trie<E>(parent, matching);
			parent.subTries.put(matching.charAt(0), newParent);
			parent = newParent;
			
			String nodeRemainder = nodeText.substring(matching.length(), nodeText.length());
			String keyRemainder = keyText.substring(matching.length(), keyText.length());
			//System.out.println(String.format("NodeRemainder: %s", nodeRemainder));
			//System.out.println(String.format("KeyRemainder: %s", keyRemainder));
			
			nodeText = nodeRemainder;
			Trie<E> sibling = new Trie<E>(parent, keyRemainder, value);
			
			parent.subTries.put(nodeRemainder.charAt(0), this);
			parent.subTries.put(keyRemainder.charAt(0), sibling);
		}
	}
	
	private static int findMatchLength(String p1, int o1, String p2, int o2) { 
		int start = o2;
		for(; o2 < p2.length() && o1 < p1.length() && p1.charAt(o1)==p2.charAt(o2); o2++, o1++) 
			{}
		return o2-start;
	}
	
	private static String findLongestPrefix(String s1, String s2) { 
		int len = 0;
		while(len < Math.min(s1.length(), s2.length()) && s1.charAt(len) == s2.charAt(len)) { 
			len++;
		}
		return s1.substring(0, len);
	}
	
	public String toString() { 
		StringWriter writer = new StringWriter();
		printTree(new PrintWriter(writer));
		return writer.toString();
	}
	
	public void printTree(PrintWriter out) { printTree(out, 0); }
	
	private void printTree(PrintWriter out, int offset) { 
		for(int i = 0; i < offset; i++) { out.print(" "); }
		if(parent == null) { out.print("*"); }
		out.print(nodeText);
		if(!values.isEmpty()) { 
			out.print(" " + values.toString());
		}
		out.println();
		for(char n : subTries.keySet()) { 
			subTries.get(n).printTree(out, parent != null ? offset+nodeText.length() : 1);
		}
	}
}

