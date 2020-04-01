package com.bw.erjiliebiao.contract;

import com.bw.erjiliebiao.base.IBaseView;
import com.bw.erjiliebiao.bean.CreatOrderBean;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:14:36
 *@Description:
 * */public interface CreatOrderContract {
     interface OrderView extends IBaseView{
         void OrderSuccess(CreatOrderBean creatOrderBean);
         void OrderError(String str);
     }
     interface OrderPresenter{
         void doCreatOrder(int userId,String sessionId,String orderinfo,double totaiprice,int addressid);
     }
     interface OrderModel{
         void doCreatOrder(int userId,String sessionId,String orderinfo,double totaiprice,int addressid,OrderICallBack iCallBack);
         interface OrderICallBack{
             void OrderSuccess(CreatOrderBean creatOrderBean);
             void OrderError(String str);
         }
     }
}
