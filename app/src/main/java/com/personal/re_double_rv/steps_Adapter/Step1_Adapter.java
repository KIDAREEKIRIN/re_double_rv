package com.personal.re_double_rv.steps_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.R;

import java.util.List;

public class Step1_Adapter extends RecyclerView.Adapter<Step1_Adapter.Step1ViewHolder> {

    private Context step1Context;

    List<DutyStep1> dutyStep1List;

    public Step1_Adapter(List<DutyStep1> dutyStep1List) {
        this.dutyStep1List = dutyStep1List;
    }

    // StepsViewHolder 클래스.
    public class Step1ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_sub_step;

        public Step1ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_sub_step = itemView.findViewById(R.id.tv_sub_step);
        }
    }

    @NonNull
    @Override
    public Step1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        step1Context = parent.getContext();

        View view = LayoutInflater.from(step1Context).inflate(R.layout.layotu_sub_item,parent,false);

        return new Step1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Step1ViewHolder holder, int position) {

        DutyStep1 step1 = dutyStep1List.get(position);
        holder.tv_sub_step.setText(step1.getStep1());

    }

    @Override
    public int getItemCount() {
        return dutyStep1List.size();
    }


}
