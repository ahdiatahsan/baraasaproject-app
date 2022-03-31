package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataResearch {

    @SerializedName("research")
    ArrayList<ResearchModel> research;

    @SerializedName("message")
    String message;

    public ArrayList<ResearchModel> getResearch() {
        return research;
    }

    public void setResearch(ArrayList<ResearchModel> research) {
        this.research = research;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
