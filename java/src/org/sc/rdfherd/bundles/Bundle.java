package org.sc.rdfherd.bundles;

import java.io.*;
import java.util.*;

public class Bundle {
	
	private File dir;
	private BundleConfig config;
	private ArrayList<File> dataFiles;

	public Bundle(File dir) throws IOException {
		if(!dir.exists()) { 
			throw new IllegalArgumentException(String.format("%s does not exist", 
					dir.getAbsolutePath()));
		}
		if(!dir.canRead()) { 
			throw new IllegalArgumentException(String.format("%s cannot be read", 
					dir.getAbsolutePath()));
		}
		if(!dir.isDirectory()) { 
			throw new IllegalArgumentException(String.format("%s is not a directory",
					dir.getAbsolutePath()));
		}
		
		this.dir = dir;
		
		config = new BundleConfig(new File(dir, "Config.pl"));
		
		dataFiles = new ArrayList<File>();
		
		dataFiles.addAll(Arrays.asList(dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String upper = name.toUpperCase();
				return (upper.endsWith(".rdf") || upper.endsWith(".owl") || 
					upper.endsWith(".n3") || upper.endsWith(".ttl")) && 
						new File(dir, name).canRead();
			} 
		})));
	}
	
	public File dir() { return dir; }
	public BundleConfig getConfig() { return config; }
	public Collection<File> getDataFiles() { return dataFiles; }
	
	public String getName() { return config.value("name"); }
	public Integer getVersion() { return config.value("version"); }
	public String getAuthority() { return config.value("authority"); }
	public String getGraph() { return config.value("graph"); }
	public String getURLRef() { return config.value("urlref"); }
	
	public boolean shouldClearVersion() { 
		Integer noNeed = config.value("no_need_to_clear_version"); 
		return noNeed == 0;
	}
	
	// Static Helper Methods ///////////////////////////////////////////////////////
	
	public static boolean isBundle(File dir) {
		if(!dir.exists()) { return false; }
		if(!dir.canRead()) { return false; }
		if(!dir.isDirectory()) { return false; }
		File config = new File(dir, "Config.pl");
		
		if(!config.exists()) { return false; }
		if(!config.canRead()) { return false; }
		
		return true;
	}
	
	public static Bundle[] findBundles(File bundleDir) throws IOException {
		
		File[] fs = bundleDir.listFiles(new FileFilter() {
			public boolean accept(File dir) {
				return isBundle(dir);
			} 
		});
		Bundle[] bundles = new Bundle[fs.length];
		for(int i = 0; i < fs.length; i++) { 
			bundles[i] = new Bundle(fs[i]);
		}
		return bundles;
	}
}
