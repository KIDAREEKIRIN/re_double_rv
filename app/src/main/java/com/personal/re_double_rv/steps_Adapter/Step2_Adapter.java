package com.personal.re_double_rv.steps_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutyStep2;

import java.util.List;

public class Step2_Adapter extends RecyclerView.Adapter<Step2_Adapter.Step2ViewHolder> {

    private Context step2Context;

    List<DutyStep2> dutyStep2List;

    public Step2_Adapter(List<DutyStep2> dutyStep2List) {
        this.dutyStep2List = dutyStep2List;
    }

    // StepsViewHolder 클래스.
    public class Step2ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_sub_step;

        public Step2ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_sub_step = itemView.findViewById(R.id.tv_sub_step);
        }
    }

    @NonNull
    @Override
    public Step2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        step2Context = parent.getContext();

        View view = LayoutInflater.from(step2Context).inflate(R.layout.layotu_sub_item,parent,false);

        return new Step2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Step2ViewHolder holder, int position) {

        DutyStep2 step2 = dutyStep2List.get(position);
        holder.tv_sub_step.setText(step2.getStep2());

    }

    @Override
    public int getItemCount() {
        return dutyStep2List.size();
    }


}
