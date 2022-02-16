package com.personal.re_double_rv.Activity.Popup;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.personal.re_double_rv.Activity.editor.Popup_Activity;
import com.personal.re_double_rv.Popup_Adapter.FullPopup1_Adapter;
import com.personal.re_double_rv.Popup_Adapter.FullPopup2_Adapter;
import com.personal.re_double_rv.Popup_Adapter.FullPopup3_Adapter;
import com.personal.re_double_rv.R;
import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutyStep2;
import com.personal.re_double_rv.models.DutyStep3;
import com.personal.re_double_rv.models.DutyTitle;
import com.personal.re_double_rv.steps_Adapter.Step_Adapter;
import com.personal.re_double_rv.title_Adapter.ItemClickListener;
import com.personal.re_double_rv.title_Adapter.Title_Adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullPopupActivity extends AppCompatActivity implements FullPopup_View, View.OnClickListener {

    CardView cv_dutyTitle;
    TextView tv_duty_title_id, tv_duty_title;
    ImageButton ib_delete_Title, ib_edit_Title;
    Button btn_OK;

    RecyclerView rv_step_item;// 리사이클러뷰

    LinearLayoutManager linearLayoutManager;// 리니어 레이아웃 그리기.

    FullPopup1_Adapter fullPopup_1_adapter; // step1 어댑터
    FullPopup2_Adapter fullPopup_2_adapter; // step2 어댑터
    FullPopup3_Adapter fullPopup_3_adapter; // step3 어댑터

    // Title + Step 리스트.
    List<DutyTitle> dutyTitleList;
    List<DutyStep> dutyStepList;
    List<DutyStep1> dutyStep1List;
    List<DutyStep2> dutyStep2List;
    List<DutyStep3> dutyStep3List;

    FullPopup_Presenter fullPopup_presenter; //팝업 Presenter

    // 받아온 제목.
    String duty_Title;
    int duty_Title_id;

    // 받은 단계;
    String step1;

    // 추가 Fab 버튼.
    FloatingActionButton fab;

    ItemClickListener itemClickListener;

    private static final String TAG = "fAB 클릭 시,";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_popup);

        fullPopup_presenter = new FullPopup_Presenter( this); //FullPopup_Presenter 생성.

        tv_duty_title = findViewById(R.id.tv_duty_title); // title
        tv_duty_title_id = findViewById(R.id.tv_duty_title_id); // title_id

        // Intent 값 받아오기.
        Intent intent = getIntent();

        // Id 값을 받아서 String으로 표시하기.
        duty_Title_id = intent.getIntExtra("duty_Title_id",0);
        tv_duty_title_id.setText(String.valueOf(duty_Title_id));

        // Title을 받아서 표현하기.
        duty_Title = intent.getStringExtra("duty_Title");
        tv_duty_title.setText(duty_Title);

        // Title
        GetDataService getDataTitle = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyTitle>> callTitle = getDataTitle.getAllTitles();

        callTitle.enqueue(new Callback<List<DutyTitle>>() {
            @Override
            public void onResponse(Call<List<DutyTitle>> call, Response<List<DutyTitle>> response) {
                dutyTitleList = response.body();
            }

            @Override
            public void onFailure(Call<List<DutyTitle>> call, Throwable t) {

            }
        });

        // 리사이클러뷰.
        rv_step_item = findViewById(R.id.rv_step_item);
        rv_step_item.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(FullPopupActivity.this);

        //Step1.
        GetDataService getDataService1 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyStep1>> callStep1 = getDataService1.getAllStep1();

        callStep1.enqueue(new Callback<List<DutyStep1>>() {
            @Override
            public void onResponse(Call<List<DutyStep1>> call, Response<List<DutyStep1>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutyStep1List = response.body();

                    if(duty_Title_id == 1){
                        fullPopup_1_adapter = new FullPopup1_Adapter(dutyStep1List);
                        rv_step_item.setLayoutManager(linearLayoutManager);
                        rv_step_item.setAdapter(fullPopup_1_adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DutyStep1>> call, Throwable t) {

            }
        });

        // Step2.
        GetDataService getDataService2 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyStep2>> callStep2 = getDataService2.getAllStep2();

        callStep2.enqueue(new Callback<List<DutyStep2>>() {
            @Override
            public void onResponse(Call<List<DutyStep2>> call, Response<List<DutyStep2>> response) {
                dutyStep2List = response.body();

                if(duty_Title_id == 2) {
                    fullPopup_2_adapter = new FullPopup2_Adapter(dutyStep2List);
                    rv_step_item.setLayoutManager(linearLayoutManager);
                    rv_step_item.setAdapter(fullPopup_2_adapter);
                }
            }

            @Override
            public void onFailure(Call<List<DutyStep2>> call, Throwable t) {

            }
        });

        // step3.
        GetDataService getDataService3 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyStep3>> callStep3 = getDataService3.getAllStep3();

        callStep3.enqueue(new Callback<List<DutyStep3>>() {
            @Override
            public void onResponse(Call<List<DutyStep3>> call, Response<List<DutyStep3>> response) {
                dutyStep3List = response.body();

                if(duty_Title_id == 3) {
                    fullPopup_3_adapter = new FullPopup3_Adapter(dutyStep3List);
                    rv_step_item.setLayoutManager(linearLayoutManager);
                    rv_step_item.setAdapter(fullPopup_3_adapter);
                }
            }

            @Override
            public void onFailure(Call<List<DutyStep3>> call, Throwable t) {

            }
        });

        // 확인 버튼 클릭 시,
        btn_OK = findViewById(R.id.btn_OK);
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fullPopup_presenter.saveStep1(step1,1);

                finish();
            }
        });
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(FullPopupActivity.this, "성공", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(FullPopupActivity.this, "실패", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onRequestSuccess(String message) {
//        Toast.makeText(FullPopupActivity.this,
//                message,
//                Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRequestError(String message) {
//        Toast.makeText(FullPopupActivity.this,
//                message,
//                Toast.LENGTH_SHORT).show();
//    }
//    private List<DutyTitle> getTitles() {
//        List<DutyTitle> titleList = new ArrayList<>();
//        for (int i = 0; i < dutyTitleList.size(); i++){
//            titleList.add(i,dutyTitleList.get(i));
//        }
//        return titleList;
//    }

    public List<DutyStep1> getStep1() {
        List<DutyStep1> step1List = new ArrayList<>();
        for (int i = 0; i < dutyStep1List.size(); i++){
            step1List.add(i,dutyStep1List.get(i));
        }
        return step1List;
    }

    @Override
    public void onClick(View v) {
//        Toast.makeText(FullPopupActivity.this, "클릭", Toast.LENGTH_SHORT).show();
    }
//
//    private List<DutyStep2> getStep2() {
//        List<DutyStep2> step2List = new ArrayList<>();
//        for (int i = 0; i < dutyStep2List.size(); i++){
//            step2List.add(i,dutyStep2List.get(i));
//        }
//        return step2List;
//    }
//
//    private List<DutyStep3> getStep3() {
//        List<DutyStep3> step3List = new ArrayList<>();
//        for (int i = 0; i < dutyStep3List.size(); i++){
//            step3List.add(i, dutyStep3List.get(i));
//        }
//        return step3List;
//    }

}