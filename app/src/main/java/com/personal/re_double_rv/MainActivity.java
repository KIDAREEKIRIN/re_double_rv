package com.personal.re_double_rv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
import com.personal.re_double_rv.models.DutyTitle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_title; // 리사이클러뷰(전체) 선언.
    LinearLayoutManager linearLayoutManager; // 리니어 레이아웃 선언
    Title_Adapter title_adapter; // 첫번째 어댑터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DutyTitle + Retrofit 결합.
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyTitle>> call = getDataService.getAllTitles();

        call.enqueue(new Callback<List<DutyTitle>>() {
            @Override
            public void onResponse(Call<List<DutyTitle>> call, Response<List<DutyTitle>> response) {
                if(response.isSuccessful() && response.body() != null) {

                    generateDataList(response.body());
                    Toast.makeText(getApplicationContext(), "잘했어.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<DutyTitle>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "도망쳐.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generateDataList(List<DutyTitle> dutyTitleList) {

        rv_title = findViewById(R.id.rv_title); // 리사이클러뷰
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        title_adapter = new Title_Adapter(dutyTitleList);
        rv_title.setAdapter(title_adapter);
        rv_title.setLayoutManager(linearLayoutManager);

    }
}