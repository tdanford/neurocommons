package org.sc.webherd;

import java.io.IOException;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sc.rdfherd.bundles.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Timothy Danford
 */
public class BundleServlet extends HttpServlet {
	
	private Bundle bundle;
	private String graph;
	private Logger logger;
	
	public BundleServlet(Bundle bundle) { 
		this(bundle, LoggerFactory.getLogger("AdaptiveMatchingServlet"));
	}
	
	public BundleServlet(Bundle b, Logger logging) {
		bundle = b;
		graph = bundle.getGraph();
		logger = logging;
	}
	
	public void init() throws ServletException { 
		super.init();
		logger.info("AdaptiveMatchingServlet initialized.");
	}
	
	public void destroy() { 
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");
		if(text == null) { 
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No 'text' parameter supplied.");
			return;
		}

		String context = request.getParameter("context");
		if(context == null) { 
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No 'context' parameter supplied.");
			return;
		}
		
		text = URLDecoder.decode(text, "UTF-8");
		context = URLDecoder.decode(context, "UTF-8");

		logger.info(String.format("GET text=\"%s\", context=\"%s\"", text, context));

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text");
		PrintWriter pw = response.getWriter();
	}
}
