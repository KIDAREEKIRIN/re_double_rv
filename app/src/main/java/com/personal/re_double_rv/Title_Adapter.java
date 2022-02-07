package com.personal.re_double_rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutyStep2;
import com.personal.re_double_rv.models.DutyTitle;
import com.personal.re_double_rv.steps_Adapter.Step1_Adapter;
import com.personal.re_double_rv.steps_Adapter.Step2_Adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Title_Adapter extends RecyclerView.Adapter<Title_Adapter.TitleViewHolder>{

    List<DutyTitle> dutyTitleList; // 메인 액티비티 리사이클러뷰에 들어갈 리스트.
    List<DutyStep1> dutyStep1List; // step1 리스트에 들어갈 리스트.
    List<DutyStep2> dutyStep2List;
    private Context title_Adapter_Context;
    LinearLayoutManager linearLayoutManager;

    // 생성자 선언해서 건네주기. -> 메인으로.
    public Title_Adapter(List<DutyTitle> dutyTitleList) {
        this.dutyTitleList = dutyTitleList;
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder{

        TextView tv_duty_title;
        RecyclerView rv_step_item;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_duty_title = itemView.findViewById(R.id.tv_duty_title);
            rv_step_item = itemView.findViewById(R.id.rv_step_item);
        }
    }

    @NonNull
    @Override
    public Title_Adapter.TitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        title_Adapter_Context = parent.getContext();

        View view = LayoutInflater.from(title_Adapter_Context).inflate(R.layout.layout_item,parent, false);
        return new TitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Title_Adapter.TitleViewHolder holder, int position) {
        DutyTitle dutyTitle = dutyTitleList.get(position);
//        DutyStep2 dutyStep2 = dutyStep2List.get(position);
        holder.tv_duty_title.setText(dutyTitle.getTitle_name()); // title을 가져와서 붙임.

        // DutyStep1에 대한 내용.
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyStep1>> callStep1 = getDataService.getAllStep1();

        callStep1.enqueue(new Callback<List<DutyStep1>>() {
            @Override
            public void onResponse(Call<List<DutyStep1>> call, Response<List<DutyStep1>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutyStep1List = response.body();

                    // 자식 레이아웃 설정.
                    linearLayoutManager = new LinearLayoutManager(
                            holder.rv_step_item.getContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                    );
                    // 시기별 업무 올리기.
                    if(dutyTitle.getTitle_id() == 1) {
                        Step1_Adapter step1_adapter = new Step1_Adapter(getStep1());
                        holder.rv_step_item.setLayoutManager(linearLayoutManager);
                        holder.rv_step_item.setAdapter(step1_adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DutyStep1>> call, Throwable t) {

            }
        });

        // step2에 대한 내용.
        GetDataService getDataService2 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyStep2>> callStep2 = getDataService2.getAllStep2();

        callStep2.enqueue(new Callback<List<DutyStep2>>() {
            @Override
            public void onResponse(Call<List<DutyStep2>> call, Response<List<DutyStep2>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutyStep2List = response.body();

                    // 자식 레이아웃 설정.
                    linearLayoutManager = new LinearLayoutManager(
                            holder.rv_step_item.getContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                    );
                    // 시기별 업무 올리기.
                    if(dutyTitle.getTitle_id() == 2) {
                        Step2_Adapter step2_adapter = new Step2_Adapter(getStep2());
                        holder.rv_step_item.setLayoutManager(linearLayoutManager);
                        holder.rv_step_item.setAdapter(step2_adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DutyStep2>> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dutyTitleList.size();
    }

    // 첫번째 메인 액티비티 리사이클러뷰 안의 카드뷰 안에 들어갈 리스트.
    // Step1.
    private List<DutyStep1> getStep1() {
        List<DutyStep1> step1List = new ArrayList<>();
        for (int i = 0; i < dutyStep1List.size(); i++){
            step1List.add(i,dutyStep1List.get(i));
        }
        return step1List;
    }

    // Step2.
    private List<DutyStep2> getStep2() {
        List<DutyStep2> step2List = new ArrayList<>();
        for (int i = 0; i < dutyStep2List.size(); i++){
             step2List.add(i,dutyStep2List.get(i));
            if(dutyStep2List.get(i) == null) {
                step2List.remove(i);
            }
        }
        return step2List;
    }


}
