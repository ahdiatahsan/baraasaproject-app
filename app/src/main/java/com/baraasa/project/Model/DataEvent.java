package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataEvent {

    @SerializedName("event")
    ArrayList<EventModel> event;

    @SerializedName("message")
    String message;

    public ArrayList<EventModel> getEvent() {
        return event;
    }

    public void setEvent(ArrayList<EventModel> event) {
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
