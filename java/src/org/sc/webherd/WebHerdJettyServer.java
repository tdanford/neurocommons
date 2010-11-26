package org.sc.webherd;

import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlet.ServletMapping;
import org.eclipse.jetty.util.log.Log;

public class WebHerdJettyServer {
	
	public static String HOSTNAME = "localhost";
	public static int PORT = 30301;
	
	static { 
		try {
			HOSTNAME = java.net.Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.err.println(String.format("Unable to find HOSTNAME: %s", e.getMessage()));
		}
	}
	
	public static String getURLPrefix() { 
		return String.format("http://%s:%d/", HOSTNAME, PORT);
	}

	public static WebHerdJettyServer start(WebHerdProperties props) throws Exception {
		int port = props.getPort();
		PORT = port;
		
		String resourceBase = props.getResourceBase();
		
		WebHerdJettyServer js = new WebHerdJettyServer(port, resourceBase);
		
		//js.addServlet("jsp", new JspServlet(), "*.jsp");
		
		for(ServletSetupWrapper wrap : props.getServlets()) {
			js.addServlet(wrap.name, wrap.servlet, wrap.path);
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownRunnable(js)));
		js.start();
		
		return js;
	}
	
	private static class ShutdownRunnable implements Runnable { 
		private WebHerdJettyServer broker;
		
		public ShutdownRunnable(WebHerdJettyServer bs) { 
			broker = bs;
		}
		
		public void run() { 
			System.out.println("Shutdown hook running..."); System.out.flush();
			broker.stop();
		}
	}
	
	private Server server;
	private ServletContextHandler context;
	
	public WebHerdJettyServer(int port, String resourceBase) { 
		server = new Server(port);
        Map<String,String> params = new TreeMap<String,String>();
        params.put("org.apache.jasper.Constants.SERVLET_CLASSPATH", "org.sc.webherd.jsps");
        params.put("org.apache.jasper.servlet.JspServlet.classpath", "org.sc.webherd.jsps");
                
        context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase(resourceBase);
        context.setInitParams(params);
        
        ResourceHandler recs = new ResourceHandler();
        recs.setResourceBase(resourceBase + "/static");
        recs.setCacheControl("max-age=3600");
        
        RequestLogHandler logHandler = new RequestLogHandler();
        
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { context, recs  });
        
        logHandler.setHandler(handlers);
        
        server.setHandler(logHandler);
        
        //server.setHandler(handlers);
        
        /*
        // Taken from example: 
        // http://wiki.eclipse.org/Jetty/Tutorial/RequestLog
        NCSARequestLog requestLog = new NCSARequestLog("./logs/broker-yyyy_mm_dd.request.log");
        requestLog.setRetainDays(90);
        requestLog.setAppend(true);
        requestLog.setExtended(false);
        requestLog.setLogTimeZone("GMT");
        logHandler.setRequestLog(requestLog);
        */
	}
	
	public void addServlet(String servletName, Servlet servlet, String firstMapping, String... mappings) { 
		ServletHolder holder = new ServletHolder(servlet);
		holder.setName(servletName);
	
		context.addServlet(holder, firstMapping);
		
		if(mappings.length > 0) { 
			ServletMapping servletMapping = new ServletMapping();
			servletMapping.setServletName(servletName);
			servletMapping.setPathSpecs(mappings);
			context.getServletHandler().addServletMapping(servletMapping);
		}
	}
	
	public void start() throws Exception { 
		server.start();
		server.join();
	}
	
	public void stop() { 
		try {
			if(server.isStarted()) { 
				server.stop();
			} else { 
				System.out.println("Ignoring stop on unstarted server.");
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}