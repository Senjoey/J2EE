package edu.nju.listeners;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class CounterListener
 *
 */
@WebListener
public class CounterListener implements ServletContextListener, ServletContextAttributeListener {
	int total;
	int loginTotal;
	int visitorTotal;
	String counterFilePath = "/Library/Tomcat9/webapps/J2EE-Lab/WebContent/counter.txt";
    /**
     * Default constructor. 
     */
    public CounterListener() {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent cse) {
    	try {
    		System.out.println("Reading Start"); 		
    		BufferedReader reader = new BufferedReader(new FileReader(counterFilePath));
    		ArrayList<String> lines = new ArrayList<String>();
    		String line;
    		while((line = reader.readLine()) != null) {
    			lines.add(line);
    		}
    		reader.close();
    		
    		String strOnlineTotal = lines.get(0);
    		String strLoginTotal = lines.get(1);
    		String strVisitorTotal = lines.get(2);
    		
    		total = Integer.parseInt(strOnlineTotal);
    		loginTotal = Integer.parseInt(strLoginTotal);
    		visitorTotal = Integer.parseInt(strVisitorTotal);
    		System.out.println("onlineTotal: " + total + "  loginTotal: " + loginTotal + "  visitorTotal: " + visitorTotal); 
    		}catch (Exception e) {
    		System.out.println(e.toString());
    	}
    	ServletContext servletContext= cse.getServletContext();
    	servletContext.setAttribute("total", Integer.toString(total));
    	servletContext.setAttribute("loginTotal", Integer.toString(loginTotal));
    	servletContext.setAttribute("visitorTotal", Integer.toString(visitorTotal));
    	System.out.println("Application initialized");
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0) {
    	System.out.println("ServletContextattribute added");
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent scae) {
    	System.out.println("ServletContextattribute replaced");
    	writeCounter(scae);
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0) {
    	System.out.println("ServletContextattribute removed");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	System.out.println("Application shut down");
    }
	
    synchronized void writeCounter(ServletContextAttributeEvent scae) {
    	ServletContext servletContext= scae.getServletContext();
    	total = Integer.parseInt((String) servletContext.getAttribute("total"));
    	loginTotal = Integer.parseInt((String) servletContext.getAttribute("loginTotal"));
    	visitorTotal = Integer.parseInt((String) servletContext.getAttribute("visitorTotal"));
    	try {
    		BufferedWriter writer = new BufferedWriter(new FileWriter(counterFilePath));
    		writer.write(Integer.toString(total)+"\n");
    		writer.write(Integer.toString(loginTotal)+"\n");
    		writer.write(Integer.toString(visitorTotal)+"\n");
    		writer.close();
    		System.out.println("Writing");
    	}catch (Exception e) {
    		System.out.println(e.toString());
    	}
    }
}
