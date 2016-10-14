package com.example.spj.youfan.dao.now;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.spj.youfan.bean.shop.Goods;
import com.example.spj.youfan.dao.pre.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spj on 2016/10/12.
 */
public class ShopCartDao {
    private volatile static ShopCartDao instance = null;

    /**
     * 获取SimpleDemoDB实例
     */
    public static ShopCartDao getInstance() {
        if (instance == null) {
            synchronized (ShopCartDao.class) {
                if (instance == null) {
                    instance = new ShopCartDao(mhelper);
                }
            }
        }
        return instance;
    }


    private static DBHelper mhelper;

    public ShopCartDao(DBHelper helper) {
        this.mhelper = helper;
    }

    // 获取所有商品
    public List<Goods> getGoods() {
        SQLiteDatabase db = mhelper.getWritableDatabase();
        String sql = "select * from " + mhelper.TB_SHOPPING_CART;
        Cursor cursor = db.rawQuery(sql, null);
        List<Goods> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Goods goods = new Goods();
            goods.setGoodsID(cursor.getString(cursor.getColumnIndex(mhelper.COL_SHOPID)));
            goods.setName(cursor.getString(cursor.getColumnIndex(mhelper.COL_SHOPNAME)));
            goods.setSale_price(cursor.getInt(cursor.getColumnIndex(mhelper.COL_PRICE)));
            goods.setMainImage(cursor.getString(cursor.getColumnIndex(mhelper.COL_IMAGE)));
            goods.setNumber(cursor.getInt(cursor.getColumnIndex(mhelper.COL_NUM)));
            list.add(goods);
        }
        //关闭资源
        cursor.close();

        return list;
    }


    //添加单个商品
    public void saveContact(Goods goods) {
        //  获取数据库链接
        SQLiteDatabase db = mhelper.getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put(mhelper.COL_SHOPID,goods.getGoodsID());
        value.put(mhelper.COL_SHOPNAME,goods.getName());
        value.put(mhelper.COL_PRICE,goods.getSale_price());
        value.put(mhelper.COL_IMAGE,goods.getMainImage());
        value.put(mhelper.COL_NUM,goods.getNumber());
        db.replace(mhelper.TB_SHOPPING_CART, null, value);
    }

    //添加好多个商品（用于没有接口的情况）
    public void saveGoods(List<Goods> list){
        if(list != null && list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                Goods goods = list.get(i);
                saveContact(goods);
            }
        }
    }


    // 删除单个的商品
    public void deleteContactByHxId(String id) {
        if (id == null) {
            return;
        }
        SQLiteDatabase db = mhelper.getWritableDatabase();
        db.delete(mhelper.TB_SHOPPING_CART,mhelper.COL_SHOPID+"=?",new String[]{id});
    }

    //删除所有的商品
    public void deleteAll(List<Goods> list){
        for (int i = 0; i < list.size(); i++) {
            deleteContactByHxId(list.get(i).getGoodsID());
        }
    }

    /**
     * 修改购物车中某件商品的信息
     *
     * @param productID 规格ID
     * @param num       商品数量
     */
    public void updateGoodsNum(String productID, int num) {
        if (productID == null || "".equals(productID) || num == 0 ) {
            return;
        }
        SQLiteDatabase db = mhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (productID != null && !"".equals(productID) && num != 0 ) {
            values.put(mhelper.COL_NUM, num);
            db.update(mhelper.TB_SHOPPING_CART, values, mhelper.COL_SHOPID + "=?", new String[]{productID});
        }
    }
}
