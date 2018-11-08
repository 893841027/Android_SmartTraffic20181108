package cn.com.edu.aib.trafficclient_2017gb.syc.personal_center;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cn.com.edu.aib.trafficclient_2017gb.R;
import cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.adapter.RcAdapter;
import cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.bean.HistoryBean;
import cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.database.DBDao;

public class Tab2Activity extends Activity {
    private ImageView r_headImg;
    private TextView r_name;
    private TextView r_total;
    private ListView r_listview;
    private ArrayList<HistoryBean> mArrHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syc_center_tab2);

         initView();
         initData();

        RcAdapter rcAdapter = new RcAdapter(this,mArrHistory);
        r_listview.setAdapter(rcAdapter);

        int rechargeTotal = 0;
        for(int i = 0;i<mArrHistory.size();i++){
            rechargeTotal+=Integer.parseInt(mArrHistory.get(i).getH_czMon());
        }

        DecimalFormat df = new DecimalFormat("###,###.00");
        String strTotal = df.format(rechargeTotal);

        r_total.setText(strTotal);
    }

    private void initData() {
        DBDao dao = new DBDao(this);
        mArrHistory = dao.queryHisstory();

    }

    private void initView() {
        r_headImg = ((ImageView) findViewById(R.id.r_headImg));
        r_name = ((TextView) findViewById(R.id.r_name));
        r_total = ((TextView) findViewById(R.id.r_total));

        r_listview = ((ListView) findViewById(R.id.r_listview));


    }

}
