package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

public class Pass_Request {
    @SerializedName("password")
    String password;

    @SerializedName("password_confirm")
    String password_confirm;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }
}
