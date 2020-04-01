package com.bw.erjiliebiao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.erjiliebiao.R;
import com.bw.erjiliebiao.bean.ShopBean;
import com.bw.erjiliebiao.view.ShopView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:18:02
 *@Description:
 * */public class MyNeiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ShopBean.ResultBean.ShoppingCartListBean> list;

    public MyNeiAdapter(Context context, List<ShopBean.ResultBean.ShoppingCartListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.zi_itme, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).cb.setChecked(list.get(position).getShop());
        Glide.with(context).load(list.get(position).getPic()).into(((ViewHolder) holder).iv);
        ((ViewHolder)holder).name.setText(list.get(position).getCommodityName());
        ((ViewHolder)holder).price.setText(list.get(position).getPrice()+"");
        //自定义view
        ((ViewHolder)holder).sv.setData(list.get(position).getCount());
        //接收自定义中回调够来的加减方法
        ((ViewHolder)holder).sv.OnConuts(new ShopView.OnConuts() {
            @Override
            public void onConuts(int count) {
                //将集合里的count字段改变
                list.get(position).setCount(count);
                EventBus.getDefault().post(0);
            }
        });

        //给CheckBox写监听改变监听事件
        ((ViewHolder)holder).cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              //改变集合
                list.get(position).setShop(isChecked);


                //更新ui 通知
                EventBus.getDefault().post(0);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cb;
        private ImageView iv;
        private TextView price;
        private TextView name;
        private ShopView sv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cb=itemView.findViewById(R.id.cb);
            iv=itemView.findViewById(R.id.iv);
            price=itemView.findViewById(R.id.price);
            name=itemView.findViewById(R.id.name);
            sv=itemView.findViewById(R.id.sv);
        }
    }
}
