package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

public class email {

    @SerializedName("email")
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
