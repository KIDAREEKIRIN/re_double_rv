package com.personal.re_double_rv.Activity.Popup_Sub;

import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
import com.personal.re_double_rv.models.DutyStep;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Popup_Sub_Presenter {

    private Popup_View view;

    public Popup_Sub_Presenter(Popup_View view) {
        this.view = view;
    }

    void saveStep(String step, int title_id) {

        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DutyStep> callPostStep = getDataService.saveSteps(step, title_id);

        callPostStep.enqueue(new Callback<DutyStep>() {
            @Override
            public void onResponse(Call<DutyStep> call, Response<DutyStep> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) { // 성공시.
                        view.onRequestSuccess(response.body().getMessage());

                    } else { // 실패시.
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<DutyStep> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }

    public void updateStep(int step_id, String step) {

        GetDataService postUpdate_Step = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DutyStep> callUpdate_Step = postUpdate_Step.updateStep(step_id, step);

        callUpdate_Step.enqueue(new Callback<DutyStep>() {
            @Override
            public void onResponse(Call<DutyStep> call, Response<DutyStep> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success) {
//                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<DutyStep> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }

    public void deleteStep(int step_id) {
        GetDataService postDelete_Step = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DutyStep> callDelete_Step = postDelete_Step.deleteSteps(step_id);

        callDelete_Step.enqueue(new Callback<DutyStep>() {
            @Override
            public void onResponse(Call<DutyStep> call, Response<DutyStep> response) {
                if(response.isSuccessful() && response.body().getSuccess() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success) {
//                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<DutyStep> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
}
