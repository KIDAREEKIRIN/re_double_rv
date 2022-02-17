package com.personal.re_double_rv.steps_Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
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

//        TextView tv_sub_step;
        CheckedTextView ct_sub_step;

        public Step1ViewHolder(@NonNull View itemView) {
            super(itemView);

            ct_sub_step = itemView.findViewById(R.id.ct_sub_step);
        }
    }

    @NonNull
    @Override
    public Step1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        step1Context = parent.getContext();

        View view = LayoutInflater.from(step1Context).inflate(R.layout.layout_sub_item,parent,false);

        return new Step1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Step1ViewHolder holder, int position) {

        DutyStep1 step1 = dutyStep1List.get(position);
        holder.ct_sub_step.setText(step1.getStep1());
        holder.ct_sub_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ct_sub_step.toggle();
                if(holder.ct_sub_step.isChecked()) {
                    holder.ct_sub_step.setPaintFlags(holder.ct_sub_step.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    holder.ct_sub_step.setPaintFlags(0);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return dutyStep1List.size();
    }


}
