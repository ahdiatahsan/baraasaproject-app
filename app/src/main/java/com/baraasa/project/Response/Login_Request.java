package com.baraasa.project.Response;

import com.google.gson.annotations.SerializedName;

public class Login_Request {
    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public Login_Request(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
