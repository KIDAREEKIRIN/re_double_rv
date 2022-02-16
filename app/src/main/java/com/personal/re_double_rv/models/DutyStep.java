package com.personal.re_double_rv.models;

import com.google.gson.annotations.SerializedName;

public class DutyStep {

    @SerializedName("step_id") private int step_id;

    @SerializedName("step_name") private String step_name;

    @SerializedName("title_id") private int title_id;

    public DutyStep(int step_id, String step_name, int title_id) {
        this.step_id = step_id;
        this.step_name = step_name;
        this.title_id = title_id;
    }

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

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }
}
