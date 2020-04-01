package com.bw.erjiliebiao.model;

import com.bw.erjiliebiao.bean.CreatOrderBean;
import com.bw.erjiliebiao.contract.CreatOrderContract;
import com.bw.erjiliebiao.utile.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:14:39
 *@Description:
 * */public class CreateOrderModel implements CreatOrderContract.OrderModel {
    @Override
    public void doCreatOrder(int userId, String sessionId, String orderinfo, double totaiprice, int addressid, OrderICallBack iCallBack) {
        NetUtile.getInstance().getApi().doCreatOrder(userId,sessionId,orderinfo,totaiprice,addressid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreatOrderBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CreatOrderBean creatOrderBean) {
                        if (iCallBack!=null){
                            iCallBack.OrderSuccess(creatOrderBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iCallBack!=null){
                            iCallBack.OrderError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
