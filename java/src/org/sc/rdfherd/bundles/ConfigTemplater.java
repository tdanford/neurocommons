package org.sc.rdfherd.bundles;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/*
{ 
	class => "RDF Bundle",
	name => "$(PACKAGE)",
	version => 0,
	authority => "uninitialized bundle authority",
	no_need_to_clear_version => 0,
	graph => "http://purl.org/science/graph/$(PACKAGE)",
}
 */
public class ConfigTemplater {
	
	private Map<String,Object> values;
	
	public ConfigTemplater(String name) { 
		this();
		values.put("name", name);
		try {
			values.put("graph", String.format("http://purl.org/science/graph/%s", 
					URLEncoder.encode(name, "UTF-8")));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(System.err);
		}
	}
	
	public ConfigTemplater() { 
		values = new LinkedHashMap<String,Object>();
		values.put("class", "RDF Bundle");
		values.put("version", 0);
		values.put("authority", "uninitialized bundle authority");
		values.put("no_need_to_clear_version", 0);
	}

	public void createTemplate(File dir) throws IOException { 
		File output = new File(dir, "Config.pl");
		PrintWriter writer = new PrintWriter(new FileWriter(output));
		writer.println("{");
		for(String key : values.keySet()) { 
			printValue(writer, key, values.get(key));
		}
		writer.println("}");
	}
	
	public static void printValue(PrintWriter writer, String key, Object value) { 
		if(value instanceof String) { 
			String strValue = (String)value;
			writer.println(String.format("\t%s => \"%s\"", key, strValue.replaceAll("\"", "\\\"")));
			
		} else if (value instanceof Integer) { 
			Integer intValue = (Integer)value;
			writer.println(String.format("\t%s => %d", key, intValue));
			
		} else { 
			throw new IllegalArgumentException(String.format(
					"Unknown/unsupported value class %s", value.getClass().getCanonicalName()));
		}
	}
}
