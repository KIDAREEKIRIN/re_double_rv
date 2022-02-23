package com.personal.re_double_rv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DutyFile {

    @SerializedName("step_id") private int step_id;

    @SerializedName("file_id") private int file_id;

    @SerializedName("file_boolean") private int file_boolean;

    @SerializedName("file_path") private String file_path;

    // CRUD 추가 내용.
    @Expose
    @SerializedName("success") private Boolean success;

    @Expose
    @SerializedName("message") private String message;

    public DutyFile(int step_id, int file_id, int file_boolean, String file_path) {
        this.step_id = step_id;
        this.file_id = file_id;
        this.file_boolean = file_boolean;
        this.file_path = file_path;
    }

    public int getStep_id() {
        return step_id;
    }

    public void setStep_id(int step_id) {
        this.step_id = step_id;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public int getFile_boolean() {
        return file_boolean;
    }

    public void setFile_boolean(int file_boolean) {
        this.file_boolean = file_boolean;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
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
