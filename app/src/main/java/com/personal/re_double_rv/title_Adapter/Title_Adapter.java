package com.personal.re_double_rv.title_Adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.re_double_rv.Activity.Popup.FullPopupActivity;
import com.personal.re_double_rv.Activity.editor.Editor_Presenter;
import com.personal.re_double_rv.Activity.editor.Editor_View;
import com.personal.re_double_rv.Activity.editor.Popup_Activity;
import com.personal.re_double_rv.Activity.main.MainActivity;
import com.personal.re_double_rv.Activity.main.Main_Presenter;
import com.personal.re_double_rv.Activity.main.Main_View;
import com.personal.re_double_rv.R;
import com.personal.re_double_rv.Retrofit.DutyStep.ApiRetroDataStep;
import com.personal.re_double_rv.Retrofit.DutyStep.RetrofitClientStep;
import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutyStep2;
import com.personal.re_double_rv.models.DutyStep3;
import com.personal.re_double_rv.models.DutySteps;
import com.personal.re_double_rv.models.DutySteps1;
import com.personal.re_double_rv.models.DutyTitle;
import com.personal.re_double_rv.steps_Adapter.Step1_Adapter;
import com.personal.re_double_rv.steps_Adapter.Step2_Adapter;
import com.personal.re_double_rv.steps_Adapter.Step3_Adapter;
import com.personal.re_double_rv.steps_Adapter.Steps1_Adapter;
import com.personal.re_double_rv.steps_Adapter.Steps_Adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Title_Adapter extends RecyclerView.Adapter<Title_Adapter.TitleViewHolder> implements Editor_View, Main_View{

    // ????????? ??????. ~04.02)
    List<DutyTitle> dutyTitleList; // ?????? ???????????? ????????????????????? ????????? ?????????.
    List<DutyStep1> dutyStep1List; // step1 ???????????? ????????? ?????????.
    List<DutyStep2> dutyStep2List; // step2 ???????????? ????????? ?????????.
    List<DutyStep3> dutyStep3List; // step3 ???????????? ????????? ?????????.

    // ????????? ???????????? ??????. 04.02.
    List<DutySteps> dutyStepsList; // steps ???????????? ????????? ?????????.
    List<DutySteps1> dutySteps1List; // Steps1 ?????????.

    private Context title_Adapter_Context; // Context ??????.
    LinearLayoutManager linearLayoutManager; // LinearLayoutManager ??????.

    // ????????? ??????.
    Step3_Adapter step3_adapter;

    // ????????? ?????? ?????????.
    ItemClickListener itemClickListener;

    // Editor_Presenter
    Editor_Presenter editor_presenter;
    Editor_View view;
    private static String TAG = "??????" ;


    // ????????? ???????????? ????????????. -> ????????????.
    public Title_Adapter(List<DutyTitle> dutyTitleList, ItemClickListener itemClickListener) {
        this.dutyTitleList = dutyTitleList;
        this.itemClickListener = itemClickListener;
    }


    // Main_View implements
    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(title_Adapter_Context,
                message,
                Toast.LENGTH_SHORT).show();
    }
    // Main_View implements
    @Override
    public void onRequestError(String message) {

    }

    @Override
    public void onGetResult(List<DutyTitle> dutyTitleList) {
        // ????????? ?????? ?????????(????????? ??????)
        itemClickListener = ((view, position) -> {

            // ????????? ?????? ??? -> Intent??? ??????????????? ?????????.
//            int title_name_id = dutyTitleList.get(position).getTitle_name_id();
            int title_order = dutyTitleList.get(position).getTitle_order(); // title_order (?????? ??????).
            String title_name = dutyTitleList.get(position).getTitle_name(); // title_name (?????? ??????).
            // Intent??? ?????????.
            Intent editTitle = new Intent(title_Adapter_Context, FullPopupActivity.class);
            editTitle.putExtra("title_order",title_order); // title_name_id -> title_order??? ?????????.
//            editTitle.putExtra("duty_Title_id",title_name_id);
            editTitle.putExtra("title_name",title_name); // ?????? duty_title ??? "Key" + "Value"
            title_Adapter_Context.startActivity(editTitle);
//            startActivityForResult(editTitle, INTENT_EDIT); // ?????? ??? 200;

        });
    }

    @Override
    public void onGetSteps(List<DutyStep> dutyStepList) {
//        itemClickListener = ((view1, position) -> {
//           int step_id = dutyStepList.get(position).getStep_id();
//           String step = dutyStepList.get(position).getStep();
//           // Intent??? ?????????.
//            Intent editStep = new Intent(title_Adapter_Context, FullPopupActivity.class);
//            editStep.putExtra("step_id",step_id);
//            editStep.putExtra("step",step);
//            title_Adapter_Context.startActivity(editStep);
//        });
    }

    // Title ViewHolder;
    public class TitleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_duty_title_order, tv_duty_title; // ?????? ????????????. ?????? ??????.
        ImageButton ib_edit_Title, ib_delete_Title, ib_downBtn, ib_upBtn; // ??????, ??????(??????). ????????? / ??????.
        RecyclerView rv_step_item; // RecyclerView.
        CardView cv_dutyTitle; // ?????? ?????????.
        ItemClickListener itemClickListener; // ???????????????.
        Editor_Presenter editor_presenter;

        // ????????? ????????? RecyclerView ????????? ??????/

        public TitleViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_duty_title_order = itemView.findViewById(R.id.tv_duty_title_order); // duty_Title_order
            tv_duty_title = itemView.findViewById(R.id.tv_duty_title); // duty_Title
            ib_edit_Title = itemView.findViewById(R.id.ib_edit_Title); // ????????????.
            ib_delete_Title = itemView.findViewById(R.id.ib_delete_Title); // ?????????.
            cv_dutyTitle = itemView.findViewById(R.id.cv_dutyTitle); // ?????????
            rv_step_item = itemView.findViewById(R.id.rv_step_item); // ?????? ??????????????????.

            // ????????? ?????? ?????????.
            this.itemClickListener = itemClickListener;
            cv_dutyTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAbsoluteAdapterPosition()); // ????????? ?????? ,View & Position ???.
        }
    }

    @NonNull
    @Override
    public Title_Adapter.TitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        title_Adapter_Context = parent.getContext();

        View view = LayoutInflater.from(title_Adapter_Context).inflate(R.layout.layout_item,parent, false);
        return new TitleViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Title_Adapter.TitleViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DutyTitle dutyTitle = dutyTitleList.get(position);

        holder.tv_duty_title.setText(dutyTitle.getTitle_name()); // title??? ???????????? ??????.
        // Title_name_id ??? title_order ??? ?????????.
        holder.tv_duty_title_order.setText(String.valueOf(dutyTitle.getTitle_order())); // Title_order ??? ?????????.
