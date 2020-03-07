package com.fardoushlab.picstagram.request_models;


import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostRM implements Serializable {

    private long id;

    private List<String> postImages =  new ArrayList<>();
    private List<MultipartFile> multipartFiles = new ArrayList<>();

    private String postText;

    public PostRM() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<String> postImages) {
        this.postImages = postImages;
    }

    public List<MultipartFile> getMultipartFiles() {
        return multipartFiles;
    }

    public void setMultipartFiles(List<MultipartFile> multipartFiles) {
        this.multipartFiles = multipartFiles;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }


    @Override
    public String toString() {
        return "PostRM{" +
                "id=" + id +
                ", postImages=" + postImages +
                ", postText='" + postText + '\'' +
                '}';
    }
}
