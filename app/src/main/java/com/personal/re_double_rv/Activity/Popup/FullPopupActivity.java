package com.personal.re_double_rv.Activity.Popup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.personal.re_double_rv.Activity.Popup_Sub.Popup_Sub;
import com.personal.re_double_rv.Activity.Popup_Sub.Popup_Sub_Presenter;
import com.personal.re_double_rv.Activity.Popup_Sub.Popup_View;
import com.personal.re_double_rv.Popup_Adapter.FullPop1_Adapter;
import com.personal.re_double_rv.Popup_Adapter.FullPopup1_Adapter;
import com.personal.re_double_rv.Popup_Adapter.FullPopup2_Adapter;
import com.personal.re_double_rv.Popup_Adapter.FullPopup3_Adapter;
import com.personal.re_double_rv.R;
import com.personal.re_double_rv.Retrofit.DutyStep.ApiRetroDataStep;
import com.personal.re_double_rv.Retrofit.DutyStep.RetrofitClientStep;
import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutyStep2;
import com.personal.re_double_rv.models.DutyStep3;
import com.personal.re_double_rv.models.DutySteps;
import com.personal.re_double_rv.models.DutySteps1;
import com.personal.re_double_rv.models.DutyTitle;
import com.personal.re_double_rv.steps_Adapter.Steps_Adapter;
import com.personal.re_double_rv.title_Adapter.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullPopupActivity extends AppCompatActivity implements FullPopup_View, Popup_View, View.OnClickListener {

    private static final int INTENT_EDIT = 200;
    private static final int INTENT_ADD = 100;
    CardView cv_dutyTitle;
//    TextView tv_duty_title_id, tv_duty_title; ?????? ???.
    TextView tv_duty_titleName, tv_duty_titleOrder;
    ImageButton ib_delete_Title, ib_edit_Title, ib_edit_Step;
    Button btn_OK;

    RecyclerView rv_step_item;// ??????????????????

    LinearLayoutManager linearLayoutManager;// ????????? ???????????? ?????????.
    Context fullPopup_context;

    // ????????? ????????????.
    Steps_Adapter steps_adapter; // Steps ?????????.
    FullPopup1_Adapter fullPopup_1_adapter; // step1 ?????????
    FullPopup2_Adapter fullPopup_2_adapter; // step2 ?????????
    FullPopup3_Adapter fullPopup_3_adapter; // step3 ?????????

    // ????????? ?????????.
    FullPop1_Adapter fullPop1_adapter;

    // Title + Step ?????????.
    List<DutyTitle> dutyTitleList;
//    List<DutyStep> dutyStepList;
    List<DutyStep1> dutyStep1List;
    List<DutyStep2> dutyStep2List;
    List<DutyStep3> dutyStep3List;
    List<DutySteps> dutyStepsList;
    // ????????????.

    // ????????? ?????????.
    List<DutySteps1> dutySteps1List;

    FullPopup_Presenter fullPopup_presenter; //?????? Presenter
    Popup_Sub_Presenter popup_sub_presenter;
    Popup_View view;

    // ????????? title_name.
//    String duty_Title; ?????? ??????.
    String duty_titleName;
    // ????????? title_order
//    int duty_Title_id; // ?????? ??????.
    int duty_titleOrder;
    // ?????? Fab ??????.
    FloatingActionButton fab;
    // ????????? ?????? ?????????.
    ItemClickListener itemClickListener;

    private static final String TAG = "fAB ?????? ???,";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_popup);

        fullPopup_presenter = new FullPopup_Presenter( this); //FullPopup_Presenter ??????.

        // titleOrder + titleName (?????? ?????? + ?????? ??????) findViewById. -> Id ?????? ????????? ???????????? ???????????? Method.
        tv_duty_titleOrder = findViewById(R.id.tv_duty_titleOrder); // ?????? titleOrder ??????.
        tv_duty_titleName = findViewById(R.id.tv_duty_titleName); // ?????? titleName ??????.
//        tv_duty_title = findViewById(R.id.tv_duty_title); // title
//        tv_duty_title_id = findViewById(R.id.tv_duty_title_id); // title_id
        // Intent ??? ????????????. MainActivity ??? Title_Adapter ?????? ????????? Intent ???.
        Intent intent = getIntent();
//         Id ?????? ????????? String?????? ????????????.
        duty_titleOrder = intent.getIntExtra("title_order",0); // ?????? title ??????.
        tv_duty_titleOrder.setText(String.valueOf(duty_titleOrder)); // tv_duty_titleOrder??? titleOrder??? ?????? String????????? ?????????.
