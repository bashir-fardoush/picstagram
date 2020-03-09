package com.fardoushlab.picstagram.request_models;

import com.fardoushlab.picstagram.models.Post;
import com.fardoushlab.picstagram.models.User;

import java.io.Serializable;

public class LikeRM implements Serializable {

    private long id;
    private Post post;
    private User woner;

    public LikeRM() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getWoner() {
        return woner;
    }

    public void setWoner(User woner) {
        this.woner = woner;
    }
}
