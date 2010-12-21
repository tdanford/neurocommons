package org.sc.grammars;

import java.util.*;
import java.io.*;

import org.sc.grammars.clauses.Bindings;

public class FileTable {

	public static void main(String[] args) { 
		
	}
	
	public String[] headers;
	public Map<String,Integer> headerIndices;
	public ArrayList<String[]> rows;
	
	public FileTable(File f) throws IOException { 
		BufferedReader br = new BufferedReader(new FileReader(f));

		headerIndices = new TreeMap<String,Integer>();
		rows = new ArrayList<String[]>();
		
		String line = br.readLine();
		String[] array = line.split("\t");
		headers = array;
		
		for(int i = 0; i < headers.length; i++) { 
			headerIndices.put(headers[i], i);
		}
		
		while((line = br.readLine()) != null) { 
			array = line.split("\t");
			rows.add(array);
		}
		
		br.close();
	}
	
	public int width() { return headers.length; }
	public int size() { return rows.size(); }
	
	public String[] row(int r) { return rows.get(r); }
	
	public Bindings rowBindings(int r) { 
		String[] bs = new String[headers.length*2];
		for(int i = 0; i < headers.length; i++) { 
			bs[i*2] = headers[i];
			bs[i*2+1] = rows.get(r)[i];
		}
		return new Bindings(bs);
	}
}
