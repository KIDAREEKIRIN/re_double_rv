package com.personal.re_double_rv.Retrofit.DutyTitle;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientTitle {

    private static Retrofit retrofit;

    // test2(데이터베이스) 의 DutyTitle(폴더/디렉토리 이름) 값에 대한 데이터베이스를 URL 주소로 연결한다.
    private static final String BASE_URL = "http://3.37.249.79/DutyTitle/";
    // Retrofit 연결하기.
    public static Retrofit getRetrofitClient() {
        // retrofit 값이 비어있다면,
        if(retrofit == null) {
            // retrofit 을 새롭게 만들고,
            retrofit = new Retrofit.Builder()
                    // Url을 연결시키고.
                    .baseUrl(BASE_URL)
                    // JSON 형식의 파일을 POJO class 형식의 파일로 변환해준다.
                    .addConverterFactory(GsonConverterFactory.create())
                    // POJO CLASS 형식의 파일은 기존의 Model 클래스의 파일의 형식
                    // private String a, private int b 등. 여기서는 DutyTitle, DutyStep 등이 있다.
                    // 마지막으로 build()를 해준다.
                    .build();
        }
        // retrofit의 리턴값을 받는다.
        return retrofit;
    }
}
