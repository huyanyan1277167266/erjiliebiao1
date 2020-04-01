package com.bw.erjiliebiao.utile;

import com.bw.erjiliebiao.bean.CreatOrderBean;
import com.bw.erjiliebiao.bean.LoginBean;
import com.bw.erjiliebiao.bean.ShopBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:16:00
 *@Description:
 * */
public interface Apis {
    @POST("user/v1/login")
    @FormUrlEncoded
    Observable <LoginBean> doLogin (@Field("phone")String phone,@Field("pwd") String pwd);

    @GET("order/verify/v1/findShoppingCart")
    Observable<ShopBean>doShop(@Header("userId")int userId,@Header("sessionId")String sessionId);
    @POST("order/verify/v1/createOrder")
    @FormUrlEncoded
    Observable<CreatOrderBean>doCreatOrder(@Header("userId")int userId,@Header("sessionId")String sessionId,@Field("orderinfo") String orderinfo,@Field("totaiprice")double totaiprice,@Field("addressid")int addressid );
}
