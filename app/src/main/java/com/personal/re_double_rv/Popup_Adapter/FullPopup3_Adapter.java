package com.personal.re_double_rv.Popup_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutyStep3;

import java.util.List;

public class FullPopup3_Adapter extends RecyclerView.Adapter<FullPopup3_Adapter.Step3_ViewHolder> {

    private Context mContext;

    List<DutyStep3> dutyStep3List;

    public FullPopup3_Adapter(List<DutyStep3> dutyStep3List) {
        this.dutyStep3List = dutyStep3List;
    }

    public class Step3_ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Popup_sub_step;

        public Step3_ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_Popup_sub_step = itemView.findViewById(R.id.tv_Popup_sub_step);
        }
    }


    @NonNull
    @Override
    public Step3_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext =parent.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_full_popup_item,parent,false);
        return new Step3_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Step3_ViewHolder holder, int position) {
        DutyStep3 step3 = dutyStep3List.get(position);

        holder.tv_Popup_sub_step.setText(step3.getStep3());
    }

    @Override
    public int getItemCount() {
        return dutyStep3List.size();
    }


}
