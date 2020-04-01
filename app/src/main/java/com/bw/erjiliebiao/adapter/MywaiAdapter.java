package com.bw.erjiliebiao.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.erjiliebiao.R;
import com.bw.erjiliebiao.activity.ShopActivity;
import com.bw.erjiliebiao.bean.ShopBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:17:47
 *@Description:
 * */
public class MywaiAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    Context context;
    List<ShopBean.ResultBean> list;
    private MyNeiAdapter myNeiAdapter;

    public MywaiAdapter(Context context, List<ShopBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.itme, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).cb2.setChecked(list.get(position).getIschek());
        ((ViewHolder)holder).name.setText(list.get(position).getCategoryName());
        //添加布局管理器  为子布局 布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL, false);
        ((ViewHolder)holder).zi_rcv.setLayoutManager(linearLayoutManager);
        //创建适配器
        myNeiAdapter = new MyNeiAdapter(context, list.get(position).getShoppingCartList());
        //设置适配器
        ((ViewHolder)holder).zi_rcv.setAdapter(myNeiAdapter);


//        Boolean aa=true;
        //设置商家的点击事件  改变商品的多选框的状态
        ((ViewHolder)holder).cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   List<ShopBean.ResultBean.ShoppingCartListBean> shoppingCartList = list.get(position).getShoppingCartList();
                   for (int j=0;j<shoppingCartList.size();j++){
                       //改变商品的值 状态
                       shoppingCartList.get(j).setShop(((ViewHolder)holder).cb2.isChecked());


                   }


                Log.i("eeeeee",((ViewHolder)holder).cb2.isChecked()+""  );
               //通知
                EventBus.getDefault().post(0);
               //刷新适配器
                myNeiAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount( ){
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
            private CheckBox cb2;
            private TextView name;
            private RecyclerView zi_rcv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cb2=itemView.findViewById(R.id.cb2);
            name=itemView.findViewById(R.id.name);
            zi_rcv=itemView.findViewById(R.id.zi_rcv);
        }
    }
}
