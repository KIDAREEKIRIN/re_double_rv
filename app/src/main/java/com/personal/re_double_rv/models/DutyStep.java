package com.personal.re_double_rv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DutyStep {

    @SerializedName("step_id") private int step_id;

    @SerializedName("step") private String step;

    @SerializedName("title_id") private int title_id; // title_order.

    //Checkbox
    @SerializedName("check_boolean") private int check_boolean;

    //file
    @SerializedName("file_boolean") private int file_boolean;

    //tip
    @SerializedName("tip_boolean") private int tip_boolean;

    // CRUD 추가 내용.
    @Expose
    @SerializedName("success") private Boolean success;

    @Expose
    @SerializedName("message") private String message;

//    private List<DutyStep> dutyStepList;


    public DutyStep(int step_id, String step, int title_id, int check_boolean, int file_boolean, int tip_boolean) {
        this.step_id = step_id;
        this.step = step;
        this.title_id = title_id;
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

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
