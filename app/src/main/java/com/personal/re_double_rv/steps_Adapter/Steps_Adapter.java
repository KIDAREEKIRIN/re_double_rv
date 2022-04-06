package com.personal.re_double_rv.steps_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutySteps;

import java.util.List;

public class Steps_Adapter extends RecyclerView.Adapter<Steps_Adapter.Step_ViewHolder> {

    private Context steps_Adapter_context;

//    List<DutyStep> dutyStepList;

    List<DutySteps> dutyStepsList;

    public Steps_Adapter(List<DutySteps> dutyStepsList,Context steps_Adapter_context) {
        this.steps_Adapter_context = steps_Adapter_context;
        this.dutyStepsList = dutyStepsList;
    }

    //    public Steps_Adapter(List<DutySteps> dutyStepsList) {
//        this.dutyStepsList = dutyStepsList;
//    }

    public class Step_ViewHolder extends RecyclerView.ViewHolder{

//        EditText et_Popup_sub_step;
//        TextView tv_Popup_sub_step;
        CheckedTextView ct_sub_step;


        public Step_ViewHolder(@NonNull View itemView) {
            super(itemView);

            ct_sub_step = itemView.findViewById(R.id.ct_sub_step);
//            tv_Popup_sub_step = itemView.findViewById(R.id.tv_Popup_sub_step);
//            et_Popup_sub_step = itemView.findViewById(R.id.tv_Popup_sub_step);
        }
    }

    @NonNull
    @Override
    public Steps_Adapter.Step_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Context를 dutyStepsList와 함께 생성자에 포함시키면, 여기에 안 써줘도 됨.
//        steps_Adapter_context = parent.getContext();

        View view = LayoutInflater.from(steps_Adapter_context).inflate(R.layout.layout_sub_item,parent,false);

        return new Step_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Steps_Adapter.Step_ViewHolder holder, int position) {
        DutySteps dutySteps = dutyStepsList.get(position);

        holder.ct_sub_step.setText(dutySteps.getStep_name());

        // 추가 수정 삭제를 하려면 하나의 어댑터에서 작동시켜야 한다..
        // 주어진 어댑터에서 사용한도 초과.
        // 하드코딩이 아닌 숫자 n을 사용할 것. 04.05. 1대1 성호님 하브루타 중 확인.
//            DutySteps dutySteps1 = dutyStepsList.get(position);
            // ct_sub_step에 dutySteps의 업무 step 이름을 붙인다.
//            holder.ct_sub_step.setText(dutySteps.getStep_name());
//            holder.ct_sub_step.setText(dutySteps.getStep_name());


//        holder.ct_sub_step.setText(dutySteps.getStep_name());

    }

    @Override
    public int getItemCount() {
        return dutyStepsList.size();
    }


}
