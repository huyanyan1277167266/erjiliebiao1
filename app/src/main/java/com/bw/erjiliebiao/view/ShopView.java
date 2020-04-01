package com.bw.erjiliebiao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.erjiliebiao.R;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:18:25
 *@Description:
 * */
public class ShopView extends LinearLayout implements View.OnClickListener {

    private TextView sl;
    private TextView jia;
    private TextView jian;

    public ShopView(Context context) {
        super(context);
        init(context);
    }



    public ShopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    //定义传值的方法
    public void setData(int b){
        if (b<1){
            return;
        }
       sl.setText(b+"");
    }
    //加载布局自定义方法
    private void init(Context context) {
        View view = View.inflate(context, R.layout.view, null);
        //找控件
        jia = view.findViewById(R.id.jia);
        sl = view.findViewById(R.id.sl);
        jian = view.findViewById(R.id.jian);
        //给加减设置点击事件
        jia.setOnClickListener(this);
        jian.setOnClickListener(this);
        //添加
        addView(view);

    }
    //点击事件
    @Override
    public void onClick(View v) {
        int count=0;

        //先拿到数量
        String s = sl.getText().toString();
        try {
            //转型
            count = Integer.valueOf(s);
        }catch (Exception e){
            e.printStackTrace();
        }

        switch (v.getId()){
            //设置加
            case R.id.jian:

                count++;
                //赋值
                sl.setText(String.valueOf(count));
                //回调接口 传值
                monConuts.onConuts(count);
            break;
            //设置减
            case R.id.jia:
                if(count<=1){
                    return;
                }
                count--;
                sl.setText(String.valueOf(count));
                //回调接口 传值
                monConuts.onConuts(count);
                break;
                default:

        }
    }
    private OnConuts monConuts;
    public void OnConuts(OnConuts onConuts){
        monConuts=onConuts;
    }
    //接口回调   回到最里边的Adapter 然后改变集合里边的count值
    public interface OnConuts{
        void onConuts(int count);
    }
}
