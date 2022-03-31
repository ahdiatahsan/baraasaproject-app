package com.baraasa.project.Model;

import com.baraasa.project.Response.User;
import com.google.gson.annotations.SerializedName;

public class KomentarModel {
    @SerializedName("id")
    String id;

    @SerializedName("thread")
    ThreadModel threadModel;

    @SerializedName("body")
    String body;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("user")
    User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ThreadModel getThreadModel() {
        return threadModel;
    }

    public void setThreadModel(ThreadModel threadModel) {
        this.threadModel = threadModel;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
