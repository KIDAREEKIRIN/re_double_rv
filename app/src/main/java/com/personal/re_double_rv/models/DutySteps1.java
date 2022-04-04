package com.personal.re_double_rv.models;

import com.google.gson.annotations.SerializedName;

public class DutySteps1 {

    @SerializedName("step_id") private int step_id; // 업무 step에 대한 인덱스 값.

    @SerializedName("step_name") private String step_name; // 업무 step 이름에 대한 값.

    public DutySteps1(int step_id, String step_name) {
        this.step_id = step_id;
        this.step_name = step_name;
    }

    // step1에 대한 Getter & Setter
    public int getStep_id() {
        return step_id;
    }

    public void setStep_id(int step_id) {
        this.step_id = step_id;
    }

    public String getStep_name() {
        return step_name;
    }

    public void setStep_name(String step_name) {
        this.step_name = step_name;
    }
}
