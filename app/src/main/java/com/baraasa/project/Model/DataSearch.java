package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataSearch {

    @SerializedName("blog")
    ArrayList<BlogModelSearch> blog;

    @SerializedName("thread")
    ArrayList<ThreadModelSearch> thread;

    @SerializedName("event")
    ArrayList<EventModel> event;

    @SerializedName("research")
    ArrayList<ResearchModel> research;
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<BlogModelSearch> getBlog() {
        return blog;
    }

    public void setBlog(ArrayList<BlogModelSearch> blog) {
        this.blog = blog;
    }

    public ArrayList<ThreadModelSearch> getThread() {
        return thread;
    }

    public void setThread(ArrayList<ThreadModelSearch> thread) {
        this.thread = thread;
    }

    public ArrayList<EventModel> getEvent() {
        return event;
    }

    public void setEvent(ArrayList<EventModel> event) {
        this.event = event;
    }

    public ArrayList<ResearchModel> getResearch() {
        return research;
    }

    public void setResearch(ArrayList<ResearchModel> research) {
        this.research = research;
    }
}
