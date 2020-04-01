package com.bw.erjiliebiao.model;

import com.bw.erjiliebiao.bean.ShopBean;
import com.bw.erjiliebiao.contract.ShopContract;
import com.bw.erjiliebiao.utile.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:16:59
 *@Description:
 * */
public class ShopPageModel implements ShopContract.ShopIModel {
    @Override
    public void doShop(int userId, String sessionId, ShopICallBack iCallBack) {
        NetUtile.getInstance().getApi().doShop(userId,sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopBean shopBean) {
                        if (iCallBack!=null){
                            iCallBack.onShopSuccess(shopBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iCallBack!=null){
                            iCallBack.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
