package org.sc.webherd;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.servlet.http.HttpServlet;

public class WebHerdProperties {  

	private ResourceBundle bundle;
	private Map<String,String> properties;
	
	public WebHerdProperties() { 
		this("org.sc.webherd.default");
	}

	public WebHerdProperties(String qualifiedName) { 
		bundle = ResourceBundle.getBundle(qualifiedName);
		properties = new TreeMap<String,String>();
		Enumeration<String> keys = bundle.getKeys();
		while(keys.hasMoreElements()) { 
			String key = keys.nextElement();
			String value = bundle.getString(key);
			properties.put(key, value);
		}
	}
	
	public Iterator<String> keys() { return properties.keySet().iterator(); }
	public String getStringValue(String k) { return properties.get(k); }
	public boolean hasKey(String k) { return properties.containsKey(k); }
	
	public String getResourceBase() { return getStringValue("resourceBase"); }
	
	public int getPort() { return Integer.parseInt(getStringValue("port")); }
	
	public ServletSetupWrapper[] getServlets() { 
		return new ServletSetupWrapper[] {};
	}
	
}
