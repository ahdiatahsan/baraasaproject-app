package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataKomenList {
    @SerializedName("comment")
    ArrayList <KomentarModel> comment;

    @SerializedName("message")
    String message;


    public ArrayList<KomentarModel> getComment() {
        return comment;
    }

    public void setComment(ArrayList<KomentarModel> comment) {
        this.comment = comment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
