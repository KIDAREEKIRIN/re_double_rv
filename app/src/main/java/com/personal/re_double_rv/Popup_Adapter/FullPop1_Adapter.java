package com.personal.re_double_rv.Popup_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.Activity.Popup_Sub.Popup_Sub;
import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutySteps1;
import com.personal.re_double_rv.title_Adapter.ItemClickListener;

import java.util.List;

public class FullPop1_Adapter extends RecyclerView.Adapter<FullPop1_Adapter.FullPop_Steps1_ViewHolder> {

    private Context fullPop1_Context; // Context 객체 생성.

    List<DutySteps1> dutySteps1List; // DutySteps1 리스트 선언.

    ItemClickListener itemClickListener;

    // 생성자 만들기. FullPop1_Adapter 에 대한.
    public FullPop1_Adapter(List<DutySteps1> dutySteps1List) {
        this.dutySteps1List = dutySteps1List;
    }

    public class FullPop_Steps1_ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_dutyStep; // CardView 선언.
        ImageButton ib_delete_Step, ib_tip; // 삭제, 팁 ImageButton 선언.
        TextView tv_Popup_sub_step; // TextView 실제 업무 step_name 이 들어가는 곳.

        public FullPop_Steps1_ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            cv_dutyStep = itemView.findViewById(R.id.cv_dutyStep);
            ib_delete_Step = itemView.findViewById(R.id.ib_delete_Step);
//            ib_tip = itemView.findViewById(R.id.ib_tip);
            tv_Popup_sub_step = itemView.findViewById(R.id.tv_Popup_sub_step);

        }
    }

    @NonNull
    @Override
    public FullPop_Steps1_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        fullPop1_Context = parent.getContext();

        View view = LayoutInflater.from(fullPop1_Context).inflate(R.layout.layout_full_popup_item,parent,false);

        return new FullPop_Steps1_ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FullPop_Steps1_ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DutySteps1 steps1 = dutySteps1List.get(position);

        holder.cv_dutyStep.setTag(steps1.getStep_id());
        holder.cv_dutyStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int step_id = dutySteps1List.get(position).getStep_id(); // int step_id 값 정의하기.
                String step_name = dutySteps1List.get(position).getStep_name(); // String step_name 값 정의하기.

                // Intent를 활용하여 Activity 값 넘기기. Popup_Sub 클래스로 intent에 저장한 값을 넘긴다.
                Intent intent = new Intent(fullPop1_Context, Popup_Sub.class);
                intent.putExtra("step_id", step_id); // 업무 step 인덱스 값.
                intent.putExtra("step_name", step_name); // 업무 step 이름.
                fullPop1_Context.startActivity(intent);
            }
        });
        // tv_Popup_sub_step의 업무 step 이름 값을 넣음.
        holder.tv_Popup_sub_step.setText(steps1.getStep_name());

    }

    @Override
    public int getItemCount() {
        // steps1List( title_order 값이 "1") 인 리스트의 사이즈로 총 합계를 낸다.
        return dutySteps1List.size();
    }


}
