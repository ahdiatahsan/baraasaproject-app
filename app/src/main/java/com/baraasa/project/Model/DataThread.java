package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataThread {
    @SerializedName("thread")
    ArrayList<ThreadModel> thread;

    @SerializedName("message")
    String message;

    public ArrayList<ThreadModel> getThread() {
        return thread;
    }

    public void setThread(ArrayList<ThreadModel> thread) {
        this.thread = thread;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
