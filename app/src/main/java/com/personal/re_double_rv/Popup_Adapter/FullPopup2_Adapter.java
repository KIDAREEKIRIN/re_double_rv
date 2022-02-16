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
import com.personal.re_double_rv.models.DutyStep2;

import java.util.List;

public class FullPopup2_Adapter extends RecyclerView.Adapter<FullPopup2_Adapter.FullPopup_Step2_ViewHolder> {

    private Context mContext;

    List<DutyStep2> dutyStep2List;

    public FullPopup2_Adapter(List<DutyStep2> dutyStep2List) {
        this.dutyStep2List = dutyStep2List;
    }

    public class FullPopup_Step2_ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Popup_sub_step;

        public FullPopup_Step2_ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_Popup_sub_step = itemView.findViewById(R.id.tv_Popup_sub_step);
        }
    }

    @NonNull
    @Override
    public FullPopup2_Adapter.FullPopup_Step2_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_full_popup_item,parent,false);

        return new FullPopup_Step2_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FullPopup2_Adapter.FullPopup_Step2_ViewHolder holder, int position) {
        DutyStep2 step2 = dutyStep2List.get(position);
        holder.tv_Popup_sub_step.setText(step2.getStep2());
    }

    @Override
    public int getItemCount() {
        return dutyStep2List.size();
    }


}
