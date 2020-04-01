package com.bw.erjiliebiao.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.erjiliebiao.R;
import com.bw.erjiliebiao.adapter.MywaiAdapter;
import com.bw.erjiliebiao.base.BaseActivity;
import com.bw.erjiliebiao.base.BasePresenter;
import com.bw.erjiliebiao.bean.ShopBean;
import com.bw.erjiliebiao.contract.ShopContract;
import com.bw.erjiliebiao.presenter.ShopPagePresenter;
import com.bw.erjiliebiao.utile.SpUtile;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopActivity extends BaseActivity implements ShopContract.ShopIView {
@BindView(R.id.rcv)
RecyclerView rcv;
@BindView(R.id.cb)
    CheckBox cb;
@BindView(R.id.tv1)
TextView tv1;
@BindView(R.id.price)
TextView pricess;
@BindView(R.id.bt)
    Button bt;
    private List<ShopBean.ResultBean> result;
    private MywaiAdapter mywaiAdapter;
    private HashMap<String, String> map;
    //定义一个集合
    ArrayList<HashMap<String, String>> selectOrderlist = new ArrayList<HashMap<String, String>>();
    @Override
    protected BasePresenter initPresenter() {
        return new ShopPagePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {
        //注册eventbus
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        //取 ID
        String userId = SpUtile.getString(this, SpUtile.USERINFOR, SpUtile.USERID);
        String sessionId = SpUtile.getString(this, SpUtile.USERINFOR, SpUtile.SESSIONID);
        //请求
        BasePresenter presenter = getPresenter();
        if (presenter instanceof ShopPagePresenter){
            ((ShopPagePresenter) presenter).doShop(Integer.valueOf(userId),sessionId);
        }

        //全选点击事件
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改变集合中checkbox的值
                //遍历集合
                for (int i=0;i<result.size();i++){

                    List<ShopBean.ResultBean.ShoppingCartListBean> list = result.get(i).getShoppingCartList();
                    //遍历每个商家的商品
                    for (int j=0;j<list.size();j++){
                        //改变集合里边的值
                        list.get(j).setShop(cb.isChecked());



                    }

                }
                //调用跟新方法重新计算
                getisshop(0);
                //刷新数据改变
                mywaiAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onShopSuccess(ShopBean shopBean) {
        result = shopBean.getResult();
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);

        //创建适配器
        mywaiAdapter = new MywaiAdapter(this, result);
        //设置适配器
        rcv.setAdapter(mywaiAdapter);
    }

    @Override
    public void onError(String str) {

    }
    //接收通知然后更新
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getisshop(Integer a){
      //初始化价格跟数
        // 量
      int price=0;
      int conut=0;
      //定义全选全不选值
      boolean x=true;

      //遍历商家
      for (int i=0;i<result.size();i++){
            //给商家定义默认为出
          boolean y=true;

          //循环商品
          List<ShopBean.ResultBean.ShoppingCartListBean> shoppingCartList = result.get(i).getShoppingCartList();
          for (int j=0;j<shoppingCartList.size();j++){
              //判断是否选中
              if (!shoppingCartList.get(j).getShop()){
                    //全选全不选
                  x=false;

                  y=false;

              }else{
                  //
                  conut+=shoppingCartList.get(j).getCount();
                  price+=shoppingCartList.get(j).getPrice()*shoppingCartList.get(j).getCount();

              }
          }
          //给商家赋值
          result.get(i).setIschek(y);

      }

      //给 全选按钮赋值
      cb.setChecked(x);
      //刷新适配器
        mywaiAdapter.notifyDataSetChanged();
        //给控件赋值
      tv1.setText("总计："+conut+"件");
      pricess.setText("总计："+price+"元");

    }
    //点击跳转
    @OnClick(R.id.bt)
    public void btClick(){

        //遍历商家
        for (int i=0;i<result.size();i++){

            List<ShopBean.ResultBean.ShoppingCartListBean> shoppingCartList = result.get(i).getShoppingCartList();
            //遍历每个商家的商品
            for (int j=0;j<shoppingCartList.size();j++){
                int commodityId = shoppingCartList.get(j).getCommodityId();
                String commodityName = shoppingCartList.get(j).getCommodityName();
                map = new HashMap<>();
                map.put("commodityId",String.valueOf(commodityId));
                map.put("commodityName",commodityName);
              if (shoppingCartList.get(j).getShop()){
                  selectOrderlist.add(map);
              }
            }
        }
        Intent intent = new Intent(ShopActivity.this, CreatOrderActivity.class);
       intent.putParcelableArrayListExtra("CreatOrder", (ArrayList<? extends Parcelable>) selectOrderlist);
        startActivity(intent);
    }
}
