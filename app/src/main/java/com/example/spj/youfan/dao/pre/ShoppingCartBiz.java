package com.example.spj.youfan.dao.pre;

import com.example.spj.youfan.bean.shop.Goods;
import com.example.spj.youfan.dao.now.ShopCartDao;

import java.util.List;

/**
 * 处理购物车的按钮的逻辑
 */
public class ShoppingCartBiz {


    /**
     * 获取结算信息，肯定需要获取总价和数量，
     */
    public static int getShoppingPrice(List<Goods> listGoods) {
        int totalPrice = 0;
        for (int i = 0; i < listGoods.size(); i++) {
            boolean isSelectd = listGoods.get(i).isSelected();
            if (isSelectd) {
                int price = listGoods.get(i).getSale_price();
                int num = listGoods.get(i).getNumber();
                totalPrice = totalPrice + price * num;
            }
        }
        return totalPrice;
    }

    /**
     * 得到商品的集合
     */
    public static List<Goods> getListGood() {
        return ShopCartDao.getInstance().getGoods();
    }

    /**
     * 添加某商品的数量到数据库（非通用部分，都有这个动作，但是到底存什么，未可知）
     */
    public static void addGoodToCart(Goods good) {
        ShopCartDao.getInstance().saveContact(good);
    }

    /**
     * 删除某个商品,即删除其ProductID
     */
    public static void delGood(List<Goods> list) {
        if(list != null && list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).isSelected()) {
                    ShopCartDao.getInstance().deleteContactByHxId(list.get(i).getGoodsID());
                }
            }
        }
    }

//    /** 删除全部商品 */
//    public static void delAllGoods(List<Goods> list) {
//        if(selectAll(list,)) {
//
//        }
//        ShopCartDao.getInstance().deleteAll(list);
//    }

    /**
     * 更新购物车的单个商品数量
     *
     * @param num
     */
    public static void updateGoodsNumber(Goods good, int num) {
        String goodsID = good.getGoodsID();
        ShopCartDao.getInstance().updateGoodsNum(goodsID, num);
    }


    /**
     * 查询购物车商品总数量
     * <p/>
     * 统一使用该接口，而就行是通过何种方式获取数据，数据库、SP、文件、网络，都可以
     *
     * @return
     */
    public static int getGoodsCount() {
        return ShopCartDao.getInstance().getGoods().size();
    }

}
