package com.example.spj.youfan.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

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
    @Bind(R.id.et_login_phone)
    EditText etLoginPhone;
    @Bind(R.id.et_login_secret)
    EditText etLoginSecret;
    @Bind(R.id.ib_qq_login)
    ImageButton ibQqLogin;
    @Bind(R.id.ib_weixin_login)
    ImageButton ibWeixinLogin;
    @Bind(R.id.tv_register)
    TextView tvRegister;
    @Bind(R.id.tv_forget_secret)
    TextView tvForgetSecret;
    @Bind(R.id.btn_login)
    Button btnLogin;


    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        ibTopMenu.setVisibility(View.GONE);
        ibTopSet.setVisibility(View.GONE);
        ivTopSeacher.setVisibility(View.GONE);
        ivTopMail.setVisibility(View.GONE);
        ivTopBack.setVisibility(View.VISIBLE);
        tvTop.setText("帐号登录");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.iv_top_back, R.id.ib_qq_login, R.id.ib_weixin_login, R.id.tv_register, R.id.tv_forget_secret,R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back:
                //点击后退按钮
               closeCurrentActivity();
                break;
            case R.id.btn_login:
                //判断登录
                String phone = etLoginPhone.getText().toString().trim();
                String secret = etLoginSecret.getText().toString().trim();
                if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(secret)) {
                    Toast.makeText(LoginActivity.this, "手机号或者密码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "登录成功，尽情购物吧", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ib_qq_login:
                Toast.makeText(LoginActivity.this, "QQ登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_weixin_login:
                Toast.makeText(LoginActivity.this, "微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_register:
                goToActivity(RegisterActivity.class,null);
                break;
            case R.id.tv_forget_secret:
                goToActivity(FindSecretActivity.class,null);
                break;
        }
    }


}
