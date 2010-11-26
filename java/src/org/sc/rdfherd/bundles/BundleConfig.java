package org.sc.rdfherd.bundles;

import java.util.*;
import java.util.regex.*;
import java.io.*;

public class BundleConfig {

	private File file;
	private Map<String,Object> values;

	private Pattern stringPattern = Pattern.compile("^\\s*([\\w\\d_]+)\\s*=>\\s*\"(.*)\"\\s*$");
	private Pattern intPattern = Pattern.compile("^\\s*([\\w\\d_]+)\\s*=>\\s*(\\d+)\\s*$");

	public BundleConfig(File f) throws IOException { 
		if(!f.exists()) { 
			throw new IllegalArgumentException(String.format("%s does not exist.", f.getAbsolutePath()));
		}
		if(!f.canRead()) { 			
			throw new IllegalArgumentException(String.format("%s is not readable.", f.getAbsolutePath()));
		}
		
		values = new TreeMap<String,Object>();
		file = f;
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = reader.readLine();
		if(line != null && line.trim().equals("{")) { 
			while((line = reader.readLine()) != null) { 
				Matcher stringM = stringPattern.matcher(line);
				Matcher intM = intPattern.matcher(line);
				
				if(stringM.matches()) { 
					String key = stringM.group(1), value = stringM.group(2);
					values.put(key, value);
				
				} else if (intM.matches()) { 
					String key = intM.group(1);
					Integer value = Integer.parseInt(intM.group(2));
					values.put(key, value);

				} else if (line.trim().equals("}")) { 
					break;
				
				} else { 
					throw new IllegalArgumentException(String.format(
							"Unrecognized line \"%s\" in file %s", 
							line, file.getAbsolutePath()));
				}
			}
		} else { 
			throw new IllegalArgumentException(String.format("Config.pl in %s must start with '{'",
					f.getParentFile().getAbsolutePath()));
		}
		reader.close();
	}
	
	public File file() { return file; }
	
	public <T> T value(String key) { return (T)values.get(key); }
	
	public boolean hasKey(String key) { return values.containsKey(key); }
	
	public String[] keys() { return values.keySet().toArray(new String[0]); } 
}
