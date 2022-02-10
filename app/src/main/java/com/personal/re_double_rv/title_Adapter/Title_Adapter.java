package com.personal.re_double_rv.title_Adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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

import com.personal.re_double_rv.Activity.editor.Editor_Presenter;
import com.personal.re_double_rv.Activity.editor.Editor_View;
import com.personal.re_double_rv.Activity.main.MainActivity;
import com.personal.re_double_rv.Activity.main.Main_Presenter;
import com.personal.re_double_rv.Activity.main.Main_View;
import com.personal.re_double_rv.R;
import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
import com.personal.re_double_rv.models.DutyStep1;
import com.personal.re_double_rv.models.DutyStep2;
import com.personal.re_double_rv.models.DutyStep3;
import com.personal.re_double_rv.models.DutyTitle;
import com.personal.re_double_rv.steps_Adapter.Step1_Adapter;
import com.personal.re_double_rv.steps_Adapter.Step2_Adapter;
import com.personal.re_double_rv.steps_Adapter.Step3_Adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Title_Adapter extends RecyclerView.Adapter<Title_Adapter.TitleViewHolder> implements Editor_View{

    List<DutyTitle> dutyTitleList; // 메인 액티비티 리사이클러뷰에 들어갈 리스트.
    List<DutyStep1> dutyStep1List; // step1 리스트에 들어갈 리스트.
    List<DutyStep2> dutyStep2List; // step2 리스트에 들어갈 리스트.
    List<DutyStep3> dutyStep3List; // step3 리스트에 들어갈 리스트.
    private Context title_Adapter_Context; // Context 선언.
    LinearLayoutManager linearLayoutManager; // LinearLayoutManager 선언.

    // 아이템 클릭 리스너.
    ItemClickListener itemClickListener;

    // Editor_Presenter
    Editor_Presenter editor_presenter;
    Editor_View view;

    // RecyclerView 접기 / 펴기.
    // Item의 클릭 상태를 저장할 array 객체.
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭됐던 Item의 position.
    private int prePosition = -1;

    private static String TAG = "시작" ;


    // 생성자 선언해서 건네주기. -> 메인으로.
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

    // Title ViewHolder;
    public class TitleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_duty_title_id, tv_duty_title; // 업무 순서번호. 업무 제목.
        ImageButton ib_edit_Title, ib_delete_Title, ib_downBtn, ib_upBtn; // 수정, 삭제(사용). 펼치기 / 접기.
        RecyclerView rv_step_item; // RecyclerView.
        CardView cv_dutyTitle; // 전체 카드뷰.
        ItemClickListener itemClickListener; // 클릭리스너.
        Editor_Presenter editor_presenter;

        // 카드뷰 클릭시 RecyclerView 펼치기 접기/
        LinearLayout linearLayout;

        public TitleViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_duty_title_id = itemView.findViewById(R.id.tv_duty_title_id); // duty_Title_id
            tv_duty_title = itemView.findViewById(R.id.tv_duty_title); // duty_Title
            ib_edit_Title = itemView.findViewById(R.id.ib_edit_Title); // 수정하기.
            ib_delete_Title = itemView.findViewById(R.id.ib_delete_Title); // 지우기.

            ib_downBtn = itemView.findViewById(R.id.ib_downBtn); // 펼치기.
            ib_upBtn = itemView.findViewById(R.id.ib_upBtn); // 접기.

            cv_dutyTitle = itemView.findViewById(R.id.cv_dutyTitle); // 카드뷰
            rv_step_item = itemView.findViewById(R.id.rv_step_item); // 하위 리사이클러뷰.

            linearLayout = itemView.findViewById(R.id.linearLayout);


            // 아이템 클릭 리스너.
            this.itemClickListener = itemClickListener;
            cv_dutyTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAbsoluteAdapterPosition()); // 클릭할 경우 ,View & Position 값.
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
//        if(String.valueOf(dutyTitle.getTitle_name_id()) != null) 조건문은 나중에 다시 달기.
        holder.tv_duty_title_id.setText(String.valueOf(dutyTitle.getTitle_name_id())); // 원래는 int인데 String 으로 붙임.
        holder.ib_delete_Title.setTag(dutyTitle.getTitle_name_id()); // 삭제 태그 달기.
        holder.ib_delete_Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(title_Adapter_Context);
                builder.setTitle("제목 삭제").setMessage("정말 삭제하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Duty _ Title 삭제하기 기능
                        editor_presenter = new Editor_Presenter(view);
                        editor_presenter.deleteTitle(dutyTitleList.get(position).getTitle_name_id());
                        remove(position);
//                        notifyDataSetChanged();
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

        holder.tv_duty_title.setText(dutyTitle.getTitle_name()); // title을 가져와서 붙임.

        holder.ib_downBtn.setTag(dutyTitle.getTitle_name_id());
        holder.ib_upBtn.setTag(dutyTitle.getTitle_name_id());


        holder.ib_downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 펼쳐진 아이템을 클릭시.
                if(selectedItems.get(position)) {
                    // position 값을 지움.
                    selectedItems.delete(position);
                } else {
                    // 직전의 클릭됐던 아이템의 클릭 상태를 지움.
                    selectedItems.delete(prePosition);
                    // 클릭한 아이템의 position을 저장.
                    selectedItems.put(position,true);
                }
                // 해당 포지션의 값에 따라 보이기 안보이기.
                if(!selectedItems.get(position)) {
                    // LinearLayout 보이기.
                    holder.linearLayout.setVisibility(View.VISIBLE);
                    holder.ib_downBtn.setVisibility(View.GONE);
                    holder.ib_upBtn.setVisibility(View.VISIBLE);
                    holder.ib_upBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // LinearLayout 안 보이기.
                            holder.linearLayout.setVisibility(View.GONE);
                            holder.ib_downBtn.setVisibility(View.VISIBLE);
                            holder.ib_upBtn.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
        // 카드뷰 클릭시 -> 펼치기 기능 달기.
        // 뷰홀더에 아이템 클릭 리스너 달기.
        holder.cv_dutyTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(title_Adapter_Context, "현재 포지션22 " + prePosition, Toast.LENGTH_SHORT).show();
            }
        });

        holder.linearLayout.setTag(dutyTitle.getTitle_id());

