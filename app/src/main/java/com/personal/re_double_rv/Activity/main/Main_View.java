package com.personal.re_double_rv.Activity.main;

import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutyTitle;

import java.util.List;

public interface Main_View {

    void onRequestSuccess(String message);
    void onRequestError(String message);
    void onGetResult(List<DutyTitle> dutyTitleList);
    void onGetSteps(List<DutyStep> dutyStepList);
}
