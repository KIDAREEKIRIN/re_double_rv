package com.personal.re_double_rv.Activity.editor;

import com.personal.re_double_rv.Retrofit.DutyTitle.ApiRetroDataTitle;
import com.personal.re_double_rv.Retrofit.DutyTitle.RetrofitClientTitle;
import com.personal.re_double_rv.models.DutyTitle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editor_Presenter {

    private Editor_View view;

    public Editor_Presenter(Editor_View view) {
        this.view = view;
    }

    // 업무 title 저장하기.
    void insertTitle(int title_order, String title_name) {
        ApiRetroDataTitle postTitle = RetrofitClientTitle.getRetrofitClient().create(ApiRetroDataTitle.class);
        Call<DutyTitle> callInsertTitle = postTitle.insertTitle(title_order, title_name);

        callInsertTitle.enqueue(new Callback<DutyTitle>() {
            @Override
            public void onResponse(Call<DutyTitle> call, Response<DutyTitle> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success) { // 성공시
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
    // 제목 저장하기.
//    void saveTitle(String title_name, int title_name_id) {
//        GetDataService postTitle = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<DutyTitle> callPostTitle = postTitle.saveTitle(title_name, title_name_id);
//
//        callPostTitle.enqueue(new Callback<DutyTitle>() {
//            @Override
//            public void onResponse(Call<DutyTitle> call, Response<DutyTitle> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    Boolean success = response.body().getSuccess();
//                    if (success) { // 성공시.
//                        view.onRequestSuccess(response.body().getMessage());
//
//                    } else { // 실패시.
//                        view.onRequestError(response.body().getMessage());
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<DutyTitle> call, Throwable t) {
//                view.onRequestError(t.getLocalizedMessage());
//            }
//        });
//    }

    // 수정하기(최신버전) title_order 버전.
    // 수정하기에서는 입력해서 바뀌는 값만, 파라미터로 지정한다. // title_id는 숨겨진 인덱스 느낌.
    void updateTitle(int title_id, int title_order, String title_name) {
        ApiRetroDataTitle postUpdateTitle = RetrofitClientTitle.getRetrofitClient().create(ApiRetroDataTitle.class);
        Call<DutyTitle> callUpdateTitle = postUpdateTitle.updateTitle(title_id, title_order,title_name);

        callUpdateTitle.enqueue(new Callback<DutyTitle>() {
            @Override
            public void onResponse(Call<DutyTitle> call, Response<DutyTitle> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success) { // 성공하면,
                        view.onRequestSuccess(response.body().getMessage());
                    } else { // 실패하면,
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
//
//    // 수정하기 (DB연동)
//    void updateTitle(int title_name_id, String title_name) {
//
//        GetDataService postUpdate_Title = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<DutyTitle> callUpdate_Title = postUpdate_Title.updateTitle(title_name_id, title_name);
//
//        callUpdate_Title.enqueue(new Callback<DutyTitle>() {
//            @Override
//            public void onResponse(Call<DutyTitle> call, Response<DutyTitle> response) {
//                if(response.isSuccessful() && response.body() != null) {
//                    Boolean success = response.body().getSuccess();
//                    if(success) {
//                        view.onRequestSuccess(response.body().getMessage());
//                    } else {
//                        view.onRequestError(response.body().getMessage());
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<DutyTitle> call, Throwable t) {
//                view.onRequestError(t.getLocalizedMessage());
//            }
//        });
//    }

    // 업무 Title 삭제(delete)하기.
    // 왜 public 인가????
   public void deleteTitle(int title_order) {
        ApiRetroDataTitle postDeleteTitle = RetrofitClientTitle.getRetrofitClient().create(ApiRetroDataTitle.class);
        Call<DutyTitle> callDeleteTitle = postDeleteTitle.deleteTitle(title_order);

        callDeleteTitle.enqueue(new Callback<DutyTitle>() {
            @Override
            public void onResponse(Call<DutyTitle> call, Response<DutyTitle> response) {
                if(response.isSuccessful() && response.body() != null) {
                    boolean success = response.body().getSuccess();
                    if(success) {
                        // 이 메시지가 뜨면 에러가 나서 해당 내용을 주석처리함.
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

    // Title 삭제하기.
//    public void deleteTitle(int title_name_id) {
//
//        GetDataService postDelete_Title = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<DutyTitle> callDelete_Title = postDelete_Title.deleteTitle(title_name_id);
//
//        callDelete_Title.enqueue(new Callback<DutyTitle>() {
//            @Override
//            public void onResponse(Call<DutyTitle> call, Response<DutyTitle> response) {
//                if(response.isSuccessful() && response.body().getSuccess() != null) {
//                    Boolean success = response.body().getSuccess();
//                    if(success) {
////                        view.onRequestSuccess(response.body().getMessage());
//                    } else {
//                        view.onRequestError(response.body().getMessage());
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<DutyTitle> call, Throwable t) {
//                view.onRequestError(t.getLocalizedMessage());
//            }
//        });
//    }
}
