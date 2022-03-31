package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataBlog {

    @SerializedName("blog")
    ArrayList<BlogModel> blog;

    @SerializedName("message")
    String message;

    public ArrayList<BlogModel> getBlog() {
        return blog;
    }

    public void setBlog(ArrayList<BlogModel> blog) {
        this.blog = blog;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

