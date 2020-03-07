package com.fardoushlab.picstagram;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.fardoushlab.picstagram.controllers"})
public class AppServletConfig  implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        registry.jsp("/WEB-INF/views/",".jsp");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
        registry.addResourceHandler("/font/**").addResourceLocations("/WEB-INF/resources/font/");
        registry.addResourceHandler("/images/avatar/**").addResourceLocations("/WEB-INF/resources/images/avatar/");
        registry.addResourceHandler("/images/post_images/**").addResourceLocations("file:///"+"D:/project/picstagram/post_images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");


    }
}
