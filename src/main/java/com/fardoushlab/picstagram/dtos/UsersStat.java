package com.fardoushlab.picstagram.dtos;

import java.io.Serializable;

public class UsersStat implements Serializable {
    private int totalPost;
    private int totalFollower;
    private int totalFollowing;

    public UsersStat() {

    }

    public int getTotalPost() {
        return totalPost;
    }

    public void setTotalPost(int totalPost) {
        this.totalPost = totalPost;
    }

    public int getTotalFollower() {
        return totalFollower;
    }

    public void setTotalFollower(int totalFollower) {
        this.totalFollower = totalFollower;
    }

    public int getTotalFollowing() {
        return totalFollowing;
    }

    public void setTotalFollowing(int totalFollowing) {
        this.totalFollowing = totalFollowing;
    }
}
