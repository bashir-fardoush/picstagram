package com.fardoushlab.picstagram.dtos;

import java.io.Serializable;

public class UserDtoMinimal implements Serializable {
    private long id;
    private String avatar;
    private String fullName;
    private String username;

    public UserDtoMinimal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
