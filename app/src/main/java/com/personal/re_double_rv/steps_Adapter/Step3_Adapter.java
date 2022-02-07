package com.personal.re_double_rv.steps_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutyStep3;

import java.util.List;

public class Step3_Adapter extends RecyclerView.Adapter<Step3_Adapter.Step3ViewHolder> {
    private Context step3Context;

    List<DutyStep3> dutyStep3List;

    public Step3_Adapter(List<DutyStep3> dutyStep3List) {
        this.dutyStep3List = dutyStep3List;
    }

    public class Step3ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_sub_step;

        public Step3ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_sub_step = itemView.findViewById(R.id.tv_sub_step);
        }
    }

    @NonNull
    @Override
    public Step3_Adapter.Step3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        step3Context = parent.getContext();

        View view = LayoutInflater.from(step3Context).inflate(R.layout.layout_sub_item,parent,false);

        return new Step3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Step3_Adapter.Step3ViewHolder holder, int position) {

        DutyStep3 step3 = dutyStep3List.get(position);
        holder.tv_sub_step.setText(step3.getStep3());
    }

    @Override
    public int getItemCount() {
        return dutyStep3List.size();
    }


}
