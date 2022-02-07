package com.personal.re_double_rv.Retrofit;

import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutyStep2;
import com.personal.re_double_rv.models.DutyTitle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    // 수련활동 업무 title 가져오기.
    @GET("sooryun_title.php")
    Call<List<DutyTitle>> getAllTitles();

    // 수련활동 업무 step1 가져오기.
    @GET("sooryun_step1.php")
    Call<List<DutyStep1>> getAllStep1();

    // 수련활동 업무 step2 가져오기.
    @GET("sooryunn_step2.php")
    Call<List<DutyStep2>> getAllStep2();
}
