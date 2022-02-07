package com.personal.re_double_rv.models;

import com.google.gson.annotations.SerializedName;

public class DutyStep2 {

//    @SerializedName("step_id") private int step_id;

    @SerializedName("step2") private String step2;

    public DutyStep2(int step_id, String step2) {
//        this.step_id = step_id;
        this.step2 = step2;
    }

//    public int getStep_id() {
//        return step_id;
//    }
//
//    public void setStep_id(int step_id) {
//        this.step_id = step_id;
//    }

    public String getStep2() {
        return step2;
    }

    public void setStep2(String step2) {
        this.step2 = step2;
    }
}
