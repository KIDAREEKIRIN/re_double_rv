package com.personal.re_double_rv.steps_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutySteps1;

import java.util.List;

public class Steps1_Adapter extends RecyclerView.Adapter<Steps1_Adapter.Steps1ViewHolder> {

    private Context steps1Context; // Context 객체 생성.

    List<DutySteps1> dutySteps1List; // DutySteps1의 List 추가.

    // Steps1_Adapter의 dutySteps1List 생성자.
    public Steps1_Adapter(List<DutySteps1> dutySteps1List) {
        this.dutySteps1List = dutySteps1List;
    }

    // Steps1ViewHolder 클래스 생성.
    public class Steps1ViewHolder extends RecyclerView.ViewHolder{

        // CheckedTextView 선언.
        CheckedTextView ct_sub_step;

        public Steps1ViewHolder(@NonNull View itemView) {
            super(itemView);

            ct_sub_step = itemView.findViewById(R.id.ct_sub_step);
        }
    }

    @NonNull
    @Override
    public Steps1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        steps1Context = parent.getContext();
        View view = LayoutInflater.from(steps1Context).inflate(R.layout.layout_sub_item,parent,false);
        return new Steps1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Steps1ViewHolder holder, int position) {
        DutySteps1 steps1 = dutySteps1List.get(position); // steps1에 dutySteps1List의 포지션 값을 할당.

        holder.ct_sub_step.setText(steps1.getStep_name());// step_name 할당.;

    }

    @Override
    public int getItemCount() {
        return dutySteps1List.size();
    }


}
