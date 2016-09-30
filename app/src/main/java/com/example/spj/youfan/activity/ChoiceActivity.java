package com.example.spj.youfan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spj.youfan.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChoiceActivity extends Activity {

    @Bind(R.id.btn_choice_men)
    Button btnChoiceMen;
    @Bind(R.id.btn_choice_women)
    Button btnChoiceWomen;
    @Bind(R.id.btn_choice_life)
    Button btnChoiceLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_choice_men, R.id.btn_choice_women, R.id.btn_choice_life})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_choice_men:
                goToMainActivity();
                break;
            case R.id.btn_choice_women:
                goToMainActivity();
                break;
            case R.id.btn_choice_life:
                goToMainActivity();
                break;

        }
        //结束这个页面
        finish();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
