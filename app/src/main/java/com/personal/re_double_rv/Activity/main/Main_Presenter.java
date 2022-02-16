package com.personal.re_double_rv.Activity.main;

import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
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
    void saveTitle(int title_name_id, String title_name) {
        GetDataService postTitle = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DutyTitle> callPostTitle = postTitle.saveTitle(title_name_id, title_name);

        callPostTitle.enqueue(new Callback<DutyTitle>() {
            @Override
            public void onResponse(Call<DutyTitle> call, Response<DutyTitle> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());

                    } else {
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

}
