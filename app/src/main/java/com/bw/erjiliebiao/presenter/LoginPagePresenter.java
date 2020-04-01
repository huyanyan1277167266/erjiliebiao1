package com.bw.erjiliebiao.presenter;

import com.bw.erjiliebiao.base.BasePresenter;
import com.bw.erjiliebiao.base.IBaseView;
import com.bw.erjiliebiao.bean.LoginBean;
import com.bw.erjiliebiao.contract.LoginContract;
import com.bw.erjiliebiao.model.LoginPageModel;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:16:20
 *@Description:
 * */public class LoginPagePresenter extends BasePresenter implements LoginContract.LoginIPresenter {

    private LoginPageModel mModel;

    public LoginPagePresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mModel = new LoginPageModel();
    }

    @Override
    public void doLogin(String phone, String pwd) {
        mModel.doLogin(phone, pwd, new LoginContract.LoginIModel.LoginIcallBack() {
            @Override
            public void onLoginSuccess(LoginBean loginBean) {
                IBaseView view = getView();
                if (view instanceof LoginContract.LoginIView){
                    ((LoginContract.LoginIView)view).onLoginSuccess(loginBean);
                }
            }

            @Override
            public void onLoginError(String str) {
                IBaseView view = getView();
                if (view instanceof LoginContract.LoginIView){
                    ((LoginContract.LoginIView)view).onLoginError(str);
                }
            }
        });
    }
}
