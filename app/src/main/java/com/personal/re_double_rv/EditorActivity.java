package com.personal.re_double_rv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.personal.re_double_rv.Retrofit.GetDataService;
import com.personal.re_double_rv.Retrofit.RetrofitClientInstance;
import com.personal.re_double_rv.models.DutyTitle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity {

    EditText et_title, et_step;
    ImageButton ib_add;
    List<DutyTitle> dutyTitleList;

    Title_Adapter title_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_title = findViewById(R.id.title);
//        et_step = findViewById(R.id.step);
//        ib_add = findViewById(R.id.step_add);
    }

    // 옵션 메뉴 달기.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case  R.id.save:
                // Save.

                String title = et_title.getText().toString().trim();

                saveNote(title);

                return true;

          default:
              return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote(String title) {

                    GetDataService postTitle = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                        Call<DutyTitle> callPostTitle = postTitle.saveTitle(title);

                        callPostTitle.enqueue(new Callback<DutyTitle>() {
                            @Override
                            public void onResponse(@NonNull Call<DutyTitle> call, @NonNull Response<DutyTitle> response) {
                                if(response.isSuccessful() && response.body() != null) {
                                    Boolean success = response.body().getSuccess();

                                    if(success) {
                                        Toast.makeText(EditorActivity.this,
                                                response.body().getMessage(),
                                                Toast.LENGTH_SHORT).show();
//                                        title_adapter.notifyDataSetChanged();
                                        finish(); // back to main
                                    } else {
                                        Toast.makeText(EditorActivity.this,
                                                response.body().getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<DutyTitle> call, Throwable t) {

                            }
                        });

    }
}