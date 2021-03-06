package com.personal.re_double_rv.Popup_Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.Activity.Popup_Sub.Popup_Sub;
import com.personal.re_double_rv.Activity.Popup_Sub.Popup_Sub_Presenter;
import com.personal.re_double_rv.Activity.Popup_Sub.Popup_View;
import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutyStep3;

import java.util.List;

public class FullPopup3_Adapter extends RecyclerView.Adapter<FullPopup3_Adapter.Step3_ViewHolder> implements Popup_View {

    private Context mContext;

    List<DutyStep3> dutyStep3List;

    Popup_Sub_Presenter popup_sub_presenter;
    Popup_View view;

    public FullPopup3_Adapter(List<DutyStep3> dutyStep3List) {
        this.dutyStep3List = dutyStep3List;
    }


    public class Step3_ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_dutyStep; // 카드뷰
        ImageButton ib_delete_Step; // 삭제하기
        TextView tv_Popup_sub_step; // 단계 내용.

        public Step3_ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_dutyStep = itemView.findViewById(R.id.cv_dutyStep);
            ib_delete_Step = itemView.findViewById(R.id.ib_delete_Step);
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

        // 카드뷰 클릭시.
        holder.cv_dutyStep.setTag(step3.getStep_id());
        holder.cv_dutyStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int step_id = dutyStep3List.get(position).getStep_id();
                String step = dutyStep3List.get(position).getStep3();

                Intent intent = new Intent(mContext, Popup_Sub.class);
                intent.putExtra("step_id",step_id);
                intent.putExtra("step",step);
                mContext.startActivity(intent);
            }
        });

        // 삭제하기.
        holder.ib_delete_Step.setTag(step3.getStep3());
        holder.ib_delete_Step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("제목 삭제").setMessage("정말 삭제하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        popup_sub_presenter = new Popup_Sub_Presenter(view);
                        popup_sub_presenter.deleteStep(dutyStep3List.get(position).getStep_id());
                        remove(position);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dutyStep3List.size();
    }

    // 삭제하기.
    private void remove(int position) {
        try{
            dutyStep3List.remove(position);
            notifyDataSetChanged();
//            notifyItemChanged(position);
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(mContext, "성공", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(mContext, "실패", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<DutyStep> dutyStepList) {

    }


}
