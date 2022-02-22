package com.personal.re_double_rv.models;

import com.google.gson.annotations.SerializedName;

public class DutyTip {
    @SerializedName("step_id") private int step_id;

    @SerializedName("step") private String step;

    @SerializedName("tip_boolean") private int tip_boolean;

    @SerializedName("tip_content") private String tip_content;

    public DutyTip(int step_id, String step, int tip_boolean, String tip_content) {
        this.step_id = step_id;
        this.step = step;
        this.tip_boolean = tip_boolean;
        this.tip_content = tip_content;
    }

    public int getStep_id() {
        return step_id;
    }

    public void setStep_id(int step_id) {
        this.step_id = step_id;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public int getTip_boolean() {
        return tip_boolean;
    }

    public void setTip_boolean(int tip_boolean) {
        this.tip_boolean = tip_boolean;
    }

    public String getTip_content() {
        return tip_content;
    }

    public void setTip_content(String tip_content) {
        this.tip_content = tip_content;
    }
}
