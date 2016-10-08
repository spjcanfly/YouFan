package com.example.spj.youfan.activity.user;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_set_secret)
    EditText etSetSecret;
    @Bind(R.id.et_login_secret)
    EditText etLoginSecret;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.ib_top_menu)
    ImageView ibTopMenu;
    @Bind(R.id.ib_top_set)
    ImageView ibTopSet;
    @Bind(R.id.iv_top_back)
    ImageView ivTopBack;
    @Bind(R.id.tv_top)
    TextView tvTop;
    @Bind(R.id.iv_top_seacher)
    ImageView ivTopSeacher;
    @Bind(R.id.iv_top_mail)
    ImageView ivTopMail;

    @Override
    protected void initData() {
        //先设置不可点击按钮
        btnLogin.setClickable(false);
        etPhone.addTextChangedListener(new MyTextChangedListener());
        etSetSecret.addTextChangedListener(new MyTextChangedListener());
        etLoginSecret.addTextChangedListener(new MyTextChangedListener());
    }

    @Override
    protected void initTitle() {
        ibTopMenu.setVisibility(View.GONE);
        ibTopSet.setVisibility(View.GONE);
        ivTopSeacher.setVisibility(View.GONE);
        ivTopMail.setVisibility(View.GONE);
        ivTopBack.setVisibility(View.VISIBLE);
        tvTop.setText("快速注册");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }


    @OnClick({R.id.iv_top_back,R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back :
                closeCurrentActivity();
                break;
            case R.id.btn_login :
                Toast.makeText(RegisterActivity.this, "注册成功，抓紧登录吧！", Toast.LENGTH_SHORT).show();
                break;
        }
    }



    private class MyTextChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            //只有全部都填了之后，才能点击注册按钮
            String et_phone = etPhone.getText().toString().trim();
            String et_Set = etSetSecret.toString().trim();
            String et_login = etLoginSecret.getText().toString().trim();
            if(!TextUtils.isEmpty(et_phone) && !TextUtils.isEmpty(et_Set) && !TextUtils.isEmpty(et_login)) {
                btnLogin.setClickable(true);
                btnLogin.setBackgroundColor(Color.YELLOW);
            }else {
                btnLogin.setClickable(false);
                btnLogin.setBackgroundColor(Color.GRAY);
            }
        }
    }
}
