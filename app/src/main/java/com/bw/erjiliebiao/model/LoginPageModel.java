package com.bw.erjiliebiao.model;

import com.bw.erjiliebiao.bean.LoginBean;
import com.bw.erjiliebiao.contract.LoginContract;
import com.bw.erjiliebiao.utile.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:16:20
 *@Description:
 * */public class LoginPageModel implements LoginContract.LoginIModel {
    @Override
    public void doLogin(String phone, String pwd, LoginIcallBack icallBack) {
        NetUtile.getInstance().getApi().doLogin(phone,pwd)
                //在IO线程上
                .subscribeOn(Schedulers.io())
                //在主线程上
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (icallBack!=null){
                            icallBack.onLoginSuccess(loginBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (icallBack!=null){
                            icallBack.onLoginError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