//        holder.tv_duty_title_id.setText(String.valueOf(dutyTitle.getTitle_name_id())); // ????????? int?????? String ?????? ??????.

        holder.ib_edit_Title.setTag(dutyTitle.getTitle_order()); // ?????? title ???????????? ????????? ?????? ?????????.
        holder.ib_edit_Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goFull = new Intent(title_Adapter_Context, FullPopupActivity.class);
//                goFull.putExtra("duty_Title_id",dutyTitleList.get(position).getTitle_id());
//                goFull.putExtra("duty_Title",dutyTitleList.get(position).getTitle_name());

                // ??? FullPopupActivity??? ???????????? ???, ?????? title_order, title_name ??? ?????????.
                goFull.putExtra("title_order",dutyTitleList.get(position).getTitle_order());
                goFull.putExtra("title_name",dutyTitleList.get(position).getTitle_name());
                title_Adapter_Context.startActivity(goFull);
            }
        });

//        holder.ib_delete_Title.setTag(dutyTitle.getTitle_name_id()); // ?????? ?????? ??????.
        // holder??? ?????? ?????? ??????.
        holder.ib_delete_Title.setTag(dutyTitle.getTitle_order());
        holder.ib_delete_Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(title_Adapter_Context);
                builder.setTitle("?????? ??????").setMessage("?????? ?????????????????????????");
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Duty _ Title ???????????? ??????
                        editor_presenter = new Editor_Presenter(view);
                        editor_presenter.deleteTitle(dutyTitleList.get(position).getTitle_order());
                        remove(position);
