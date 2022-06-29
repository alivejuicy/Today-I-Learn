package org.example;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce){
        System.out.println("Context Initialized");
        sce.getServletContext().setAttribute("name","kwak");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("context destroyed");
    }
}
