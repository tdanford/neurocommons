package org.sc.rdfherd.bundles;

import java.util.*;
import java.io.*;

public class MakeBundle {

	private String bundleName;
	private File parentDir;
	
	private ConfigTemplater configTemplater;
	
	public MakeBundle(File dir, String bundleName) { 
		this.parentDir = dir;
		this.bundleName = bundleName;
		
		configTemplater = new ConfigTemplater(bundleName);
	}
	
	public void makeBundle() throws IOException { 
		File bundleDir = new File(parentDir, bundleName);
		bundleDir.mkdirs();
		configTemplater.createTemplate(bundleDir);
	}
}

