package com.bw.erjiliebiao.base;

import java.lang.ref.WeakReference;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:15:24
 *@Description:
 * */
public abstract class BasePresenter <V extends IBaseView> {

    private WeakReference<V> mWeakReference;

    public BasePresenter(V v) {
        mWeakReference = new WeakReference<>(v);
        initModel();
    }
    public V getView(){
        if (mWeakReference!=null){
            return mWeakReference.get();
        }
        return null;
    }
    protected abstract void initModel();
    public void datachView(){
        if (mWeakReference!=null){
            mWeakReference.clear();
            mWeakReference=null;
        }
    }
}
