package com.personal.re_double_rv;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

    // 추가 Fab 버튼.
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 추가 버튼을 누르면
        fab = findViewById(R.id.add_main); // 선언.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 추가 가능한 Activity 화면전환.
//                startActivity(new Intent(MainActivity.this,EditorActivity.class));

                final EditText editText = new EditText(MainActivity.this);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("추가할 제목");
                builder.setView(editText);
                builder.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title_name =  editText.getText().toString();
                        Log.d("입력한 값은?", title_name);

                        GetDataService postTitle = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                        Call<DutyTitle> callPostTitle = postTitle.saveTitle(title_name);

                        callPostTitle.enqueue(new Callback<DutyTitle>() {
                            @Override
                            public void onResponse(Call<DutyTitle> call, Response<DutyTitle> response) {
                                if(response.isSuccessful() && response.body() != null) {
                                    Boolean success = response.body().getSuccess();

                                    if(success) {
                                        Toast.makeText(MainActivity.this,
                                                response.body().getMessage(),
                                                Toast.LENGTH_SHORT).show();
//                                        finish(); // back to main
                                    } else {
                                        Toast.makeText(MainActivity.this,
                                                response.body().getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<DutyTitle> call, Throwable t) {

                            }
                        });

                    }
                });
                builder.show();
            }
        });

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
        title_adapter.notifyDataSetChanged();
        rv_title.setLayoutManager(linearLayoutManager);

    }
}