//        holder.cv_dutyTitle.setTag(dutyTitle.ge); // CardView 에는 무엇을 붙일까???

        // DutyStep1에 대한 내용.
        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyStep1>> callStep1 = getDataService.getAllStep1();

        callStep1.enqueue(new Callback<List<DutyStep1>>() {
            @Override
            public void onResponse(Call<List<DutyStep1>> call, Response<List<DutyStep1>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutyStep1List = response.body();
                    // 자식 레이아웃 설정.
                    linearLayoutManager = new LinearLayoutManager(
                            holder.rv_step_item.getContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                    );
                    // 시기별 업무 올리기.
                    if(dutyTitle.getTitle_id() == 1) {
                        Step1_Adapter step1_adapter = new Step1_Adapter(getStep1());
                        holder.rv_step_item.setLayoutManager(linearLayoutManager);
                        holder.rv_step_item.setAdapter(step1_adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DutyStep1>> call, Throwable t) {

            }
        });

        // step2에 대한 내용.
        GetDataService getDataService2 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyStep2>> callStep2 = getDataService2.getAllStep2();

        callStep2.enqueue(new Callback<List<DutyStep2>>() {
            @Override
            public void onResponse(Call<List<DutyStep2>> call, Response<List<DutyStep2>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutyStep2List = response.body();

                    // 자식 레이아웃 설정.
                    linearLayoutManager = new LinearLayoutManager(
                            holder.rv_step_item.getContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                    );
                    // 시기별 업무 올리기.
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

        // step3에 대한 내용.
        GetDataService getDataService3 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<DutyStep3>> callStep3 = getDataService3.getAllStep3();

        callStep3.enqueue(new Callback<List<DutyStep3>>() {
            @Override
            public void onResponse(Call<List<DutyStep3>> call, Response<List<DutyStep3>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dutyStep3List = response.body();

                    // 자식 레이아웃 설정.
                    linearLayoutManager = new LinearLayoutManager(
                            holder.rv_step_item.getContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                    );
                    // 시기별 업무 올리기.
                    if(dutyTitle.getTitle_id() == 3) {
                        Step3_Adapter step3_adapter = new Step3_Adapter(getStep3());
//                        Step3_Adapter step3_adapter = new Step3_Adapter(getStep3());
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


    // ItemClickListener 아이템 클릭 리스너.
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // 삭제하기.
    public void remove(int position) {
        // 예외상황이 벌어졌을 때, 강제상황 실행.
        try{
            dutyTitleList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
         }
    }


    // 첫번째 메인 액티비티 리사이클러뷰 안의 카드뷰 안에 들어갈 리스트.
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
