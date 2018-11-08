package cn.com.edu.aib.trafficclient_2017gb.syc.bus_query;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.com.edu.aib.trafficclient_2017gb.R;
import cn.com.edu.aib.trafficclient_2017gb.syc.bus_query.adapter.ExpAdapter;
import cn.com.edu.aib.trafficclient_2017gb.syc.bus_query.bean.ExpChildBean;
import cn.com.edu.aib.trafficclient_2017gb.syc.bus_query.bean.ExpGroupBean;
import cn.com.edu.aib.trafficclient_2017gb.syc.bus_query.view.DialogItemView;

public class BusMainActivity extends AppCompatActivity {

    private TextView tv_total;
    private Button btn_detail;
    private ExpandableListView expListView;
    private int mCarPropleNum;
    private int mDistance;
    private int mArriveTime;
    private ExpChildBean mEcb;
    private ExpGroupBean mEgb;
    private ArrayList<ExpGroupBean> mGroupList;
    private ArrayList<ExpChildBean> mChildList;
    private int mTotal;
    private LinearLayout mDialog_list;
    private View mView;
    private TextView mTv_sum;
    private AlertDialog mMyDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.syc_busquery_main);


        initView();
        initData();



        final ExpAdapter expAdapter = new ExpAdapter(this,mGroupList);
        expListView.setAdapter(expAdapter);

        final Thread thread = new Thread(){
            @Override
            public void run() {
                while(true){
                try {
                    Thread.sleep(3000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initData();
                            //"当前承载能力 : 1211人"
                            tv_total.setText("当前承载能力 : "+mTotal+"人");
                            expAdapter.mGroupArr = mGroupList;
                            expAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }}
        };

        thread.start();




    }

    private void initData() {
        //String carNum, int people, int timeArrive, int distance
        //ExpChildBean ecb = new ExpChildBean("1号",);

        //传入适配器的list
        mGroupList = new ArrayList<>();
        mChildList = new ArrayList<>();

        //ExpChildBean
        mCarPropleNum = (int)(Math.random()*100+1);
        mDistance = (int)(Math.random()*2000+1);
        mArriveTime = (int)(mDistance /167);
        mEcb = new ExpChildBean("1号",mCarPropleNum,mArriveTime,mDistance);
        mChildList.add(mEcb);

        mCarPropleNum = (int)(Math.random()*100+1);
        mDistance = (int)(Math.random()*2000+1);
        mArriveTime = (int)(mDistance /167);
        mEcb = new ExpChildBean("2号",mCarPropleNum,mArriveTime,mDistance);
        mChildList.add(mEcb);

        //ExpGroupBean
        mEgb = new ExpGroupBean();
        mEgb.setStationName("中医院站");
        mEgb.setChildArr(mChildList);
        mGroupList.add(mEgb);

        //-------------------------------
        mChildList = new ArrayList<>();

        mCarPropleNum = (int)(Math.random()*100+1);
        mDistance = (int)(Math.random()*2000+1);
        mArriveTime = (int)(mDistance /167);
        mEcb = new ExpChildBean("1号",mCarPropleNum,mArriveTime,mDistance);
        mChildList.add(mEcb);

        mCarPropleNum = (int)(Math.random()*100+1);
        mDistance = (int)(Math.random()*2000+1);
        mArriveTime = (int)(mDistance /167);
        mEcb = new ExpChildBean("2号",mCarPropleNum,mArriveTime,mDistance);
        mChildList.add(mEcb);

        //ExpGroupBean
        mEgb = new ExpGroupBean();
        mEgb.setStationName("联想大厦站");
        mEgb.setChildArr(mChildList);
        mGroupList.add(mEgb);

        mTotal = 0;
        for(int i = 0;i<mGroupList.size();i++){
            for(int j = 0;j<mGroupList.get(i).getChildArr().size();j++){
                mTotal+=mGroupList.get(i).getChildArr().get(j).getPeople();
            }
        }


    }

    private void initView() {
        tv_total = ((TextView) findViewById(R.id.tv_total));
        btn_detail = ((Button) findViewById(R.id.btn_detail));
        expListView = ((ExpandableListView) findViewById(R.id.expListView));

        // 这种写法是错误的,因为findViewById是在syc_busquery_main里面找,而不是syc_busquery_dialog
        //mDialog_list = ((LinearLayout) findViewById(R.id.dialog_list));

        mView = View.inflate(this, R.layout.syc_busquery_dialog,null);
        mDialog_list = mView.findViewById(R.id.dialog_list);
        mTv_sum = mView.findViewById(R.id.sum);
    }

    public void detail(View view) {
        mMyDialog = new AlertDialog.Builder(this).create();

        int xuhao = 0;
        String busID = "";
        int peoNum=0;
        for(int i = 0;i<mGroupList.size();i++){
            for(int j = 0;j<mGroupList.get(i).getChildArr().size();j++){
                xuhao++;
                busID = mGroupList.get(i).getChildArr().get(j).getCarNum();
                peoNum = mGroupList.get(i).getChildArr().get(j).getPeople();

                DialogItemView div = new DialogItemView(BusMainActivity.this);
                div.mTv_busId.setText(busID);
                div.mTv_id.setText(xuhao+"");
                div.mTv_peoNun.setText(peoNum+"");

                mDialog_list.addView(div);
            }
        }
        mTv_sum.setText(mTotal+"");
        mMyDialog.show();
        mMyDialog.getWindow().setContentView(mView);

        mView = View.inflate(this, R.layout.syc_busquery_dialog,null);
        mDialog_list = mView.findViewById(R.id.dialog_list);
    }

    public void returnBusquery(View view) {
        mMyDialog.dismiss();
    }
}
