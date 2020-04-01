package com.bw.erjiliebiao.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.util.Log;

import com.bw.erjiliebiao.R;
import com.bw.erjiliebiao.base.BaseActivity;
import com.bw.erjiliebiao.base.BasePresenter;
import com.bw.erjiliebiao.bean.CreatOrderBean;
import com.bw.erjiliebiao.contract.CreatOrderContract;
import com.bw.erjiliebiao.presenter.CreateOrderPresenter;

import java.util.ArrayList;

public class CreatOrderActivity extends BaseActivity implements CreatOrderContract.OrderView {



    @Override
    protected BasePresenter initPresenter() {
        return new CreateOrderPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_creat_order;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        ArrayList<String> creatOrderlist = intent.getStringArrayListExtra("CreatOrder");
        Log.i("xxx",creatOrderlist+"");

        BasePresenter presenter = getPresenter();
        if (presenter instanceof CreateOrderPresenter){
           // ((CreateOrderPresenter) presenter).doCreatOrder("33330","158572414925633330",);
        }
    }

    @Override
    public void OrderSuccess(CreatOrderBean creatOrderBean) {

    }

    @Override
    public void OrderError(String str) {

    }
}
