package com.personal.re_double_rv.Retrofit.DutyStep;

import com.personal.re_double_rv.models.DutySteps;
import com.personal.re_double_rv.models.DutySteps1;
import com.personal.re_double_rv.models.DutyTitle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRetroDataStep {

    // 업무 Step 추가(insert)하기.
    @FormUrlEncoded // url의 형식으로 encoded(암호화된).
    @POST("dutyStep_insert.php")
    // 기존 DutyStep 을 사용하므로 새로운 내용을 적용하려면 DutySteps POJO 클래스를 만들어서 적용시킨다.
    Call<DutySteps> insertStep(
            // step_name -> 업무 step 이름.
            @Field("step_name") String step_name,
            // title_order -> 업무 title 순서.
            @Field("title_order") int title_order
    );
    // title_order 에 따라서 불러오는 값이 달라진다. FullPopup에서 step_name 에 대한 CRUD 작성.

    // 업무 Step 읽기(read).
    @GET("dutyStep_read.php") // List 형태로 steps 전부 불러오기.
    Call<List<DutySteps>> readAllSteps();

    // 업무 Step1List 읽기(read) -> title_order 값이 "1"인 것만 불러냄.
    @GET("dutyStep_step1List.php") // step1List만 따로 불러냄.
    Call<List<DutySteps1>> readAllSteps1(); // title_order 가 "1"인 얘들만 모아서 하나의 리스트를 만든다.





}
