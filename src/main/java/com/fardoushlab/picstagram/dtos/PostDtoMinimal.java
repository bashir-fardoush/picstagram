package com.fardoushlab.picstagram.dtos;

import com.fardoushlab.picstagram.models.Like;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostDtoMinimal  implements Serializable {

    private long id;
    private String postText;

    private UserDtoMinimal woner;
    private List<String> postImages =  new ArrayList<>();
    private List<CommentDtoMinimal> comments  = new ArrayList<>();
    /*no need while show posts*/
    //private List<LikeDtoMinimal> likes = new ArrayList<>();

    private long totalLike;
    private long totalComment;
    private boolean isLiked;
    private String postTimeString;

    /*Keep in consideration*/
    //private LocalDateTime commentTime;


    public PostDtoMinimal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public UserDtoMinimal getWoner() {
        return woner;
    }

    public void setWoner(UserDtoMinimal woner) {
        this.woner = woner;
    }

    public List<String> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<String> postImages) {
        this.postImages = postImages;
    }

    public List<CommentDtoMinimal> getComments() {
        return comments;
    }

    public void setComments(List<CommentDtoMinimal> comments) {
        this.comments = comments;
    }

    public long getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(long totalLike) {
        this.totalLike = totalLike;
    }

    public long getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(long totalComment) {
        this.totalComment = totalComment;
    }


    public String getPostTimeString() {
        return postTimeString;
    }

    public void setPostTimeString(String postTimeString) {
        this.postTimeString = postTimeString;
    }

    public void addComment(CommentDtoMinimal comment){
        this.comments.add(comment);
    }

    public boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
