package com.personal.re_double_rv.models;

import com.google.gson.annotations.SerializedName;

public class DutyStep1 {
    @SerializedName("step_id") private int step_id;

    @SerializedName("step1") private String step1;

    @SerializedName("check_boolean") private int check_boolean;

    @SerializedName("file_boolean") private int file_boolean;

    @SerializedName("tip_boolean") private int tip_boolean;

    public DutyStep1(int step_id, String step1, int check_boolean, int file_boolean, int tip_boolean) {
        this.step_id = step_id;
        this.step1 = step1;
        this.check_boolean = check_boolean;
        this.file_boolean = file_boolean;
        this.tip_boolean = tip_boolean;
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

    public int getCheck_boolean() {
        return check_boolean;
    }

    public void setCheck_boolean(int check_boolean) {
        this.check_boolean = check_boolean;
    }

    public int getFile_boolean() {
        return file_boolean;
    }

    public void setFile_boolean(int file_boolean) {
        this.file_boolean = file_boolean;
    }

    public int getTip_boolean() {
        return tip_boolean;
    }

    public void setTip_boolean(int tip_boolean) {
        this.tip_boolean = tip_boolean;
    }
}
