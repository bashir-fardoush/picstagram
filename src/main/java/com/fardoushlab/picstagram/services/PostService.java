package com.fardoushlab.picstagram.services;

import com.fardoushlab.picstagram.config.persistancy.HibernateConfig;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private HibernateConfig hibernateConfig;

    public PostService(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }


}
