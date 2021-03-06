package com.personal.re_double_rv.Retrofit;

import com.personal.re_double_rv.models.DutyFile;
import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutyStep2;
import com.personal.re_double_rv.models.DutyStep3;
import com.personal.re_double_rv.models.DutyTip;
import com.personal.re_double_rv.models.DutyTitle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

// Retrofit에서 정의하는 Service.
// interface로 선언하기에 구현할 필요는 없고, 어떤 형태와 방식으로 통신을 할 지를
// Annotation과 parameter
public interface GetDataService {

    // 수련활동 업무 title 가져오기.
    @GET("sooryun_title.php")
    Call<List<DutyTitle>> getAllTitles();

    // 수련활동 업무 Step 다 가져오기.
    @GET("sooryun_step.php")
    Call<List<DutyStep>> getAllSteps();

    // 수련활동 업무 step1 가져오기.
    @GET("sooryun_step1.php")
    Call<List<DutyStep1>> getAllStep1();

    // 수련활동 업무 step2 가져오기.
    @GET("sooryunn_step2.php")
    Call<List<DutyStep2>> getAllStep2();

    @GET("sooryun_step3.php")
    Call<List<DutyStep3>> getAllStep3();

    // 제목 추가 Post 관련.
    @FormUrlEncoded
    @POST("save_title.php")
    Call<DutyTitle> saveTitle(
            @Field("title_name_id") int title_name_id,
            @Field("title_name") String title_name
    );

    // 단계 추가 Post 관련.
    @FormUrlEncoded
    @POST("sooryun_save_step.php")
    Call<DutyStep> saveSteps(
            @Field("step") String step,
            @Field("title_id") int title_id
    );

    // 제목 수정 Post 관련.
    @FormUrlEncoded
    @POST("update_title.php")
    Call<DutyTitle> updateTitle(
            @Field("title_id") int title_id,
            @Field("title_name_id") int title_name_id,
            @Field("title_name") String title_name
    );

    @FormUrlEncoded
    @POST("sooryun_update_step.php")
    Call<DutyStep> updateStep(
            @Field("step_id") int step_id,
            @Field("step") String step
    );

    // 제목 삭제 Post 관련.
    @FormUrlEncoded
    @POST("delete_title.php")
    Call<DutyTitle> deleteTitle(
            @Field("title_name_id") int title_name_id
    );

    // 단계 삭제 Post 관련.
    @FormUrlEncoded // Field 형식을 사용할 때에는 Form이 encode 되어야 한다.
    @POST("sooryun_delete_step.php")
    Call<DutyStep> deleteSteps(
            @Field("step_id") int step_id
    );

    // 단계 체크박스 Post 관련.
    @FormUrlEncoded
    @POST("sooryun_update_check.php")
    Call<DutyStep> updateCheck(
            @Field("step_id") int step_id,
            @Field("check_boolean") int check_boolean
    );

    // 파일 GET 관련.
    @GET("sooryun_file.php")
    Call<List<DutyFile>> getFiles();

    // Tip Get 관련.
    @GET("sooryun_tip.php")
    Call<List<DutyTip>> getTips();
}
