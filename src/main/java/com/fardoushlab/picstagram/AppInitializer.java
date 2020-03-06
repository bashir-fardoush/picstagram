package com.fardoushlab.picstagram;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(AppConfig.class);
        ac.refresh();

        servletContext.addListener(new ContextLoaderListener(ac));
        AnnotationConfigWebApplicationContext servletCtxConfig = new AnnotationConfigWebApplicationContext();

        servletCtxConfig.register(AppServletConfig.class);

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("appservlet", new DispatcherServlet(servletCtxConfig));
        dynamic.setLoadOnStartup(1);

        dynamic.addMapping("/");
    }
}
