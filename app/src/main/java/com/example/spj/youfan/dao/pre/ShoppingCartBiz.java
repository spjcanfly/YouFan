package com.example.spj.youfan.dao.pre;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.spj.youfan.R;
import com.example.spj.youfan.bean.shop.Goods;
import com.example.spj.youfan.dao.now.ShopCartDao;

import java.util.List;

/**
 * 处理购物车的按钮的逻辑
 */
public class ShoppingCartBiz {
    /**
     * 点下全选按钮，改变所有商品选中状态,如果传递过来的是true，则改为false
     */
    public static boolean selectAll(List<Goods> list,boolean isSelectAll,ImageView ivCheck) {
        isSelectAll = !isSelectAll;
        checkItem(isSelectAll,ivCheck);
        for (int i = 0; i < list.size(); i++) {
           list.get(i).setIsSelected(isSelectAll);
        }
        return true;
    }



    /**
     * 勾与不勾选中选项
     *
     * @param ivCheck
     * @return 是否勾上，之后状态
     */
    public static boolean checkItem(boolean isSelect, ImageView ivCheck) {
        if (isSelect) {
            ivCheck.setImageResource(R.drawable.ic_checked);
        } else {
            ivCheck.setImageResource(R.drawable.ic_uncheck);
        }
        return isSelect;
    }

    /**
     * 组内所有子选项是否全部被选中
     *
     * @param list
     * @return
     */
    private static boolean isSelectAll(List<Goods> list) {
        for (int i = 0; i < list.size(); i++) {
            boolean isSelectGroup = list.get(i).isSelected();
            if (!isSelectGroup) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return 是否选择一个
     */
    public static boolean selectOne(Goods goods) {
        return  goods.isSelected();
    }

    /**=====================上面是界面改动部分，下面是数据变化部分=========================*/

    /**
     * 获取结算信息，肯定需要获取总价和数量，
     *
     */
    public static int getShoppingPrice(List<Goods> listGoods) {
        int totalPrice  = 0;
        for (int i = 0; i < listGoods.size(); i++) {
                boolean isSelectd = listGoods.get(i).isSelected();
                if (isSelectd) {
                    int price = listGoods.get(i).getSale_price();
                    int num = listGoods.get(i).getNumber();
                   totalPrice = price * num;
                }
        }
        return totalPrice;
    }

    /**
     * 得到商品的集合
     */
    public static List<Goods> getListGood(){
        return ShopCartDao.getInstance().getGoods();
    }

    /**
     * 添加某商品的数量到数据库（非通用部分，都有这个动作，但是到底存什么，未可知）
     *
     */
    public static void addGoodToCart(Goods good) {
        ShopCartDao.getInstance().saveContact(good);
    }

    /**
     * 删除某个商品,即删除其ProductID
     *
     */
    public static void delGood(Goods good) {
        boolean selected = good.isSelected();
        //如果选中，根据id删除商品
        if(selected) {
            ShopCartDao.getInstance().deleteContactByHxId(good.getGoodsID());
        }

    }

//    /** 删除全部商品 */
//    public static void delAllGoods(List<Goods> list) {
//        if(selectAll(list,)) {
//
//        }
//        ShopCartDao.getInstance().deleteAll(list);
//    }

    /** 增减数量，操作通用，数据不通用 */
    public static void addOrReduceGoodsNum(boolean isPlus, Goods goods, TextView tvNum) {
        int currentNum = goods.getNumber();
        String num = "1";
        if (isPlus) {
            num = String.valueOf(currentNum + 1);
        } else {
            int i = currentNum;
            if (i > 1) {
                num = String.valueOf(i - 1);
            } else {
                num = "1";
            }
        }
        tvNum.setText(num);
        goods.setNumber(Integer.parseInt(num));
        updateGoodsNumber(goods, num);
    }

    /**
     * 更新购物车的单个商品数量
     * @param num
     */
    public static void updateGoodsNumber(Goods good, String num) {
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
