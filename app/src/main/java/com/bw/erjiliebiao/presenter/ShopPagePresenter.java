package com.bw.erjiliebiao.presenter;

import com.bw.erjiliebiao.base.BasePresenter;
import com.bw.erjiliebiao.base.IBaseView;
import com.bw.erjiliebiao.bean.ShopBean;
import com.bw.erjiliebiao.contract.ShopContract;
import com.bw.erjiliebiao.model.ShopPageModel;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:16:59
 *@Description:
 * */
public class ShopPagePresenter extends BasePresenter implements ShopContract.ShopIPresenter {

    private ShopPageModel mModel;

    public ShopPagePresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    public void doShop(int userId, String sessionId) {
        mModel.doShop(userId, sessionId, new ShopContract.ShopIModel.ShopICallBack() {
            @Override
            public void onShopSuccess(ShopBean shopBean) {
                IBaseView view = getView();
                if (view instanceof ShopContract.ShopIView){
                    ((ShopContract.ShopIView)view).onShopSuccess(shopBean);
                }
            }

            @Override
            public void onError(String str) {
                IBaseView view = getView();
                if (view instanceof ShopContract.ShopIView){
                    ((ShopContract.ShopIView) view).onError(str);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        mModel = new ShopPageModel();
    }
}
