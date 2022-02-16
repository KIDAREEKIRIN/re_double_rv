package com.personal.re_double_rv.Activity.Popup;

import android.view.View;

import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutyTitle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullPopup_Presenter {

    FullPopup_View view;

    public FullPopup_Presenter(FullPopup_View view) {
        this.view = view;
    }

    void saveStep1(String step, int title_id) {
        GetDataService postStep1 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DutyStep1> callPostStep1 = postStep1.saveStep1(step,title_id);

        callPostStep1.enqueue(new Callback<DutyStep1>() {
            @Override
            public void onResponse(Call<DutyStep1> call, Response<DutyStep1> response) {
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
            public void onFailure(Call<DutyStep1> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void saveTitle(String title_name) {
        GetDataService postTitle = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DutyTitle> callPostTitle = postTitle.saveTitle(title_name);

        callPostTitle.enqueue(new Callback<DutyTitle>() {
            @Override
            public void onResponse(Call<DutyTitle> call, Response<DutyTitle> response) {
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
            public void onFailure(Call<DutyTitle> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    // 데이터 가져오기.
//    void getData() {
//        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<DutyStep1>> call = getDataService.getAllStep1();
//
//        call.enqueue(new Callback<List<DutyStep1>>() {
//            @Override
//            public void onResponse(Call<List<DutyStep1>> call, Response<List<DutyStep1>> response) {
//                if(response.isSuccessful() && response.body() != null) {
//                    view.onGetResult(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DutyStep1>> call, Throwable t) {
//                view.onRequestError(t.getLocalizedMessage());
//            }
//        });
//    }

}
