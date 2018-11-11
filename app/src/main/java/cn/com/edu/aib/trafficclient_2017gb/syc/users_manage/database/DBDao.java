package cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.bean.CarBean;
import cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.bean.HistoryBean;
import cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.bean.CarManageBean;

public class DBDao implements DBImpl {

    DBHelper mHelper;
    Context mContext;

    public DBDao(Context context) {
        this.mContext = context;
        mHelper = new DBHelper(mContext);
    }

    @Override
    public void initData() {
        try {
            SQLiteDatabase db = mHelper.getWritableDatabase();
            // "1","宝马","粤A 888888","张三","98"
            int param1,param2,param3,param4;
            String str1="",str2="",str3="",str4="";
            for(int i = 0;i<18;i++){
                param1 = (int) (Math.random()*4+1);
                param2 = (int) (Math.random()*100000+1);
                param3 = (int) (Math.random()*4+1);
                param4 = (int) (Math.random()*200+1);

                // 车型
                if (param1==1){str1="奔驰";}
                if (param1==2){str1="宝马";}
                if (param1==3){str1="现代";}
                if (param1==4){str1="雪佛兰";}

                // 车牌
                if (param3==1){str2="粤A "+param2;}
                if (param3==2){str2="粤B "+param2;}
                if (param3==3){str2="粤C "+param2;}
                if (param3==4){str2="粤D "+param2;}

                // 车主
                if (param3==1){str3="锵锵老师";}
                if (param3==2){str3="吖_裕创";}
                if (param3==3){str3="吖_乾鑫";}
                if (param3==4){str3="吖_盛昌";}

                // 余额
                str4 = param4+"";

                db.execSQL("insert into etcUsers (carType,carNum,carOwner,carMoney,czTF) values(?,?,?,?,'false')",new Object[]{str1,str2,str3,str4});

            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("数据库初始化数据出错!");
        }

    }

    @Override
    public ArrayList<CarManageBean> queryAll() {
        ArrayList<CarManageBean> list = new ArrayList<>();
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from etcUsers",null);
        CarManageBean cmb;
        while (cursor.moveToNext()){
            String id = cursor.getInt(cursor.getColumnIndex("_id"))+"";
            String carType = cursor.getString(cursor.getColumnIndex("carType"));
            String carNum = cursor.getString(cursor.getColumnIndex("carNum"));
            String carOwner = cursor.getString(cursor.getColumnIndex("carOwner"));
            String carMoney = cursor.getString(cursor.getColumnIndex("carMoney"));

            cmb = new CarManageBean(id,carType,carNum,carOwner,carMoney);
            list.add(cmb);
            System.out.println("cmb:"+cmb);
        }

        cursor.close();
        db.close();

        Toast.makeText(mContext,"数据获取成功!",Toast.LENGTH_SHORT).show();

        return list;
    }

    @Override
    public boolean recharge(HashMap<String, String> map) {// 参数一:carNum 参数二:carMoney

        try{
            SQLiteDatabase db = mHelper.getWritableDatabase();
            Date date = new Date();// new Date()为获取当前系统时间

            //需要改变的值 : 充值金额 余额 充值人 充值日期 充值周几 充值时间
            SimpleDateFormat df;Cursor mCursor;
            String key;//车牌号-
            String value="";//充值金额-
            String carMoney;//余额-
            String czPeople="User2";
            String czDate;//日期-
            String czWeek;//周几-
            String czTime;//具体时间-
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()){

                key = iterator.next().toString();//充值的车牌号
                value = map.get(key);//充值金额

                mCursor = db.rawQuery("select * from etcUsers where carNum=?",new String[]{key});
                if (mCursor.moveToNext()){
                    carMoney = mCursor.getString(mCursor.getColumnIndex("carMoney"));

                    df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                    czDate = df.format(date);

                    df = new SimpleDateFormat("EEEE");//设置周几格式
                    czWeek = df.format(date);

                    df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置具体时间格式
                    czTime = df.format(date);

                    carMoney = Integer.parseInt(carMoney)+Integer.parseInt(value)+"";

                    db.execSQL("update etcUsers set czDate=?,czWeek=?,czTime=?,czPeople=?,czMoney=?,carMoney=?,czTF=? where carNum=?",new Object[]{czDate,czWeek,czTime,czPeople,value,carMoney,"true",key});
                }

            }
            Toast.makeText(mContext,"充值成功!",Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(mContext,"充值失败!",Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    @Override
    public ArrayList<HistoryBean> queryHisstory() {

        SQLiteDatabase db = mHelper.getWritableDatabase();
        ArrayList<HistoryBean> arrHistory = new ArrayList<>();
        HistoryBean historyBean;
        Cursor cursor = db.rawQuery("select * from etcUsers where czTF=?",new String[]{"true"});
        while (cursor.moveToNext()){
            //String h_date, String h_week, String h_czPeo, String h_carNum, String h_czMon, String h_carMon, String h_time
            //czDate=?,czWeek=?,czTime=?,czPeople=?,czMoney=?,carMoney=?,czTF=? where carNum=?
            String czDate = cursor.getString(cursor.getColumnIndex("czDate"));
            String czWeek = cursor.getString(cursor.getColumnIndex("czWeek"));
            String czPeople = cursor.getString(cursor.getColumnIndex("czPeople"));
            String carNum = cursor.getString(cursor.getColumnIndex("carNum"));
            String czMoney = cursor.getString(cursor.getColumnIndex("czMoney"));
            String carMoney = cursor.getString(cursor.getColumnIndex("carMoney"));
            String czTime = cursor.getString(cursor.getColumnIndex("czTime"));

            //String h_date, String h_week, String h_czPeo, String h_carNum, String h_czMon, String h_carMon, String h_time
            historyBean = new HistoryBean(czDate,czWeek,czPeople,carNum,czMoney,carMoney,czTime);

            arrHistory.add(historyBean);


        }

        return arrHistory;


    }


    @Override
    public ArrayList<CarBean> queryCars(String userName) {

        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from etcUsers where carOwner=?",new String[]{userName});
        CarBean cb;
        ArrayList<CarBean> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            String carType = cursor.getString(cursor.getColumnIndex("carType"));
            String carNum = cursor.getString(cursor.getColumnIndex("carNum"));

            cb = new CarBean(carType,carNum);
            arrayList.add(cb);

        }


        return arrayList;
    }
}
