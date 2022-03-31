package com.baraasa.project.Model;

import com.baraasa.project.Response.User;
import com.google.gson.annotations.SerializedName;

public class ThreadModel {
    @SerializedName("id")
    String id;

    @SerializedName("title")
    String title;

    @SerializedName("slug")
    String slug;

    @SerializedName("body")
    String body;

    @SerializedName("publication_status")
    String publication_status;

    @SerializedName("user_id")
    String user_id;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("updated_at")
    String updated_at;

    @SerializedName("user")
    User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPublication_status() {
        return publication_status;
    }

    public void setPublication_status(String publication_status) {
        this.publication_status = publication_status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
