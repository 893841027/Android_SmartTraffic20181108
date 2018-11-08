package cn.com.edu.aib.trafficclient_2017gb.syc.personal_center;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.com.edu.aib.trafficclient_2017gb.R;
import cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.bean.CarBean;
import cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.view.CarItemView;
import cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.database.DBDao;

public class Tab1Activity extends Activity {
    private ImageView tab1_headImg;
    private TextView tab1_name;
    private TextView tab1_sex;
    private TextView tab1_idNum;
    private TextView tab1_regTime;
    private LinearLayout tab1_list;
    private TextView tab1_phoneNum;
    private ArrayList<CarBean> mArrCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syc_center_tab1);

        initView();
        initData();

        CarItemView civ;
        for(int i = 0;i<mArrCars.size();i++){
            civ = new CarItemView(this);
            civ.mV_carNum.setText(mArrCars.get(i).getCarNum());

            if (mArrCars.get(i).getCarType().equals("宝马")){
                civ.mV_carLogo.setImageResource(R.drawable.baoma);
            }else if (mArrCars.get(i).getCarType().equals("奔驰")){
                civ.mV_carLogo.setImageResource(R.drawable.benchi);
            }else if (mArrCars.get(i).getCarType().equals("现代")){
                civ.mV_carLogo.setImageResource(R.drawable.xiandai);
            }else if (mArrCars.get(i).getCarType().equals("雪佛兰")){
                civ.mV_carLogo.setImageResource(R.drawable.xuefulan);
            }

            tab1_list.addView(civ);
        }





    }

    private void initData() {
        String userName = "锵锵老师";

        tab1_name.setText("姓名 : 锵锵老师");
        tab1_sex.setText("性别 : 男");
        tab1_phoneNum.setText("手机号 : 13025666015");
        tab1_regTime.setText("2018.11.08");

        if (tab1_sex.getText().toString().equals("男")){
            tab1_headImg.setImageResource(R.drawable.touxiang_1);
        }else{
            tab1_headImg.setImageResource(R.drawable.touxiang_2);
        }

        DBDao dao = new DBDao(this);
        mArrCars = dao.queryCars(userName);
        System.out.println("arrCars:"+ mArrCars);


    }

    private void initView() {
        tab1_headImg = ((ImageView) findViewById(R.id.tab1_headImg));
        tab1_name = ((TextView) findViewById(R.id.tab1_name));
        tab1_sex = ((TextView) findViewById(R.id.tab1_sex));
        tab1_idNum = ((TextView) findViewById(R.id.tab1_idNum));
        tab1_regTime = ((TextView) findViewById(R.id.tab1_regTime));
        tab1_list = ((LinearLayout) findViewById(R.id.tab1_list));
        tab1_phoneNum = ((TextView) findViewById(R.id.tab1_phoneNum));
    }

}
