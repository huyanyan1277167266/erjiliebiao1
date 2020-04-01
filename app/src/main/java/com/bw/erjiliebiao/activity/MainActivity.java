package com.bw.erjiliebiao.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bw.erjiliebiao.R;
import com.bw.erjiliebiao.base.BaseActivity;
import com.bw.erjiliebiao.base.BasePresenter;
import com.bw.erjiliebiao.bean.LoginBean;
import com.bw.erjiliebiao.contract.LoginContract;
import com.bw.erjiliebiao.presenter.LoginPagePresenter;
import com.bw.erjiliebiao.utile.SpUtile;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements LoginContract.LoginIView {

@BindView(R.id.phone)
    EditText phone;
@BindView(R.id.pwd)
EditText pwd;
@BindView(R.id.bt)
    Button bt;
    @Override
    protected BasePresenter initPresenter() {
        return new LoginPagePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {

    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
            //解析
        String sessionId = loginBean.getResult().getSessionId();
        int userId = loginBean.getResult().getUserId();
        //调用sp工具类
        SpUtile.putString(this,SpUtile.USERINFOR,SpUtile.USERID,userId+"");
        SpUtile.putString(this,SpUtile.USERINFOR,SpUtile.SESSIONID,sessionId);

        Intent intent = new Intent(MainActivity.this, ShopActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginError(String str) {

    }
    @OnClick(R.id.bt)
    public void getOnclick(View view){
        String phones = phone.getText().toString();
        String pwds = pwd.getText().toString();
        BasePresenter presenter = getPresenter();
        if (presenter instanceof LoginPagePresenter){
            ((LoginPagePresenter)presenter).doLogin(phones,pwds);
        }
    }
}
