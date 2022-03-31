package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

public class ResearchModel {
    @SerializedName("id")
    String id;

    @SerializedName("title")
    String title;

    @SerializedName("date_of_publish")
    String date_of_publish;

    @SerializedName("file")
    String file;

    @SerializedName("crated_at")
    String created_at;

    @SerializedName("updated_at")
    String updated_at;

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

    public String getDate_of_publish() {
        return date_of_publish;
    }

    public void setDate_of_publish(String date_of_publish) {
        this.date_of_publish = date_of_publish;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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
}
