package com.personal.re_double_rv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DutySteps {

    @SerializedName("step_id") private int step_id; // 업무 단계 id , Primary Key

    @SerializedName("step_name") private String step_name; // 업무 단계 이름.

    @SerializedName("step_order") private int step_order; // 업무 단계 순서 order,

    @SerializedName("title_order") private int title_order; // 업무 타이틀 순서. Foreign Key.

    @SerializedName("duty_id") private int duty_id; // 업무 이름 index 값.

    @SerializedName("user_nickname") private String user_nickname; // 유저 닉네임 (user_info)에서 값을 받아올 예정.

    // CRUD 추가 내용.
    @Expose
    @SerializedName("success") private Boolean success; // Boolean 성공시.

    @Expose
    @SerializedName("message") private String message; // 메세지 값.

    // 생성자 만들기.
    public DutySteps(int step_id, String step_name, int step_order, int title_order, int duty_id, String user_nickname) {
        this.step_id = step_id;
        this.step_name = step_name;
        this.step_order = step_order;
        this.title_order = title_order;
        this.duty_id = duty_id;
        this.user_nickname = user_nickname;
    }

    // Getter & Setter 만들가.
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

    public int getStep_order() {
        return step_order;
    }

    public void setStep_order(int step_order) {
        this.step_order = step_order;
    }

    public int getTitle_order() {
        return title_order;
    }

    public void setTitle_order(int title_order) {
        this.title_order = title_order;
    }

    public int getDuty_id() {
        return duty_id;
    }

    public void setDuty_id(int duty_id) {
        this.duty_id = duty_id;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
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

