package com.fardoushlab.picstagram;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.File;

public class AppInitializer implements WebApplicationInitializer {

    private String TMP_FOLDER = "/tmp";
    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

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

        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        MultipartConfigElement element = new MultipartConfigElement(uploadDirectory.getAbsolutePath(), MAX_UPLOAD_SIZE,MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
        dynamic.setMultipartConfig(element);
    }
}
