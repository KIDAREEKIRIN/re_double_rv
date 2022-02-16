package com.personal.re_double_rv.steps_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutyStep;

import java.util.List;

public class Steps_Adapter extends RecyclerView.Adapter<Steps_Adapter.Step_ViewHolder> {

    Context mContext;

    List<DutyStep> dutyStepList;

    public Steps_Adapter(List<DutyStep> dutyStepList) {
        this.dutyStepList = dutyStepList;
    }

    public class Step_ViewHolder extends RecyclerView.ViewHolder{

        EditText et_Popup_sub_step;

        public Step_ViewHolder(@NonNull View itemView) {
            super(itemView);

            et_Popup_sub_step = itemView.findViewById(R.id.tv_Popup_sub_step);
        }
    }

    @NonNull
    @Override
    public Steps_Adapter.Step_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_full_popup_item,parent,false);

        return new Step_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Steps_Adapter.Step_ViewHolder holder, int position) {
        DutyStep dutyStep = dutyStepList.get(position);
        holder.et_Popup_sub_step.setText(dutyStep.getStep());

    }

    @Override
    public int getItemCount() {
        return dutyStepList.size();
    }


}
