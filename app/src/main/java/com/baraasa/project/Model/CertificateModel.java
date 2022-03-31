package com.baraasa.project.Model;

import com.baraasa.project.Response.User;
import com.google.gson.annotations.SerializedName;

public class CertificateModel {

    @SerializedName("id")
    String id;

    @SerializedName("certificate")
    String certificate;

    @SerializedName("user")
    User user;

    @SerializedName("event")
    EventModel event;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
    }
}
