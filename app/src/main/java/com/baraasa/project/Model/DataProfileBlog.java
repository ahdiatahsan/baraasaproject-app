package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataProfileBlog {
    @SerializedName("blog")
    ArrayList<BlogModel> blog;


    public ArrayList<BlogModel> getBlog() {
        return blog;
    }

    public void setBlog(ArrayList<BlogModel> blog) {
        this.blog = blog;
    }
}
