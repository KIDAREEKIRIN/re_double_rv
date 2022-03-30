package com.personal.re_double_rv.Activity.editor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.personal.re_double_rv.Activity.main.Main_View;
import com.personal.re_double_rv.R;
import com.personal.re_double_rv.models.DutyStep;
import com.personal.re_double_rv.models.DutyTitle;

import java.util.List;

public class Popup_Activity extends Activity implements Editor_View, Main_View {

    EditText et_idText,editText_Title ; // 번호.EditText 선언. ,
    Button btn_Popup_Ok, btn_Popup_Update; // "확인" 버튼 선언. "수정" 버튼.
    ImageButton ib_resetBtn, ib_cancel_PopupBtn, ib_edit_PopupBtn, ib_update_PopupBtn; // EditText reset, 팝업창 닫기, 수정하기.
    Editor_Presenter editor_presenter; // Editor_Presenter 받아오기.

//    String edit_dutyTitle;

    int id; // 넘어온 Duty_Title_Id;
    String name; // 넘어온 Duty_Title;

    // Insert Title_order + Title_name
    int title_order; // title_order
    String title_name; // title_name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 타이틀 바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        et_idText = findViewById(R.id.et_idText);// 번호.
        editText_Title = findViewById(R.id.et_txtText); // 추가할 내용.

        // Editor_Presenter 객체 생성. MVP 모델의 Presenter 객
        editor_presenter = new Editor_Presenter(this); // Editor_Presenter 호출.

        // Activity에서 넘긴 Intent 값 받기. (기존값)
        // Popup 창에서 수정하기를 위해 불러옴.
        Intent intent = getIntent();
//        id = intent.getIntExtra("title_name_id",0);
//        name = intent.getStringExtra("title_name");

        setDataFormIntentExtra();

        // EditText 리셋 버튼.
        ib_resetBtn = findViewById(R.id.ib_resetBtn); // 추가한 제목 다시 쓰기.
        ib_resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // editText_Title 을 빈칸으로 만들기.
                editText_Title.setText(null);
            }
        });

        // 팝업창 닫기. 팝업창 우측 상단의 x 표시.
        ib_cancel_PopupBtn = findViewById(R.id.ib_cancel_PopupBtn);
        ib_cancel_PopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 팝업창 이미지버튼 update 버튼 선언.
        ib_update_PopupBtn = findViewById(R.id.ib_update_PopupBtn);
        btn_Popup_Update = findViewById(R.id.btn_Popup_Update); // "수정" 버튼 선언.

        // 팝업창 edit 버튼 클릭시.
        ib_edit_PopupBtn = findViewById(R.id.ib_edit_PopupBtn);
        ib_edit_PopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Popup_Ok.setVisibility(View.GONE); // 추가 버튼 사라짐.
                btn_Popup_Update.setVisibility(View.VISIBLE); // 수정버튼 등장.
                editMode();
                ib_update_PopupBtn.setVisibility(View.VISIBLE); // update 팝업창 버튼 이 보이고
                ib_edit_PopupBtn.setVisibility(View.GONE); // edit 버튼이 사라짐(gone).

                btn_Popup_Update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // int 값 올리기.
                        int title_name_id = Integer.parseInt(et_idText.getText().toString());
                        // 수정한 title_name 올리기.
                        String title_name_edit = editText_Title.getText().toString();
                        editor_presenter.updateTitle(title_name_id,title_name_edit); // 받아온 값으로 UPDATE를 해줘야 수정이 됨.
                        finish();
                    }
                });
            }
        });

        // 수정하기.
        ib_update_PopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // title_name_id 값이랑, 수정한 내용을 Update(수정).
                int title_name_id = Integer.parseInt(et_idText.getText().toString());
                String title_name_edit = editText_Title.getText().toString();
                editor_presenter.updateTitle(title_name_id,title_name_edit); // 받아온 값으로 UPDATE를 해줘야 수정이 됨.
                finish(); // 끝내기.
            }
        });

        // 확인 버튼 클릭시. -> 데이터 저장 + DB.
        // Insert DutyTitle의 과정.
        btn_Popup_Ok = findViewById(R.id.btn_Popup_Ok);
        btn_Popup_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int title_name_id = Integer.parseInt(et_idText.getText().toString());
//                String title_name_edit = editText_Title.getText().toString(); // EditText 의 값을 저장한다.
//                editor_presenter.saveTitle(title_name_edit,title_name_id); // duty_Title 저장.
                int title_order = Integer.parseInt(et_idText.getText().toString());
                String title_name = editText_Title.getText().toString();
                editor_presenter.insert_DutyTitle(title_order,title_name);
                Log.d("업무 추가하기", "onClick: " + title_order +"\n"+  title_name);
                finish();
            }
        });

    }

    // Intent 값을 처리하는 Method.
    private void setDataFormIntentExtra() {

        if ( id != 0 ) {
            // 추가할 내용의 EditText 에 Intent로 받아온 title_name 값을 넣는다.
            editText_Title.setText(name);
            // 읽기 모드.
            readMode();
        } else {
            // 수정 모드.
            editMode();
        }

    }

    // 수정 모드.
    private void editMode() {
        // true => 터치 모드에서 모든 키입력을 받음.
        editText_Title.setFocusableInTouchMode(true);
    }

    // 읽기 모드 -> 뷰의 포커스를 받지 않음.
    private void readMode() {
        // false => 터치 모드에서 모든 키입력을 받지 않음.
        editText_Title.setFocusableInTouchMode(false); // 터치 모드에서도 모든 키입력을 받음(터치 + 키보드).
        editText_Title.setFocusable(false); // 뷰의 포커스를 가질 수 있는지?
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 바깥 레이어 클릭시 안닫히게
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return  false;
        }

        return true;
    }

    // 안드로이드 백버튼 막기.
    @Override
    public void onBackPressed() {
        return;
//        main_presenter.getData();
    }


    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(Popup_Activity.this,
                message,
                Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(Popup_Activity.this,
                message,
                Toast.LENGTH_SHORT).show();
    }

    // Main_View의 반환값.
    @Override
    public void onGetResult(List<DutyTitle> dutyTitleList) {

    }

    @Override
    public void onGetSteps(List<DutyStep> dutyStepList) {

    }
}