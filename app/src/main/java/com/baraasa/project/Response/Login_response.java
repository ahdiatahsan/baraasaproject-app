package com.baraasa.project.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Login_response<T> {

    @Expose
    @SerializedName("message")
    String message;

    @Expose
    @SerializedName("0")
    X0 x0;

    @Expose
    @SerializedName("1")
    int x1;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public X0 getX0() {
        return x0;
    }

    public void setX0(X0 x0) {
        this.x0 = x0;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }
}
