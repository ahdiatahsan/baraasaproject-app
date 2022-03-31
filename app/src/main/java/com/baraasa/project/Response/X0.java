package com.baraasa.project.Response;

import com.google.gson.annotations.SerializedName;

public class X0 {
    @SerializedName("token")
    String token;

    @SerializedName("user")
    User user;

    @SerializedName("role")
    String role;

    @SerializedName("0")
    int errorpass;

    public int getErrorpass() {
        return errorpass;
    }

    public void setErrorpass(int errorpass) {
        this.errorpass = errorpass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
