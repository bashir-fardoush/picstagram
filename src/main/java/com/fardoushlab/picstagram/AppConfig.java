package com.fardoushlab.picstagram;

import com.fardoushlab.picstagram.config.persistancy.HibernateConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@ComponentScan(basePackages = {"com.fardoushlab.picstagram.services",
        "com.fardoushlab.picstagram.config.persistancy",
        "com.fardoushlab.picstagram.config.security"})
public class AppConfig {

    @Bean
    HibernateConfig hibernateConfig(){
        return new HibernateConfig();
    }

    @Bean
    public PasswordEncoder PasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
