package com.fardoushlab.picstagram.dtos;

import java.io.Serializable;

public class CommentDtoMinimal implements Serializable {

    private long id;
    private UserDtoMinimal woner;
    private String commentText;
    private String commentTime;

    public CommentDtoMinimal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDtoMinimal getWoner() {
        return woner;
    }

    public void setWoner(UserDtoMinimal woner) {
        this.woner = woner;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}
