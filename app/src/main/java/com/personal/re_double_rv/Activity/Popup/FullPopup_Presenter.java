package com.personal.re_double_rv.Activity.Popup;

import android.view.View;

import com.personal.re_double_rv.Popup_Adapter.FullPopup1_Adapter;
import com.personal.re_double_rv.Retrofit.DutyStep.ApiRetroDataStep;
import com.personal.re_double_rv.Retrofit.DutyStep.RetrofitClientStep;
import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutySteps;
import com.personal.re_double_rv.models.DutyTitle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullPopup_Presenter {

    FullPopup_View view;

    public FullPopup_Presenter(FullPopup_View view) {
        this.view = view;
    }

    // DB에 저장된 데이터 가져오기.
    void getTitleData() {
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyTitle>> call = getDataService.getAllTitles();

        call.enqueue(new Callback<List<DutyTitle>>() {
            @Override
            public void onResponse(Call<List<DutyTitle>> call, Response<List<DutyTitle>> response) {
                if(response.isSuccessful() && response.body() != null) {
//                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DutyTitle>> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    // 업무 steps 불러오기(read).
    void getSteps() {
        ApiRetroDataStep getDataSteps = RetrofitClientStep.getRetrofitClient().create(ApiRetroDataStep.class);
        Call<List<DutySteps>> callReadSteps = getDataSteps.readAllSteps(); // steps 전부 불러오기.

        callReadSteps.enqueue(new Callback<List<DutySteps>>() {
            @Override
            public void onResponse(Call<List<DutySteps>> call, Response<List<DutySteps>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    // 얘는 response.body().getMessage
                    view.onRequestSuccess(response.message());
                } else { // 새로운 내용 추가.
                    view.onRequestError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<DutySteps>> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

//    void getSteps() {
//        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<DutyStep>> callSteps = getDataService.getAllSteps();
//
//        callSteps.enqueue(new Callback<List<DutyStep>>() {
//            @Override
//            public void onResponse(Call<List<DutyStep>> call, Response<List<DutyStep>> response) {
//                if(response.isSuccessful() && response.body() != null) {
//                    view.onRequestSuccess(response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DutyStep>> call, Throwable t) {
//                    view.onRequestError(t.getLocalizedMessage());
//            }
//        });
//    }

    void getStep1List() {
        //Step1.
        GetDataService getDataService1 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyStep1>> callStep1 = getDataService1.getAllStep1();

        callStep1.enqueue(new Callback<List<DutyStep1>>() {
            @Override
            public void onResponse(Call<List<DutyStep1>> call, Response<List<DutyStep1>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    view.onRequestSuccess(response.message());
//                    dutyStep1List = response.body();
//
//                    if(duty_Title_id == 1){
//                        fullPopup_1_adapter = new FullPopup1_Adapter(dutyStep1List);
//                        rv_step_item.setLayoutManager(linearLayoutManager);
//                        rv_step_item.setAdapter(fullPopup_1_adapter);
//                    }
                }
            }

            @Override
            public void onFailure(Call<List<DutyStep1>> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

//    public List<DutyStep1> getStep1() {
//        List<DutyStep1> step1List = new ArrayList<>();
//        for (int i = 0; i < dutyStep1List.size(); i++){
//            step1List.add(i,dutyStep1List.get(i));
//        }
//        return step1List;
//    }

}
