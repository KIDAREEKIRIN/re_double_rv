package com.personal.re_double_rv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.personal.re_double_rv.models.DutyStep1;

import java.util.List;

// test2의 데이터베이스의 값을 불러오는 POJO 클래스.
public class DutyTitle {

    // title에 붙어있는 id 값.
    @Expose
    @SerializedName("title_id") private int title_id;
    // 업무 title 에 붙어있는 순서 번호.
    // 기존의 title_name_id 를 title_order로 바꿈.
    @Expose
    @SerializedName("title_order") private int title_order;
//    @Expose
//    @SerializedName("title_name_id") private int title_name_id;

    // title_name의 String 값.
    @Expose
    @SerializedName("title_name") private String title_name;

    // CRUD 추가 내용.
    @Expose
    @SerializedName("success") private Boolean success;

    @Expose
    @SerializedName("message") private String message;

    private List<DutyStep> dutyStepList;

    public DutyTitle(int title_id, int title_order ,String title_name, List<DutyStep> dutyStepList) {
        this.title_id = title_id;
        this.title_order = title_order;
//        this.title_name_id = title_name_id;
        this.title_name = title_name;
        this.dutyStepList = dutyStepList;
    }

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    // 기존 title_name_id => title_order 로 교체.
//    public int getTitle_name_id() {
//        return title_name_id;
//    }
//
//    public void setTitle_name_id(int title_name_id) {
//        this.title_name_id = title_name_id;
//    }


    public int getTitle_order() {
        return title_order;
    }

    public void setTitle_order(int title_order) {
        this.title_order = title_order;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public List<DutyStep> getDutyStepList() {
        return dutyStepList;
    }

    public void setDutyStepList(List<DutyStep> dutyStepList) {
        this.dutyStepList = dutyStepList;
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
