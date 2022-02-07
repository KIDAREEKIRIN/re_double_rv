package com.personal.re_double_rv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.personal.re_double_rv.models.DutyStep1;

import java.util.List;

public class DutyTitle {

    @Expose
    @SerializedName("title_id") private int title_id;

    @Expose
    @SerializedName("title_name") private String title_name;

    // CRUD 추가 내용.
    @Expose
    @SerializedName("success") private Boolean success;

    @Expose
    @SerializedName("message") private String message;

    private List<DutyStep1> dutyStep1List;

    public DutyTitle(int title_id, String title_name, List<DutyStep1> dutyStep1List) {
        this.title_id = title_id;
        this.title_name = title_name;
        this.dutyStep1List = dutyStep1List;
    }

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public List<DutyStep1> getDutyStep1List() {
        return dutyStep1List;
    }

    public void setDutyStep1List(List<DutyStep1> dutyStep1List) {
        this.dutyStep1List = dutyStep1List;
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