//                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });

        // DutySteps??? ?????? ??????. 04.05. (????????? ???????????? ?????????????????? ?????????).
        // 1. ???????????? ??????
        ApiRetroDataStep getSteps = RetrofitClientStep.getRetrofitClient().create(ApiRetroDataStep.class);
        Call<List<DutySteps>> callSteps = getSteps.readAllSteps(); // ?????? ?????? Steps ??? ????????????.

        callSteps.enqueue(new Callback<List<DutySteps>>() {
            @Override
            public void onResponse(Call<List<DutySteps>> call, Response<List<DutySteps>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutyStepsList = response.body();

                    linearLayoutManager = new LinearLayoutManager(
                            holder.rv_step_item.getContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                    );

                    // ?????? ?????? title_order ??? ???????????? ?????? ????????? ?????????.
                    Steps_Adapter steps_adapter = new Steps_Adapter(getSteps(),title_Adapter_Context);
                    holder.rv_step_item.setLayoutManager(linearLayoutManager);
                    holder.rv_step_item.setAdapter(steps_adapter);
//
//                    if(dutyTitle.getTitle_order() == 1) {
//                    } else {
//                        holder.rv_step_item.setLayoutManager(linearLayoutManager);
//                        holder.rv_step_item.setAdapter(step3_adapter);
//                    }
                }
            }

            @Override
            public void onFailure(Call<List<DutySteps>> call, Throwable t) {

            }
        });

        // DutySteps1??? ?????? ??????. 04.04.(????????? ??????)
        ApiRetroDataStep getSteps1 = RetrofitClientStep.getRetrofitClient().create(ApiRetroDataStep.class);
        Call<List<DutySteps1>> callSteps1 = getSteps1.readAllSteps1();

        callSteps1.enqueue(new Callback<List<DutySteps1>>() {
            @Override
            public void onResponse(Call<List<DutySteps1>> call, Response<List<DutySteps1>> response) {
                if(response.isSuccessful() && response.body() != null){
                    dutySteps1List = response.body();

                    linearLayoutManager = new LinearLayoutManager(
                            holder.rv_step_item.getContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                    );
                    // ????????? ?????? ????????? title_order = "1" ??? ???,
                    if(dutyTitle.getTitle_order() == 1) {
                        Steps1_Adapter steps1_adapter = new Steps1_Adapter(getSteps1());
                        holder.rv_step_item.setLayoutManager(linearLayoutManager);
                        holder.rv_step_item.setAdapter(steps1_adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DutySteps1>> call, Throwable t) {

            }
        });


        // DutyStep1??? ?????? ??????.
//        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<DutyStep1>> callStep1 = getDataService.getAllStep1();
//
//        callStep1.enqueue(new Callback<List<DutyStep1>>() {
//            @Override
//            public void onResponse(Call<List<DutyStep1>> call, Response<List<DutyStep1>> response) {
//                if(response.isSuccessful() && response.body() != null) {
//                    dutyStep1List = response.body();
//                    // ?????? ???????????? ??????.
//                    linearLayoutManager = new LinearLayoutManager(
//                            holder.rv_step_item.getContext(),
//                            LinearLayoutManager.VERTICAL,
//                            false
//                    );
//                    // ????????? ?????? ?????????.
//                    if(dutyTitle.getTitle_id() == 1) {
//                        Step1_Adapter step1_adapter = new Step1_Adapter(getStep1());
//                        holder.rv_step_item.setLayoutManager(linearLayoutManager);
//                        holder.rv_step_item.setAdapter(step1_adapter);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DutyStep1>> call, Throwable t) {
//
//            }
//        });

        // step2??? ?????? ??????.
        GetDataService getDataService2 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyStep2>> callStep2 = getDataService2.getAllStep2();

        callStep2.enqueue(new Callback<List<DutyStep2>>() {
            @Override
            public void onResponse(Call<List<DutyStep2>> call, Response<List<DutyStep2>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutyStep2List = response.body();

                    // ?????? ???????????? ??????.
                    linearLayoutManager = new LinearLayoutManager(
                            holder.rv_step_item.getContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                    );
                    // ????????? ?????? ?????????.
                    if(dutyTitle.getTitle_id() == 2) {
                        Step2_Adapter step2_adapter = new Step2_Adapter(getStep2());
                        holder.rv_step_item.setLayoutManager(linearLayoutManager);
                        holder.rv_step_item.setAdapter(step2_adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DutyStep2>> call, Throwable t) {

            }
        });

        // step3??? ?????? ??????.
        GetDataService getDataService3 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyStep3>> callStep3 = getDataService3.getAllStep3();

        callStep3.enqueue(new Callback<List<DutyStep3>>() {
            @Override
            public void onResponse(Call<List<DutyStep3>> call, Response<List<DutyStep3>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutyStep3List = response.body();

                    // ?????? ???????????? ??????.
                    linearLayoutManager = new LinearLayoutManager(
                            holder.rv_step_item.getContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                    );
                    // ????????? ?????? ?????????.
                    if(dutyTitle.getTitle_id() == 3) {
                        Step3_Adapter step3_adapter = new Step3_Adapter(getStep3());
                        holder.rv_step_item.setLayoutManager(linearLayoutManager);
                        holder.rv_step_item.setAdapter(step3_adapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<DutyStep3>> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dutyTitleList.size();
    }


    // ItemClickListener ????????? ?????? ?????????.
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }

    // ????????????. (DB ????????? ??????, ?????? RecyclerView????????? ?????? ??????.)
    public void remove(int position) {
        // ??????????????? ???????????? ???, ???????????? ??????.
        try{
            dutyTitleList.remove(position);
//            notifyItemRemoved(position);
            notifyDataSetChanged();
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
         }
    }


    // ????????? ?????? ???????????? ?????????????????? ?????? ????????? ?????? ????????? ?????????.

    // ?????? Steps ??? ????????????.
    private List<DutySteps> getSteps() {
        List<DutySteps> stepsList = new ArrayList<>();
        for(int i = 0; i < dutyStepsList.size(); i++) {
            stepsList.add(i,dutyStepsList.get(i));
        }
        return stepsList;
    }

    // ???????????? ??? Steps1.
    private List<DutySteps1> getSteps1() {
        List<DutySteps1> steps1List = new ArrayList<>();
        for(int i=0; i < dutySteps1List.size(); i++) {
            steps1List.add(i,dutySteps1List.get(i));
        }
        return steps1List;
    }

    // Step1.
    private List<DutyStep1> getStep1() {
        List<DutyStep1> step1List = new ArrayList<>();
        for (int i = 0; i < dutyStep1List.size(); i++){
            step1List.add(i,dutyStep1List.get(i));
        }
        return step1List;
    }

    // Step2.
    private List<DutyStep2> getStep2() {
        List<DutyStep2> step2List = new ArrayList<>();
        for (int i = 0; i < dutyStep2List.size(); i++){
             step2List.add(i,dutyStep2List.get(i));
        }
        return step2List;
    }

    // Step3.
    private List<DutyStep3> getStep3() {
        List<DutyStep3> step3List = new ArrayList<>();
        for (int i = 0; i < dutyStep3List.size(); i++){
            step3List.add(i,dutyStep3List.get(i));
        }
        return step3List;
    }


}
