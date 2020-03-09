package com.fardoushlab.picstagram.request_models;


import com.fardoushlab.picstagram.models.Like;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostRM implements Serializable {

    private long id;

    private List<String> postImages =  new ArrayList<>();
    private List<MultipartFile> multipartFiles = new ArrayList<>();
    private List<CommentRM> commentRMS = new ArrayList<>();
    private List<LikeRM> likeList = new ArrayList<>();

    private String postText;
    private long totalLike;
    private long totalComment;
    private long isLiked;

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

    public long getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(long isLiked) {
        this.isLiked = isLiked;
    }

    public List<CommentRM> getCommentRMS() {
        return commentRMS;
    }

    public void setCommentRMS(List<CommentRM> commentRMS) {
        this.commentRMS = commentRMS;
    }

    public List<LikeRM> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<LikeRM> likeList) {
        this.likeList = likeList;
    }

    @Override
    public String toString() {
        return "PostRM{" +
                "id=" + id +
                ", postImages=" + postImages +
                ", postText='" + postText + '\'' +
                ", totalLike=" + totalLike +
                ", totalComment=" + totalComment +
                '}';
    }
}
