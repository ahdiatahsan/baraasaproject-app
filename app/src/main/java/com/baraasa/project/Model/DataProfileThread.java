package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataProfileThread {
    @SerializedName("thread")
    ArrayList<ThreadModel> thread;

    public ArrayList<ThreadModel> getThread() {
        return thread;
    }

    public void setThread(ArrayList<ThreadModel> thread) {
        this.thread = thread;
    }
}
