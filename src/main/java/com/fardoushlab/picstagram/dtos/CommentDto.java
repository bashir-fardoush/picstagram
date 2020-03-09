package com.fardoushlab.picstagram.dtos;

import com.fardoushlab.picstagram.models.Post;
import com.fardoushlab.picstagram.models.User;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CommentDto implements Serializable {

    private long id;
    private Post post;
    private User woner;
    private String commentText;
    private LocalDateTime commentTime;

    public CommentDto() {
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }
}
