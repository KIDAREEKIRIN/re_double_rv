package com.personal.re_double_rv.models;

import com.google.gson.annotations.SerializedName;

public class DutyStep3{

    @SerializedName("step_id") private int step_id;

    @SerializedName("step3") private String step3;

    public DutyStep3(int step_id, String step3) {
        this.step_id = step_id;
        this.step3 = step3;
    }

    public int getStep_id() {
        return step_id;
    }

    public void setStep_id(int step_id) {
        this.step_id = step_id;
    }

    public String getStep3() {
        return step3;
    }

    public void setStep3(String step3) {
        this.step3 = step3;
    }
}
