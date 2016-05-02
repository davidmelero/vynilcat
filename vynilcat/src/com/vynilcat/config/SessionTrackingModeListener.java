package com.vynilcat.config;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.SessionTrackingMode;
import javax.servlet.annotation.WebListener;

/**
 * This Listener sets the tracking modes used by the servletContext
 */
@WebListener(value = "This listener sets the session tracking modes")
public class SessionTrackingModeListener implements ServletContextListener {
 
    // Public constructor is required by servlet spec
 
    public SessionTrackingModeListener() {
    	
    }
    
    public void contextInitialized(ServletContextEvent sce) {
        Set<SessionTrackingMode> modes = new HashSet<SessionTrackingMode>();
        // modes.add(SessionTrackingMode.URL); // thats the default behaviour!
        modes.add(SessionTrackingMode.COOKIE);
//        modes.add(SessionTrackingMode.SSL); // this works only with client certs.       
        sce.getServletContext().setSessionTrackingModes(modes);
    }
 
    public void contextDestroyed(ServletContextEvent sce) {
    	
    }
 
}
