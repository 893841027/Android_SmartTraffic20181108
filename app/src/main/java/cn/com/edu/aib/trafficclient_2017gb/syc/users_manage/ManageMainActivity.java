package cn.com.edu.aib.trafficclient_2017gb.syc.users_manage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.com.edu.aib.trafficclient_2017gb.R;
import cn.com.edu.aib.trafficclient_2017gb.origin_code.Activity_Main;
import cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.CenterMainActivity;
import cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.adapter.CarManageAdapter;
import cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.bean.CarManageBean;
import cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.database.DBDao;

public class ManageMainActivity extends AppCompatActivity {

    private ListView manage_list;
    private ArrayList<CarManageBean> mArrayList;
    private SharedPreferences mSp;
    private SharedPreferences.Editor mEdit;
    private DBDao mDao;
    private CarManageAdapter mCarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syc_manage_main);


        initView();
        initData();

        mCarAdapter = new CarManageAdapter(this,mArrayList);
        manage_list.setAdapter(mCarAdapter);

        Thread thread = new Thread(){
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            mCarAdapter.notifyDataSetChanged();
                        }

                    }
                });
            }
        };




    }

    private void initData() {
        /*mArrayList = new ArrayList<>();
        CarManageBean cmb = new CarManageBean("1","宝马","粤A 888888","张三","98","","","","","","");
        mArrayList.add(cmb);
        cmb = new CarManageBean("2","奔驰","粤A 888888","张三","98","","","","","","");
        mArrayList.add(cmb);
        cmb = new CarManageBean("3","现代","粤A 888888","张三","98","","","","","","");
        mArrayList.add(cmb);
        cmb = new CarManageBean("4","雪佛兰","粤A 888888","张三","98","","","","","","");
        mArrayList.add(cmb);
        for(int i = 5;i<18;i++){
            cmb = new CarManageBean(i+"","宝马","粤A 666666","张三","98","","","","","","");
            mArrayList.add(cmb);
        }*/

        mSp = getSharedPreferences("syc",MODE_PRIVATE);
        mEdit = mSp.edit();
        mEdit.putString("warnNum","-1");
        mDao = new DBDao(this);

        String initNum = mSp.getString("firstInit","0");//0没有 1有过
        if (initNum.equals("0")){
            mDao.initData();
            mEdit.putString("firstInit","1");
            mEdit.commit();
        }


        mArrayList = mDao.queryAll();

    }

    private void initView() {
        manage_list = ((ListView) findViewById(R.id.manage_list));
    }

    public void plRecharge(View view) {
        final AlertDialog myDialog = new AlertDialog.Builder(this).create();
        myDialog.show();
        myDialog.getWindow().setContentView(R.layout.syc_manage_dialog);

        TextView dialog_carNum = myDialog.getWindow().findViewById(R.id.dialog_carNum);
        final EditText dialog_czMoney = myDialog.getWindow().findViewById(R.id.dialog_czMoney);
        Button dialog_btn1 = myDialog.getWindow().findViewById(R.id.dialog_btn1);
        Button dialog_btn2 = myDialog.getWindow().findViewById(R.id.dialog_btn2);

        // HashMap存放充值的id,以及充值的金额
        HashMap<String,String> map = new HashMap<>();
        final List rechargeList = new ArrayList();//保存被勾选的车牌号

        final HashMap<Integer,Boolean> plMap = mCarAdapter.mapChecked;
        String str = "";
        for(int i = 0;i<plMap.size();i++){
            if (plMap.get(i)){
                CarManageBean cmb = mArrayList.get(i);
                rechargeList.add(cmb.getCarNum());
                str+= (cmb.getCarNum()+" ");
            }
        }

        dialog_carNum.setText(str);

        // ------------------------
        dialog_czMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String strMoney =dialog_czMoney.getText().toString();
                int[] arrInt = {0,1,2,3,4,5,6,7,8,9};
                if (strMoney.equals("")){

                }else{
                    //money = Integer.parseInt(dialog_czMoney.getText().toString());
                    //strNum.matches("[0-9]{1,}");

                    if (strMoney.matches("[0-9]{1,}")){
                        if (Integer.parseInt(strMoney)<1||Integer.parseInt(strMoney)>999){
                            Toast.makeText(getApplicationContext(),"你输入的金额有误,请重新输入!",Toast.LENGTH_SHORT).show();
                            dialog_czMoney.getText().clear();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"含有非法无效字符,请重新输入!",Toast.LENGTH_SHORT).show();
                        dialog_czMoney.getText().clear();
                    }
                }
            }
        });


        // ------------------------

        dialog_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBDao dao = new DBDao(getApplicationContext());
                HashMap<String,String> czMap = new HashMap<>();

                // 存放要充值的车牌号以及充值的金额
                for(int i = 0;i<rechargeList.size();i++){
                    czMap.put(rechargeList.get(i).toString(),dialog_czMoney.getText().toString());
                }

                //TODO 立马刷新---刷新选择项
                for(int i = 0;i<plMap.size();i++){
                    if (plMap.get(i)){
                        CarManageBean cmb = mCarAdapter.mArrList.get(i);
                        cmb.setCarMoney(Integer.parseInt(cmb.getCarMoney())+Integer.parseInt(dialog_czMoney.getText().toString())+"");
                    }
                }

                dao.recharge(czMap);
                mCarAdapter.notifyDataSetChanged();
                myDialog.dismiss();




            }
        });

        dialog_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

    }

    public void toHistory(View view) {
        //Intent intent = new Intent(ManageMainActivity.this, Tab2Activity.class);
        Intent intent = new Intent(ManageMainActivity.this, CenterMainActivity.class);
        intent.putExtra("tag",true);
        startActivity(intent);
    }

    public void toMain(View view) {
        Intent intent = new Intent(ManageMainActivity.this, Activity_Main.class);
        startActivity(intent);
    }
}
