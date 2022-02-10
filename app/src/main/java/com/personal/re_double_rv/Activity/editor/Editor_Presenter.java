package com.personal.re_double_rv.Activity.editor;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.personal.re_double_rv.Activity.main.MainActivity;
import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
import com.personal.re_double_rv.models.DutyTitle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editor_Presenter {

    private Editor_View view;

    public Editor_Presenter(Editor_View view) {
        this.view = view;
    }

    // 제목 저장하기.
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

    // 수정하기 (DB연동)
    void updateTitle(int title_name_id, String title_name) {

        GetDataService postUpdate_Title = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DutyTitle> callUpdate_Title = postUpdate_Title.updateTitle(title_name_id, title_name);

        callUpdate_Title.enqueue(new Callback<DutyTitle>() {
            @Override
            public void onResponse(Call<DutyTitle> call, Response<DutyTitle> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success) {
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

    // Title 삭제하기.
    public void deleteTitle(int title_name_id) {

        GetDataService postDelete_Title = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DutyTitle> callDelete_Title = postDelete_Title.deleteTitle(title_name_id);

        callDelete_Title.enqueue(new Callback<DutyTitle>() {
            @Override
            public void onResponse(Call<DutyTitle> call, Response<DutyTitle> response) {
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
            public void onFailure(Call<DutyTitle> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
}
