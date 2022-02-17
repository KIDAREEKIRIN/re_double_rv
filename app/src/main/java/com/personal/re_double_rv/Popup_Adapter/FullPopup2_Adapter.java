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
import com.personal.re_double_rv.models.DutyStep2;

import java.util.List;

public class FullPopup2_Adapter extends RecyclerView.Adapter<FullPopup2_Adapter.FullPopup_Step2_ViewHolder> implements Popup_View{

    private Context mContext;

    List<DutyStep2> dutyStep2List;

    Popup_Sub_Presenter popup_sub_presenter;
    Popup_View view;

    public FullPopup2_Adapter(List<DutyStep2> dutyStep2List) {
        this.dutyStep2List = dutyStep2List;
    }


    public class FullPopup_Step2_ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_dutyStep; // 카드뷰
        ImageButton ib_delete_Step; // 삭제하기
        TextView tv_Popup_sub_step; // 단계 내용.

        public FullPopup_Step2_ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_dutyStep = itemView.findViewById(R.id.cv_dutyStep);
            ib_delete_Step = itemView.findViewById(R.id.ib_delete_Step);
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

        // 카드뷰 클릭시.
        holder.cv_dutyStep.setTag(step2.getStep_id());
        holder.cv_dutyStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int step_id = dutyStep2List.get(position).getStep_id();
                String step = dutyStep2List.get(position).getStep2();

                Intent intent = new Intent(mContext, Popup_Sub.class);
                intent.putExtra("step_id",step_id);
                intent.putExtra("step",step);
                mContext.startActivity(intent);
            }
        });


        // 삭제하기.
        holder.ib_delete_Step.setTag(step2.getStep2());
        holder.ib_delete_Step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("제목 삭제").setMessage("정말 삭제하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        popup_sub_presenter = new Popup_Sub_Presenter(view);
                        popup_sub_presenter.deleteStep(dutyStep2List.get(position).getStep_id());
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

    // 삭제하기.
    private void remove(int position) {
        try{
            dutyStep2List.remove(position);
            notifyDataSetChanged();
//            notifyItemChanged(position);
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dutyStep2List.size();
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