//        duty_Title_id = intent.getIntExtra("duty_Title_id",0);
//        tv_duty_title_id.setText(String.valueOf(duty_Title_id));

        // Title??? ????????? ????????????.
        duty_titleName = intent.getStringExtra("title_name"); // ?????? titleName.
        tv_duty_titleName.setText(duty_titleName); // tv_duty_titleName ??? duty_titleName??? ?????? ?????????.
//        duty_Title = intent.getStringExtra("duty_Title");
//        tv_duty_title.setText(duty_Title);

        // ??????????????????.
        rv_step_item = findViewById(R.id.rv_step_item);
        rv_step_item.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(FullPopupActivity.this);

        // ?????? steps ????????????(read).
//        fullPopup_presenter.getAllSteps();


        // DutyStep ??????(Insert)??????.
        fab = findViewById(R.id.add_subItem);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goStepSub = new Intent(FullPopupActivity.this, Popup_Sub.class);
//                goSub.putExtra("title_id_value",duty_Title_id); ?????? ??????.
                goStepSub.putExtra("step_name",""); // ?????? ?????? ??? ????????? ???. 04.02.
                goStepSub.putExtra("title_order",duty_titleOrder); // ?????? title ????????? ????????? ?????????.
                startActivity(goStepSub); // intent ??? ?????????.
            }
        });

        // DutySteps1 ????????????. Read
        // Step1.
        ApiRetroDataStep getDataSteps = RetrofitClientStep.getRetrofitClient().create(ApiRetroDataStep.class);
        Call<List<DutySteps1>> callSteps1 = getDataSteps.readAllSteps1(); // ?????? ???????????? ?????? ?????????.

        callSteps1.enqueue(new Callback<List<DutySteps1>>() {
            @Override
            public void onResponse(Call<List<DutySteps1>> call, Response<List<DutySteps1>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutySteps1List = response.body();

//                    dutySteps1List = new ArrayList<>();
//                    dutySteps1List.addAll(getSteps1()); // ???????????? ??????. ?????? getSteps1()??? ???????????? ??????.

                    // duty_titleName ?????? title ????????? "?????? ??????" ???????
                    if(duty_titleOrder == 1) {
                        // ????????? FullPopup_Activity ??????.
//                        fullPop1_adapter = new FullPop1_Adapter(getSteps1())
                        fullPop1_adapter = new FullPop1_Adapter(getSteps1());
                        rv_step_item.setLayoutManager(linearLayoutManager);
                        rv_step_item.setAdapter(fullPop1_adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DutySteps1>> call, Throwable t) {

            }
        });
        // DutySteps ???????????? Read. ?????? ??????.
//        //Step1.
//        GetDataService getDataService1 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<DutyStep1>> callStep1 = getDataService1.getAllStep1();
//
//        callStep1.enqueue(new Callback<List<DutyStep1>>() {
//            @Override
//            public void onResponse(Call<List<DutyStep1>> call, Response<List<DutyStep1>> response) {
//                if(response.isSuccessful() && response.body() != null) {
//                    dutyStep1List = response.body();
//
////                    dutyStep1List = new ArrayList<>();
////                    dutyStep1List.addAll(getStep1());
//
//                    if(duty_Title_id == 1){
//                        fullPopup_1_adapter = new FullPopup1_Adapter(getStep1());
//                        rv_step_item.setLayoutManager(linearLayoutManager);
//                        rv_step_item.setAdapter(fullPopup_1_adapter);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DutyStep1>> call, Throwable t) {
//
//            }
//        });
//
//        // Step2.
//        GetDataService getDataService2 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<DutyStep2>> callStep2 = getDataService2.getAllStep2();
//
//        callStep2.enqueue(new Callback<List<DutyStep2>>() {
//            @Override
//            public void onResponse(Call<List<DutyStep2>> call, Response<List<DutyStep2>> response) {
//                dutyStep2List = response.body();
//
//
//                if(duty_Title_id == 2) {
//                    fullPopup_2_adapter = new FullPopup2_Adapter(getStep2());
//                    rv_step_item.setLayoutManager(linearLayoutManager);
//                    rv_step_item.setAdapter(fullPopup_2_adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DutyStep2>> call, Throwable t) {
//
//            }
//        });
//
//        // step3.
//        GetDataService getDataService3 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<DutyStep3>> callStep3 = getDataService3.getAllStep3();
//
//        callStep3.enqueue(new Callback<List<DutyStep3>>() {
//            @Override
//            public void onResponse(Call<List<DutyStep3>> call, Response<List<DutyStep3>> response) {
//                dutyStep3List = response.body();
//
//                if(duty_Title_id == 3) {
//                    fullPopup_3_adapter = new FullPopup3_Adapter(getStep3());
//                    rv_step_item.setLayoutManager(linearLayoutManager);
//                    rv_step_item.setAdapter(fullPopup_3_adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DutyStep3>> call, Throwable t) {
//
//            }
//        });


//        Intent editStep = getIntent();
//        editStep.getIntExtra("step_id",0);
//        editStep.getStringExtra("step");
//
//        ib_edit_Step = findViewById(R.id.ib_edit_Step);
//        ib_edit_Step.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popup_sub_presenter = new Popup_Sub_Presenter(view);
//                popup_sub_presenter.updateStep(editStep.getIntExtra("step_id",0),editStep.getStringExtra("step"));
//            }
//        });

    }

    // ???????????? ?????? ??????????????????.
    @Override
    protected void onResume() {
        super.onResume();
        // ??????????????????.
        rv_step_item = findViewById(R.id.rv_step_item);
        rv_step_item.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(FullPopupActivity.this);

        // Step1.
        ApiRetroDataStep getDataSteps = RetrofitClientStep.getRetrofitClient().create(ApiRetroDataStep.class);
        Call<List<DutySteps1>> callSteps1 = getDataSteps.readAllSteps1(); // ?????? ???????????? ?????? ?????????.

        callSteps1.enqueue(new Callback<List<DutySteps1>>() {
            @Override
            public void onResponse(Call<List<DutySteps1>> call, Response<List<DutySteps1>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutySteps1List = response.body();

//                    dutySteps1List = new ArrayList<>();
//                    dutySteps1List.addAll(getSteps1()); // ???????????? ??????. ?????? getSteps1()??? ???????????? ??????.

                    // duty_titleName ?????? title ????????? "?????? ??????" ??????? ??? ??????.
                    // duty_titleOrder ?????? title ????????? "1"???????
                    if(duty_titleOrder == 1) {
                        // ????????? FullPopup_Activity ??????.
                        fullPop1_adapter = new FullPop1_Adapter(getSteps1());
                        rv_step_item.setLayoutManager(linearLayoutManager);
                        rv_step_item.setAdapter(fullPop1_adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DutySteps1>> call, Throwable t) {

            }
        });
        // ?????? Step1, Step2, Step3 ???????????? ??????.
//        //Step1.
//        GetDataService getDataService1 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<DutyStep1>> callStep1 = getDataService1.getAllStep1();
//
//        callStep1.enqueue(new Callback<List<DutyStep1>>() {
//            @Override
//            public void onResponse(Call<List<DutyStep1>> call, Response<List<DutyStep1>> response) {
//                if(response.isSuccessful() && response.body() != null) {
//                    dutyStep1List = response.body();
//
//                    if(duty_Title_id == 1){
//                        fullPopup_1_adapter = new FullPopup1_Adapter(getStep1());
//                        rv_step_item.setLayoutManager(linearLayoutManager);
//                        rv_step_item.setAdapter(fullPopup_1_adapter);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DutyStep1>> call, Throwable t) {
//
//            }
//        });
//
//        // Step2.
//        GetDataService getDataService2 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<DutyStep2>> callStep2 = getDataService2.getAllStep2();
//
//        callStep2.enqueue(new Callback<List<DutyStep2>>() {
//            @Override
//            public void onResponse(Call<List<DutyStep2>> call, Response<List<DutyStep2>> response) {
//                dutyStep2List = response.body();
//
//
//                if(duty_Title_id == 2) {
//                    fullPopup_2_adapter = new FullPopup2_Adapter(getStep2());
//                    rv_step_item.setLayoutManager(linearLayoutManager);
//                    rv_step_item.setAdapter(fullPopup_2_adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DutyStep2>> call, Throwable t) {
//
//            }
//        });
//
//        // step3.
//        GetDataService getDataService3 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<DutyStep3>> callStep3 = getDataService3.getAllStep3();
//
//        callStep3.enqueue(new Callback<List<DutyStep3>>() {
//            @Override
//            public void onResponse(Call<List<DutyStep3>> call, Response<List<DutyStep3>> response) {
//                dutyStep3List = response.body();
//
//                if(duty_Title_id == 3) {
//                    fullPopup_3_adapter = new FullPopup3_Adapter(getStep3());
//                    rv_step_item.setLayoutManager(linearLayoutManager);
//                    rv_step_item.setAdapter(fullPopup_3_adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DutyStep3>> call, Throwable t) {
//
//            }
//        });

    }


    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(FullPopupActivity.this, "??????", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(FullPopupActivity.this, "??????", Toast.LENGTH_SHORT).show();
    }

    // ?????? step ??????(update)??????.
    @Override
    public void onGetResult(List<DutyStep> dutyStepList) {
        // ????????? ?????? ?????????(????????? ??????)
        itemClickListener = ((view, position) -> {

            // ????????? ?????? ??? -> Intent??? ??????????????? ?????????.
            int title_id_value = dutyStepList.get(position).getTitle_id();
            int step_id = dutyStepList.get(position).getStep_id();
            String step = dutyStepList.get(position).getStep();
//            String title_name = dutyTitleList.get(position).getTitle_name();
            // Intent??? ?????????.
            Intent editStep = new Intent(FullPopupActivity.this, Popup_Sub.class);
            editStep.putExtra("title_id_value",title_id_value);
            editStep.putExtra("step_id",step_id);
            editStep.putExtra("step",step);
            startActivity(editStep);
//            editTitle.putExtra("title_name",title_name); // ?????? duty_title ??? "Key" + "Value"
//            startActivityForResult(editStep, INTENT_EDIT); // ?????? ??? 200;

        });

//        steps_adapter = new Steps_Adapter(getSteps1());
        steps_adapter = new Steps_Adapter(getSteps(),fullPopup_context); // ?????? ????????? ??? ?????????. -> ??? ????????? ????????????.
        rv_step_item.setLayoutManager(linearLayoutManager);
        rv_step_item.setAdapter(steps_adapter);

//        if(dutyStepList.indexOf(1) == duty_Title_id) {
//            steps_adapter = new Steps_Adapter(getSteps()); // ?????? ????????? ??? ?????????. -> ??? ????????? ????????????.
//            rv_step_item.setLayoutManager(linearLayoutManager);
//            rv_step_item.setAdapter(steps_adapter);
//        } else if (dutyStepList.indexOf(2) == duty_Title_id){
//            steps_adapter = new Steps_Adapter(getSteps()); // ?????? ????????? ??? ?????????. -> ??? ????????? ????????????.
//            rv_step_item.setLayoutManager(linearLayoutManager);
//            rv_step_item.setAdapter(steps_adapter);
//        } else if (dutyStepList.indexOf(3) == duty_Title_id){
//            steps_adapter = new Steps_Adapter(getSteps()); // ?????? ????????? ??? ?????????. -> ??? ????????? ????????????.
//            rv_step_item.setLayoutManager(linearLayoutManager);
//            rv_step_item.setAdapter(steps_adapter);
//        }


    }
    // ????????? ?????? DutySteps1 ??? ?????? ?????????. 04.04.
    public List<DutySteps1> getSteps1() {
        List<DutySteps1> steps1List = new ArrayList<>();
        for ( int i = 0; i < dutySteps1List.size(); i++ ) {
            steps1List.add(i,dutySteps1List.get(i));
        }
        return steps1List;
    }

    // ?????? ?????? Step ????????? ?????????.
    public List<DutySteps> getSteps() {
        List<DutySteps> stepsList = new ArrayList<>();
        for (int i = 0; i < dutyStepsList.size() ; i++){
            stepsList.add(i,dutyStepsList.get(i));
        }
        return stepsList;
    }

    public List<DutyStep1> getStep1() {
        List<DutyStep1> step1List = new ArrayList<>();
        for (int i = 0; i < dutyStep1List.size(); i++){
            step1List.add(i,dutyStep1List.get(i));
        }
        return step1List;
    }

    public List<DutyStep2> getStep2() {
        List<DutyStep2> step2List = new ArrayList<>();
        for (int i = 0; i < dutyStep2List.size(); i++){
            step2List.add(i,dutyStep2List.get(i));
        }
        return step2List;
    }
    public List<DutyStep3> getStep3() {
        List<DutyStep3> step3List = new ArrayList<>();
        for (int i = 0; i < dutyStep3List.size(); i++){
            step3List.add(i,dutyStep3List.get(i));
        }
        return step3List;
    }


    @Override
    public void onClick(View v) {
//        Toast.makeText(FullPopupActivity.this, "??????", Toast.LENGTH_SHORT).show();
    }


}