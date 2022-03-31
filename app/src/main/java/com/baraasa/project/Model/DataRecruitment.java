package com.baraasa.project.Model;

import com.google.gson.annotations.SerializedName;

public class DataRecruitment {

    @SerializedName("recruitment")
    RecruitmentModel recruitment;

    public RecruitmentModel getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(RecruitmentModel recruitment) {
        this.recruitment = recruitment;
    }
}
