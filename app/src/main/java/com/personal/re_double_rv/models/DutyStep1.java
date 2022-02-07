package com.personal.re_double_rv.models;

import com.google.gson.annotations.SerializedName;

public class DutyStep1 {
    @SerializedName("step_id") private int step_id;

    @SerializedName("step1") private String step1;

    public DutyStep1(int step_id, String step1) {
        this.step_id = step_id;
        this.step1 = step1;
    }

    public int getStep_id() {
        return step_id;
    }

    public void setStep_id(int step_id) {
        this.step_id = step_id;
    }

    public String getStep1() {
        return step1;
    }

    public void setStep1(String step1) {
        this.step1 = step1;
    }
}
