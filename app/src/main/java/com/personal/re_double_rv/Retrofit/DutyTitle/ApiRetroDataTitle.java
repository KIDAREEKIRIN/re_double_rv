package com.personal.re_double_rv.Retrofit.DutyTitle;

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
public interface ApiRetroDataTitle {

    // (JAVA) Annotation = 자바 소스 코드에 추가하여 사용할 수 있는 메타데이터의 일종, 보통 @기호를 앞에 붙여 사용한다.\
    // 클래스 파일에 임베디드되어 컴파일러에 의해 생성된 후 자바 가상머신에 포함되어 작동한다.

    // dutyTitle POJO 데이터 클래스.

    // 업무 title 추가(insert)하기.
    @FormUrlEncoded // url의 형식으로 encoded(암호화된).
    @POST("dutyTitle_insert.php")
    Call<DutyTitle> insertTitle(
            // title_order -> 업무순서.
            @Field("title_order") int title_order,
            // title_name -> 업무 title 이름
            @Field("title_name") String title_name
    );

    // 업무 title 읽기(read).
    @GET("dutyTitle_read.php") // List 형태로 불러오기.
    Call<List<DutyTitle>> readAllTitles();

    // 업무 title 수정(update)하기.
    @FormUrlEncoded
    @POST("dutyTitle_update.php") // update php 주소.
    Call<DutyTitle> updateTitle(
            @Field("title_id") int title_id, // 업무 title Index.
            @Field("title_order") int title_order, // 업무 title 순서.
            @Field("title_name") String title_name // 업무 title 이름.
    );

    // 업무 title 삭제(delete)하기.
    @FormUrlEncoded
    @POST("dutyTitle_delete.php") // delete php 주소.
    Call<DutyTitle> deleteTitle(
            @Field("title_order") int title_order // 업무 title 순서.
    );
}
