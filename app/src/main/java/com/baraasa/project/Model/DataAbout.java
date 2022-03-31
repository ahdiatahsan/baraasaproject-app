package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

public class DataAbout {

    @SerializedName("about")
    AboutModel about;

    public AboutModel getAbout() {
        return about;
    }

    public void setAbout(AboutModel about) {
        this.about = about;
    }
}
