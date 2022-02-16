package com.personal.re_double_rv.Popup_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutyStep2;
import com.personal.re_double_rv.models.DutyTitle;
import com.personal.re_double_rv.title_Adapter.ItemClickListener;
import com.personal.re_double_rv.title_Adapter.Title_Adapter;

import java.util.List;

public class FullPopup1_Adapter extends RecyclerView.Adapter<FullPopup1_Adapter.FullPopup_Step1_ViewHolder> {

    private Context mContext;

    List<DutyStep1> dutyStep1List;
    ItemClickListener itemClickListener;

    public FullPopup1_Adapter(List<DutyStep1> dutyStep1List) {
        this.dutyStep1List = dutyStep1List;
    }

    public class FullPopup_Step1_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv_dutyStep;
        EditText et_Popup_sub_step;
        ImageButton ib_add_step;
        ItemClickListener itemClickListener;

        public FullPopup_Step1_ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            cv_dutyStep = itemView.findViewById(R.id.cv_dutyStep);
            et_Popup_sub_step = itemView.findViewById(R.id.et_Popup_sub_step);
            ib_add_step = itemView.findViewById(R.id.ib_add_step);

            // 아이템 클릭 리스너.
            this.itemClickListener = itemClickListener;
            cv_dutyStep.setOnClickListener(this);
            cv_dutyStep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "클릭" + getAbsoluteAdapterPosition(), Toast.LENGTH_SHORT).show();;
                }
            });
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,getAbsoluteAdapterPosition()); // 클릭할 경우, View & Position 값.
        }
    }


    @NonNull
    @Override
    public FullPopup_Step1_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_full_popup_item,parent,false);

        return new FullPopup_Step1_ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FullPopup_Step1_ViewHolder holder, int position) {

        DutyStep1 step1 = dutyStep1List.get(position);

        holder.et_Popup_sub_step.setText(step1.getStep1());
        holder.ib_add_step.setTag(step1.getTitle_id());

        if(step1.getTitle_id() == 1) {
            holder.ib_add_step.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DutyStep1 step1 = new DutyStep1(dutyStep1List.size(),"",1);
                    dutyStep1List.add(step1);
                    notifyDataSetChanged();
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        return dutyStep1List.size();
    }


}
