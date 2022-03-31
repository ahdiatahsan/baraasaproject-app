package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

public class RecruitmentModel {

    @SerializedName("recruitment_status")
    String recruitment_status;

    @SerializedName("recruitment_thumbnail")
    String recruitment_thumbnail;

    public String getRecruitment_status() {
        return recruitment_status;
    }

    public void setRecruitment_status(String recruitment_status) {
        this.recruitment_status = recruitment_status;
    }

    public String getRecruitment_thumbnail() {
        return recruitment_thumbnail;
    }

    public void setRecruitment_thumbnail(String recruitment_thumbnail) {
        this.recruitment_thumbnail = recruitment_thumbnail;
    }
}
