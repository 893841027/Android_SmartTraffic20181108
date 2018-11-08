package cn.com.edu.aib.trafficclient_2017gb.osc.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by teacher on 2018/11/8.
 */

public class SQLHelper extends SQLiteOpenHelper{

    String content = "驾驶机动车在高速公路，城市快速路以外的道路上不按规定车道行驶。";
    public SQLHelper(Context context) {
        super(context, "oscdb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table peccancy(carnum varchar(20),koufen Integer,fakuan Integer,content varchar(200),date varchar(30),position varchar(10));";
        db.execSQL(sql);
        db.execSQL("insert into peccancy values(\'A10001\',0,200,\'"+content+"\',\'2016-2-3 12:33:23\',\'学院路\');");
        db.execSQL("insert into peccancy values(\'A10001\',0,200,\'"+content+"\',\'2017-2-3 15:33:23\',\'学院路\');");
        db.execSQL("insert into peccancy values(\'A10001\',0,200,\'"+content+"\',\'2018-2-3 15:34:23\',\'学院路\');");
        db.execSQL("insert into peccancy values(\'A10002\',0,200,\'"+content+"\',\'2018-2-3 15:34:23\',\'学院路\');");
        db.execSQL("insert into peccancy values(\'A10003\',0,200,\'"+content+"\',\'2018-2-3 15:34:23\',\'学院路\');");
        db.execSQL("insert into peccancy values(\'A10003\',0,200,\'"+content+"\',\'2018-2-3 15:34:23\',\'学院路\');");
        db.execSQL("insert into peccancy values(\'A10003\',0,200,\'"+content+"\',\'2018-2-3 15:34:23\',\'学院路\');");
        db.execSQL("insert into peccancy values(\'A10003\',0,200,\'"+content+"\',\'2018-2-3 15:34:23\',\'学院路\');");
        db.execSQL("insert into peccancy values(\'A10004\',0,200,\'"+content+"\',\'2018-2-3 15:34:23\',\'学院路\');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
