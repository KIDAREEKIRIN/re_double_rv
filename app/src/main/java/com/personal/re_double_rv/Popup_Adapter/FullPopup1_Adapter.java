package com.personal.re_double_rv.Popup_Adapter;

import android.annotation.SuppressLint;
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
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutyStep2;
import com.personal.re_double_rv.models.DutyTitle;
import com.personal.re_double_rv.title_Adapter.ItemClickListener;
import com.personal.re_double_rv.title_Adapter.Title_Adapter;

import java.util.ArrayList;
import java.util.List;

public class FullPopup1_Adapter extends RecyclerView.Adapter<FullPopup1_Adapter.FullPopup_Step1_ViewHolder> implements Popup_View {

    private Context mContext;

    List<DutyStep1> dutyStep1List;
    ItemClickListener itemClickListener;

    Popup_Sub_Presenter popup_sub_presenter;
    Popup_View view;

    public FullPopup1_Adapter(List<DutyStep1> dutyStep1List) {
        this.dutyStep1List = dutyStep1List;
    }

    public class FullPopup_Step1_ViewHolder extends RecyclerView.ViewHolder{

        CardView cv_dutyStep;
        ImageButton ib_delete_Step;
        TextView tv_Popup_sub_step;

        public FullPopup_Step1_ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            cv_dutyStep = itemView.findViewById(R.id.cv_dutyStep);
            ib_delete_Step = itemView.findViewById(R.id.ib_delete_Step);
            tv_Popup_sub_step = itemView.findViewById(R.id.tv_Popup_sub_step);

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
    public void onBindViewHolder(@NonNull FullPopup_Step1_ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DutyStep1 step1 = dutyStep1List.get(position);
//        dutyStep1List = new ArrayList<>();
//        dutyStep1List.addAll()

        holder.cv_dutyStep.setTag(step1.getStep_id());
        holder.cv_dutyStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int step_id = dutyStep1List.get(position).getStep_id();
                String step = dutyStep1List.get(position).getStep1();

                Intent intent = new Intent(mContext,Popup_Sub.class);
                intent.putExtra("step_id",step_id);
                intent.putExtra("step",step);
                mContext.startActivity(intent);
            }
        });

        holder.tv_Popup_sub_step.setText(step1.getStep1());

        // 삭제하기.
        holder.ib_delete_Step.setTag(step1.getStep1());
        holder.ib_delete_Step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("제목 삭제").setMessage("정말 삭제하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        popup_sub_presenter = new Popup_Sub_Presenter(view);
                        popup_sub_presenter.deleteStep(dutyStep1List.get(position).getStep_id());
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
          dutyStep1List.remove(position);
          notifyDataSetChanged();
//          notifyItemChanged(position);
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
         }
    }

    @Override
    public int getItemCount() {
        return dutyStep1List.size();
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
