package com.bw.erjiliebiao.contract;

import com.bw.erjiliebiao.base.IBaseView;
import com.bw.erjiliebiao.bean.LoginBean;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:16:17
 *@Description:
 * */public interface LoginContract {
     interface LoginIView extends IBaseView{
         void onLoginSuccess(LoginBean loginBean);
         void onLoginError(String str);
     }
     interface LoginIPresenter{
         void doLogin(String phone,String pwd);
     }
     interface LoginIModel{
         void doLogin(String phone,String pwd,LoginIcallBack icallBack);
         interface LoginIcallBack{
             void onLoginSuccess(LoginBean loginBean);
             void onLoginError(String str);
         }
     }
}
