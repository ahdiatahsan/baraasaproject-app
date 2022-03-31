package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

public class DataPassword {

    @SerializedName("message")
    String message;

    @SerializedName("0")
    int x0;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
