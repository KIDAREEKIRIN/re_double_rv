package com.personal.re_double_rv.Activity.Popup_Sub;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.personal.re_double_rv.Activity.Popup.FullPopupActivity;
import com.personal.re_double_rv.Popup_Adapter.FullPopup1_Adapter;
import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutyStep;

import java.util.List;

public class Popup_Sub extends Activity implements Popup_View {

    EditText et_txtText; // 추가할 내용.
    ImageButton ib_cancel_PopupBtn, ib_resetBtn, ib_edit_PopupBtn; // 창 닫기, 내용 다시 쓰기.
    Button btn_Popup_insert,btn_Popup_Update ; // 확인 버튼.
//    기존 내용.
//    String step;
//    int step_id; // step_id 값.
//    int title_id_value; // title_order 값.

    String step_name; // 업무 단계 name
    int step_id; // 업무 단계 index
    int title_order; // 업무 title 순서.

    Popup_Sub_Presenter popup_sub_presenter; // MVP 패턴 중 P(Presenter).

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 타이틀 바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_sub);

        et_txtText = findViewById(R.id.et_txtText);

        // Popup_sub_presenter 선언.
        popup_sub_presenter = new Popup_Sub_Presenter(this);

        // FullPopupActivity 에서 넘겨받은 Intent 값.
        Intent intent = getIntent(); // Intent 받기.
        step_name = intent.getStringExtra("step_name"); // 업무 step 이름.
//        step_id = intent.getIntExtra("step_id") -> 얘는 원래 없는 값인데 어디서 나온거지???
        title_order = intent.getIntExtra("title_order",0); // 업무 title 순서.
//        title_id_value = intent.getIntExtra("title_id_value",0); // 업무 title 순서.
//        step_id = intent.getIntExtra("step_id",0); // 업무 step index 값.
//        step = intent.getStringExtra("step"); // 업무 step 이름.


        // 팝업창 닫기.
        ib_cancel_PopupBtn = findViewById(R.id.ib_cancel_PopupBtn); // 취소하기(우측 상단 x버튼).
        ib_cancel_PopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 팝업창 내용 다시쓰기.
        ib_resetBtn = findViewById(R.id.ib_resetBtn);
        ib_resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_txtText.setText(null);
            }
        });

        // 수정버튼 이미지버튼 클릭시.
        ib_edit_PopupBtn = findViewById(R.id.ib_edit_PopupBtn);
        ib_edit_PopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Popup_insert.setVisibility(View.GONE); // 확인(추가) 버튼은 사라지고
                btn_Popup_Update.setVisibility(View.VISIBLE); // 수정하기 버튼이 보인다.
            }
        });

        // 확인버튼 클릭시,(추가하기 버튼) insert.
        btn_Popup_insert = findViewById(R.id.btn_Popup_insert);
        btn_Popup_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dutyStep = et_txtText.getText().toString(); // EditText의 값을 저장한다.
                // 업무 step 추가하기.
                popup_sub_presenter.insertStep(dutyStep,title_order);
//                popup_sub_presenter.saveStep(step, title_id_value);
                finish();
                Log.d("클릭", "onClick: 추가 버튼 클릭 시, step과 title_id_value 값." + dutyStep +"\n"+step_name+"\n"+step_id+"\n"+title_order);

            }
        });

//        et_txtText.setText(step); // 업무 step_name 붙이기. 수정하기(update) 단계에서만 붙이기.
        et_txtText.setText(step_name);
        // 수정하기 버튼 클릭시.
        btn_Popup_Update = findViewById(R.id.btn_Popup_Update);
        btn_Popup_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_name = et_txtText.getText().toString(); // title_name 에는 et_txtText 에 작성한 값을 붙인다.
                // 업무 단계 수정하기(update).
                popup_sub_presenter.updateStep(step_id, title_name);
                finish();
            }
        });
    }

    // 성공시 보이는 메세지.
    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(Popup_Sub.this, message, Toast.LENGTH_SHORT).show();
    }

    // 실패시 보이는 메시지.
    @Override
    public void onRequestError(String message) {
        Toast.makeText(Popup_Sub.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<DutyStep> dutyStepList) {

    }
}