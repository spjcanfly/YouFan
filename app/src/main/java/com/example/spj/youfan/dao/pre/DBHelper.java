package com.example.spj.youfan.dao.pre;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 *
 */
public class DBHelper extends SQLiteOpenHelper {

    /** 数据库名称常量 */
    public static final String DATABASE_NAME = "shopping_cart.db1";
    /** 数据库版本常量 */
    private static final int DATABASE_VERSION = 1;
    /** 购物车表 */
    public static final String TB_SHOPPING_CART = "tb_shopping_cart";

    public static final String COL_SHOPNAME="name";
    public static final String COL_SHOPID="id";
    public static final String COL_PRICE="price";
    public static final String COL_IMAGE="image";
    public static final String COL_NUM="num";



    public DBHelper(Context context,String name) {
        super(context,name,null,DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        String CREATE_TB_SHOPPING_CART = "create table "
                + TB_SHOPPING_CART + "("
                + COL_SHOPID + " text primary key,"
                + COL_SHOPNAME + " text,"
                + COL_PRICE + " integer,"
                + COL_NUM + " integer,"
                + COL_IMAGE + " text);";
        db.execSQL(CREATE_TB_SHOPPING_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
//        db.execSQL("DROP TABLE IF EXISTS " + TB_SHOPPING_CART);
    }

}
