package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

public class DataRegister {

    @SerializedName("message")
    String message;

    @SerializedName("errors")
    email email;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public com.baraasa.project.Model.email getEmail() {
        return email;
    }

    public void setEmail(com.baraasa.project.Model.email email) {
        this.email = email;
    }
}
