package com.bw.erjiliebiao.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:16:49
 *@Description:
 * */
public class ShopBean {



    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * categoryName : 女鞋
         * shoppingCartList : [{"commodityId":27,"commodityName":"休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋","count":1,"pic":"http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg","price":139},{"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","count":2,"pic":"http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg","price":88},{"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","count":1,"pic":"http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg","price":278}]
         */

        private String categoryName;
        private List<ShoppingCartListBean> shoppingCartList;

        //定义一个字段来判断商家的多选框 默认未选中
        private boolean ischek=false;

        public boolean getIschek() {
            return ischek;
        }

        public void setIschek(boolean ischek) {
            this.ischek = ischek;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<ShoppingCartListBean> getShoppingCartList() {
            return shoppingCartList;
        }

        public void setShoppingCartList(List<ShoppingCartListBean> shoppingCartList) {
            this.shoppingCartList = shoppingCartList;
        }

        public static class ShoppingCartListBean implements Parcelable {
            /**
             * commodityId : 27
             * commodityName : 休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋
             * count : 1
             * pic : http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg
             * price : 139
             */

            private int commodityId;
            private String commodityName;
            private int count;
            private String pic;
            private int price;
            //添加一个商品的字段用来判断是否选中 默认未选中
            private boolean isShop=false;

            protected ShoppingCartListBean(Parcel in) {
                commodityId = in.readInt();
                commodityName = in.readString();
                count = in.readInt();
                pic = in.readString();
                price = in.readInt();
                isShop = in.readByte() != 0;
            }

            public static final Creator<ShoppingCartListBean> CREATOR = new Creator<ShoppingCartListBean>() {
                @Override
                public ShoppingCartListBean createFromParcel(Parcel in) {
                    return new ShoppingCartListBean(in);
                }

                @Override
                public ShoppingCartListBean[] newArray(int size) {
                    return new ShoppingCartListBean[size];
                }
            };

            public boolean getShop() {
                return isShop;
            }

            public void setShop(boolean shop) {
                isShop = shop;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(commodityId);
                dest.writeString(commodityName);
                dest.writeInt(count);
                dest.writeString(pic);
                dest.writeInt(price);
                dest.writeByte((byte) (isShop ? 1 : 0));
            }
        }
    }
}
