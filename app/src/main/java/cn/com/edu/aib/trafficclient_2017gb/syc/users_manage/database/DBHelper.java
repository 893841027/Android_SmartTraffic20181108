package cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "etcUsers", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建数据库
        //String id, String carType, String carNum, String carOwner, String carMoney,
        //String czTF, String czMoney, String czPeople, String czDate, String czWeek, String czTime
        db.execSQL("create table etcUsers(" +
                "_id integer primary key autoincrement," +
                "carType varchar(60)," +
                "carNum varchar(60)," +
                "carOwner varchar(60)," +
                "carMoney varchar(60)," +
                "czTF varchar(60)," +
                "czMoney varchar(60)," +
                "czPeople varchar(60)," +
                "czDate varchar(60)," +
                "czWeek varchar(60)," +
                "czTime varchar(60))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
