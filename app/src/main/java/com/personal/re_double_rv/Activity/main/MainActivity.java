package com.personal.re_double_rv.Activity.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.personal.re_double_rv.Activity.Popup.FullPopup_View;
import com.personal.re_double_rv.Activity.editor.Popup_Activity;
import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutyTitle;
import com.personal.re_double_rv.title_Adapter.ItemClickListener;
import com.personal.re_double_rv.title_Adapter.Title_Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements Main_View{

    private static final int INTENT_EDIT = 200; // 수정 시, 코드 200;
    private static final int INTENT_ADD = 100; // 추가 시, 코드 200;
    RecyclerView rv_title; // 리사이클러뷰(전체) 선언.
    LinearLayoutManager linearLayoutManager; // 리니어 레이아웃 선언
    Title_Adapter title_adapter; // 첫번째 어댑터
    ItemClickListener itemClickListener;

    List<DutyTitle> dutyTitleList; // 이게 데이터가 있을 지 모르겠음.. (역시나 없음).

    // 추가 Fab 버튼.
    FloatingActionButton fab;

    // MVP 패턴의 Presenter
    Main_Presenter main_presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_title = findViewById(R.id.rv_title); // 리사이클러뷰
        linearLayoutManager = new LinearLayoutManager(MainActivity.this); // LinearLayoutManager
        rv_title.setLayoutManager(linearLayoutManager); // 리사이클러뷰를 레이아웃에 붙이기.

        // FAB 버튼을 리사이클러뷰 스크롤 시 보이기 + 사라지기.
        rv_title.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy <= 0) {
                     fab.show(); // 보이기.
                } else {
                    fab.hide(); // 안 보이기.
                }
            }
        });

        // 추가 버튼을 누르면
        fab = findViewById(R.id.add_main); // 선언.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 추가 가능한 Activity 화면전환.
                // 팝업으로 띄우기.
                startActivityForResult(new Intent(MainActivity.this,
                        Popup_Activity.class),INTENT_ADD); // 추가시 100;
            }
        });

        main_presenter = new Main_Presenter(this); // Main_Presenter 생성.
//        title_adapter.notifyDataSetChanged();
        main_presenter.getData(); // DB에서 데이터 가져오기.


    }

    // startActivityForResult 와 짝궁.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 데이터 추가시.
        if (requestCode == INTENT_ADD && resultCode == RESULT_OK) {
            main_presenter.getData(); // reload data
        } else if (requestCode == INTENT_EDIT && resultCode == RESULT_OK)
            main_presenter.getData(); // reload data
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(MainActivity.this,
                message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(MainActivity.this,
                message,
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onGetResult(List<DutyTitle> dutyTitleList) {
        // 아이템 클릭 리스너(데이터 수정)
        itemClickListener = ((view, position) -> {

            // 아이템 클릭 시 -> Intent로 팝업창으로 보내기.
            int title_id = dutyTitleList.get(position).getTitle_id();
            int title_name_id = dutyTitleList.get(position).getTitle_name_id();
            String title_name = dutyTitleList.get(position).getTitle_name();
            // Intent로 넘기기.
            Intent editTitle = new Intent(MainActivity.this, Popup_Activity.class);
            editTitle.putExtra("title_id",title_id);
            editTitle.putExtra("title_name_id",title_name_id);
            editTitle.putExtra("title_name",title_name); // 넘길 duty_title 에 "Key" + "Value"
            startActivityForResult(editTitle, INTENT_EDIT); // 수정 시 200;

        });

        title_adapter = new Title_Adapter(dutyTitleList, itemClickListener);
        title_adapter.notifyDataSetChanged();
        rv_title.setAdapter(title_adapter);


    }

    @Override
    public void onGetSteps(List<DutyStep> dutyStepList) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        main_presenter.getData();
    }

}