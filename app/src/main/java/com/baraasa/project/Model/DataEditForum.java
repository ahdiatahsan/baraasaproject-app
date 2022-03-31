package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

public class DataEditForum {
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
