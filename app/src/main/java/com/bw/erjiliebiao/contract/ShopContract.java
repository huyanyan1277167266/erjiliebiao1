package com.bw.erjiliebiao.contract;

import com.bw.erjiliebiao.base.IBaseView;
import com.bw.erjiliebiao.bean.ShopBean;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:16:47
 *@Description:
 * */
public interface ShopContract {
    interface ShopIView extends IBaseView{
    void onShopSuccess(ShopBean shopBean);
    void onError(String str);
    }
    interface ShopIPresenter{
        void doShop(int userId,String sessionId);
    }
    interface ShopIModel{
        void doShop(int userId,String sessionId,ShopICallBack iCallBack);
        interface ShopICallBack{
            void onShopSuccess(ShopBean shopBean);
            void onError(String str);
        }
    }
}
