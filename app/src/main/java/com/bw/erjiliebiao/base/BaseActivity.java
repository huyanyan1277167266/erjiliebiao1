package com.bw.erjiliebiao.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView{
P mPresenter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        mPresenter=initPresenter();
        initView();
        getData();
    }
    public P getPresenter(){
        return mPresenter;
    }
    protected abstract P initPresenter();
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void getData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.datachView();
            mPresenter=null;
            //解绑
            bind.unbind();
        }

    }
}
