package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataCertificate {

    @SerializedName("participant")
    ArrayList<CertificateModel> research;

    public ArrayList<CertificateModel> getResearch() {
        return research;
    }

    public void setResearch(ArrayList<CertificateModel> research) {
        this.research = research;
    }
}
