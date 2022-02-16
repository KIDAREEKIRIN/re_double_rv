package com.personal.re_double_rv.Activity.Popup_Sub;

import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutyTitle;

import java.util.List;

public interface Popup_View {

    // 수정 시, 명칭 바꾸기.
    void onRequestSuccess(String message);
    void onRequestError(String message);
    void onGetResult(List<DutyStep> dutyStepList);
}
