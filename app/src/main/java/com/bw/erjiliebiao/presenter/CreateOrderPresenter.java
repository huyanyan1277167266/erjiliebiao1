package com.bw.erjiliebiao.presenter;

import com.bw.erjiliebiao.base.BasePresenter;
import com.bw.erjiliebiao.base.IBaseView;
import com.bw.erjiliebiao.bean.CreatOrderBean;
import com.bw.erjiliebiao.contract.CreatOrderContract;
import com.bw.erjiliebiao.model.CreateOrderModel;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:14:42
 *@Description:
 * */public class CreateOrderPresenter extends BasePresenter implements CreatOrderContract.OrderPresenter {

    private CreateOrderModel mModel;

    public CreateOrderPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mModel = new CreateOrderModel();
    }

    @Override
    public void doCreatOrder(int userId, String sessionId, String orderinfo, double totaiprice, int addressid) {
        mModel.doCreatOrder(userId, sessionId, orderinfo, totaiprice, addressid, new CreatOrderContract.OrderModel.OrderICallBack() {
            @Override
            public void OrderSuccess(CreatOrderBean creatOrderBean) {
                IBaseView view = getView();
                if (view instanceof CreatOrderContract.OrderView){
                    ((CreatOrderContract.OrderView) view).OrderSuccess(creatOrderBean);
                }
            }

            @Override
            public void OrderError(String str) {
                IBaseView view = getView();
                if (view instanceof CreatOrderContract.OrderView){
                    ((CreatOrderContract.OrderView) view).OrderError(str);
                }
            }
        });
    }
}
