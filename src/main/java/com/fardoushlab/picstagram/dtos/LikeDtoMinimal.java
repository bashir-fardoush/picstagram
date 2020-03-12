package com.fardoushlab.picstagram.dtos;

import java.io.Serializable;

public class LikeDtoMinimal implements Serializable {

    private long id;

    private long postId;

    private long wonerId;

    public LikeDtoMinimal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getWonerId() {
        return wonerId;
    }

    public void setWonerId(long wonerId) {
        this.wonerId = wonerId;
    }
}
