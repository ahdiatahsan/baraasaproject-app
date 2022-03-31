package com.baraasa.project.Response;

import com.google.gson.annotations.SerializedName;

public class LogOut {
    @SerializedName("token")
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
