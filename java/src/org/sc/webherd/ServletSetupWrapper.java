package org.sc.webherd;

import javax.servlet.http.HttpServlet;

public class ServletSetupWrapper {

	public String name;
	public HttpServlet servlet;
	public String path;
	
	public ServletSetupWrapper(String n, HttpServlet s, String p) {
		name = n;
		servlet = s;
		path = p;
	}
}
