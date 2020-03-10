package com.fardoushlab.picstagram.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "avatar", updatable = true, nullable = true)
    private String avatar;

    @Column(name = "bio", updatable = true, nullable = true)
    private String bio;

    @Column(name = "fullName", updatable = true, nullable = false)
    private String fullName;

    @Column(name = "email", updatable = true, nullable = true)
    private String email;

    @Column(name = "password", updatable = true, nullable = false)
    private String password;

    @Column(name = "username", updatable = true, nullable = false)
    private String username;

    @Column(name = "phone", updatable = true, nullable = true)
    private String phone;

    @Column(name = "createdAt", updatable = true, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", updatable = true, nullable = true)
    private LocalDateTime updatedAt;

    @ElementCollection(fetch=FetchType.LAZY)
    @CollectionTable(name = "following")
    private List<Long> following =  new ArrayList<>();

    @ElementCollection(fetch=FetchType.LAZY)
    @CollectionTable(name = "followed_by")
    private List<Long> followedBy =  new ArrayList<>();

    public User() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Long> getFollowing() {
        return following;
    }

    public void setFollowing(List<Long> following) {
        this.following = following;
    }

    public List<Long> getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(List<Long> followedBy) {
        this.followedBy = followedBy;
    }
}
