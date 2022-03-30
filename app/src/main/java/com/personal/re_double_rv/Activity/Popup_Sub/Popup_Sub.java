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
    Button btn_Popup_Ok,btn_Popup_Update ; // 확인 버튼.

    String step;
    int step_id;
    int title_id_value;

    Popup_Sub_Presenter popup_sub_presenter;
    Popup_View view;

    FullPopup1_Adapter fullPopup1_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 타이틀 바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_sub);

        et_txtText = findViewById(R.id.et_txtText);

        // Popup_sub_presenter 선언.
        popup_sub_presenter = new Popup_Sub_Presenter(this);

        // MainActivity로부터 넘겨받은 Intent 값.
        Intent intent = getIntent();
        title_id_value = intent.getIntExtra("title_id_value",0);
        step_id = intent.getIntExtra("step_id",0);
        step = intent.getStringExtra("step");


        // 팝업창 닫기.
        ib_cancel_PopupBtn = findViewById(R.id.ib_cancel_PopupBtn);
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
                btn_Popup_Ok.setVisibility(View.GONE);
                btn_Popup_Update.setVisibility(View.VISIBLE);
            }
        });

        // 확인버튼 클릭시,
        btn_Popup_Ok = findViewById(R.id.btn_Popup_Ok);
        btn_Popup_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String step = et_txtText.getText().toString(); // EditText의 값을 저장한다.
//                popup_sub_presenter = new Popup_Sub_Presenter(view);
                popup_sub_presenter.saveStep(step, title_id_value);
                finish();
                Log.d("클릭", "onClick: 추가 버튼 클릭 시, step과 title_id_value 값." + step+ title_id_value);

            }
        });

        et_txtText.setText(step);
        btn_Popup_Update = findViewById(R.id.btn_Popup_Update);
        btn_Popup_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title_name = et_txtText.getText().toString();
                popup_sub_presenter.updateStep(step_id, title_name);
                finish();
            }
        });
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(Popup_Sub.this, "단계 성공", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(Popup_Sub.this, "단계 실패", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<DutyStep> dutyStepList) {

    }
}