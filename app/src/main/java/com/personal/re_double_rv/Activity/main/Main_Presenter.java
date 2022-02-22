package com.personal.re_double_rv.Activity.main;

import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
import com.personal.re_double_rv.models.DutyFile;
import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutyTitle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_Presenter {

    private Main_View view;

    public Main_Presenter(Main_View view) {
        this.view = view;
    }

    // 제목 저장하기.
//    void saveTitle(String title_name) {
//        GetDataService postTitle = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<DutyTitle> callPostTitle = postTitle.saveTitle( title_name);
//
//        callPostTitle.enqueue(new Callback<DutyTitle>() {
//            @Override
//            public void onResponse(Call<DutyTitle> call, Response<DutyTitle> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    Boolean success = response.body().getSuccess();
//                    if (success) {
//                        view.onRequestSuccess(response.body().getMessage());
//
//                    } else {
//                        view.onRequestError(response.body().getMessage());
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DutyTitle> call, Throwable t) {
//                view.onRequestError(t.getLocalizedMessage());
//            }
//        });
//    }

    // DB에 저장된 데이터 가져오기.
    void getData() {
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyTitle>> call = getDataService.getAllTitles();

        call.enqueue(new Callback<List<DutyTitle>>() {
            @Override
            public void onResponse(Call<List<DutyTitle>> call, Response<List<DutyTitle>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DutyTitle>> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    // 체크박스 업데이트.
    public void updateCheck(int step_id, int check_boolean) {
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DutyStep> callPostCheck = getDataService.updateCheck(step_id,check_boolean);

        callPostCheck.enqueue(new Callback<DutyStep>() {
            @Override
            public void onResponse(Call<DutyStep> call, Response<DutyStep> response) {
                if(response.isSuccessful() && response.body() != null) {
//                    view.onRequestSuccess(response.message());
                }
            }

            @Override
            public void onFailure(Call<DutyStep> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    // 파일 가져오기.
    public void getFiles() {
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyFile>> callFiles = getDataService.getFiles();

        callFiles.enqueue(new Callback<List<DutyFile>>() {
            @Override
            public void onResponse(Call<List<DutyFile>> call, Response<List<DutyFile>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    view.onRequestSuccess(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<DutyFile>> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

}
