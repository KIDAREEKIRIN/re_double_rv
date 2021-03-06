package com.personal.re_double_rv.steps_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.Activity.main.Main_Presenter;
import com.personal.re_double_rv.Activity.main.Main_View;
import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
import com.personal.re_double_rv.models.DutyFile;
import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutyTip;
import com.personal.re_double_rv.models.DutyTitle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Step1_Adapter extends RecyclerView.Adapter<Step1_Adapter.Step1ViewHolder> implements Main_View {

    private Context step1Context;

    List<DutyStep1> dutyStep1List;
    List<DutyFile> dutyFileList;
    List<DutyTip> dutyTipList;

    private int checkPosition, filePosition, tipPosition;

    Main_Presenter main_presenter;
    Main_View view;

    public Step1_Adapter(List<DutyStep1> dutyStep1List) {
        this.dutyStep1List = dutyStep1List;
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(step1Context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(step1Context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<DutyTitle> dutyTitleList) {

    }

    @Override
    public void onGetSteps(List<DutyStep> dutyStepList) {

    }

    // StepsViewHolder ?????????.
    public class Step1ViewHolder extends RecyclerView.ViewHolder {

//        TextView tv_sub_step;
        CheckedTextView ct_sub_step; // ????????????
        ImageButton ib_file, ib_tip; // ??????, ???.


        public Step1ViewHolder(@NonNull View itemView) {
            super(itemView);

            ct_sub_step = itemView.findViewById(R.id.ct_sub_step);
            ib_file = itemView.findViewById(R.id.ib_file);
            ib_tip = itemView.findViewById(R.id.ib_tip);
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
    public void onBindViewHolder(@NonNull Step1ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DutyStep1 step1 = dutyStep1List.get(position);

        tipPosition = step1.getTip_boolean(); // Tip boolean ??? ????????????.
        filePosition = step1.getFile_boolean(); // file boolean ??? ????????????.
        checkPosition = step1.getCheck_boolean(); // ???????????? boolean ??? ????????????.

        holder.ct_sub_step.setTag(step1.getCheck_boolean()); // check_boolean ?????? ??????.
        holder.ct_sub_step.setText(step1.getStep1()); // step1??? ????????? ??????. setText();
        main_presenter = new Main_Presenter(view);
        // check???????????????.
        if(step1.getCheck_boolean() == 1) {
            holder.ct_sub_step.toggle(); // ??????????????????.
            holder.ct_sub_step.isChecked(); // ??????????????????.
            // CheckedTextView ?????????,
            holder.ct_sub_step.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ???????????? ?????? ??????.
                    holder.ct_sub_step.toggle();
                    int check = step1.getCheck_boolean();
                    // CheckBox ?????? ??????.
                    if(holder.ct_sub_step.isChecked()) {
                        // ?????? ?????? ????????? ????????? ??????.
                        holder.ct_sub_step.setPaintFlags(holder.ct_sub_step.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        // ?????? ?????? ?????????????????? check = 1
                        main_presenter.updateCheck(step1.getStep_id(),check);
                    } else {
                        // ?????? ???????????? ????????? ?????????. check ?????? ?????????????????? -1
                        holder.ct_sub_step.setPaintFlags(0);
                        main_presenter.updateCheck(step1.getStep_id(),check-1);
                    }
                }
            });
            // check_boolean ?????? ??????????????????.
        } else {
            // CheckedTextView ?????????,
            holder.ct_sub_step.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ???????????? ?????? ??????.
                    holder.ct_sub_step.toggle(); //???????????? toggle();
                    int check = step1.getCheck_boolean();
                    // CheckBox ?????? ??????.
                    if(holder.ct_sub_step.isChecked()) {
                        // ?????? ?????? ????????? ????????? ??????.
                        holder.ct_sub_step.setPaintFlags(holder.ct_sub_step.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        // check ??? ????????? ???????????? ?????? ???????????? toggle + ?????? +1.
                        main_presenter = new Main_Presenter(view);
                        main_presenter.updateCheck(step1.getStep_id(),check+1);
                    } else {
                        // ?????? ???????????? ????????? ?????????. check ??? ???????????? 0.
                        holder.ct_sub_step.setPaintFlags(0);
                        main_presenter.updateCheck(step1.getStep_id(),check);
                    }
                }
            });
        }
        // FileName ????????????.
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyFile>> callFiles = getDataService.getFiles();

        callFiles.enqueue(new Callback<List<DutyFile>>() {
            @Override
            public void onResponse(Call<List<DutyFile>> call, Response<List<DutyFile>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutyFileList = response.body(); // dutyFile ?????????.

                    holder.ib_file.setTag(step1.getFile_boolean()); // file_boolean ?????? ??????.

                    if(step1.getFile_boolean() == 0) { // ????????? ????????? 0 ??????,
                        holder.ib_file.setVisibility(View.GONE); // ?????????.
                    } else { // 0??? ?????????.
                        holder.ib_file.setVisibility(View.VISIBLE); // ??????.
                        holder.ib_file.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DutyFile dutyFile = dutyFileList.get(position); // DutyFile ?????????.

                                switch(dutyFile.getFile_id()){
                                  case  1:
                                    case 2:
                                        openPdf(dutyFile.getFile_path());
                                    break;
                                    default:
                                    break;
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DutyFile>> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
            }
        });

        // Tip ????????????.(Get ?????????).
        GetDataService getDataService1 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyTip>> callTips = getDataService1.getTips();

        callTips.enqueue(new Callback<List<DutyTip>>() {
            @Override
            public void onResponse(Call<List<DutyTip>> call, Response<List<DutyTip>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutyTipList = response.body(); // TipList ??????.
                    holder.ib_tip.setTag(step1.getTip_boolean()); // tip_boolean ?????? ??????.
                    if(step1.getTip_boolean() == 0) {// Tip??? ????????? 0?????? ??????.
                        holder.ib_tip.setVisibility(View.GONE); // ?????????.
                    } else { // 0??? ?????????.
                        holder.ib_tip.setVisibility(View.VISIBLE); // ??????.
                        holder.ib_tip.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tip_contentAlertDialog(holder,position); // AlertDialog ?????????.(Tip ??????).
                            }
                        });
                    }
                }
            }
            @Override
            public void onFailure(Call<List<DutyTip>> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dutyStep1List.size();
    }

    // Pdf ?????? ??????.
    public void openPdf(String filename) {
        /** PDF reader code **/
        Uri uri = Uri.parse(filename);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            step1Context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // AlertDialog ??????. -> Tip ??????.
    public void tip_contentAlertDialog(@NonNull Step1ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DutyTip dutyTip = dutyTipList.get(position);
        holder.ib_tip.setTag(dutyTip.getTip_id());

        // AlertDialog ??????.
        AlertDialog.Builder builder = new AlertDialog.Builder(step1Context);
        builder.setTitle(dutyTip.getStep()).setMessage(dutyTip.getTip_content()); // title = step??????. Message = tip_content
        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
