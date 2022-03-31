package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

public class AboutModel {
    @SerializedName("id")
    String id;

    @SerializedName("email")
    String email;

    @SerializedName("phone")
    String phone;

    @SerializedName("address")
    String address;

    @SerializedName("instagram")
    String instagram;

    @SerializedName("spotify")
    String spotify;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }
}
