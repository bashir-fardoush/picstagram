package com.fardoushlab.picstagram.request_models;

import com.fardoushlab.picstagram.util.ValidationConstraints;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

public class UserRM {


    private long id;
    private String avatar;
    private String bio;
    private String fullName;

    @Pattern(regexp = ValidationConstraints.EMAIL_PATTERN, message = "Enter valid email address")
    private String email;

    @Pattern(regexp = ValidationConstraints.PASSWORD_PATTERN, message = "Enter valid password")
    private String password;

    private String username;
    private String phone;

    public UserRM() {
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserRM{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", bio='" + bio + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
