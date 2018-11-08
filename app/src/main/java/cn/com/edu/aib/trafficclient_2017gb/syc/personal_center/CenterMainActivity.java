package cn.com.edu.aib.trafficclient_2017gb.syc.personal_center;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import cn.com.edu.aib.trafficclient_2017gb.R;

public class CenterMainActivity extends TabActivity {

    private TabHost mTabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syc_center_main);

        mTabhost = this.getTabHost();
        mTabhost.addTab(mTabhost.newTabSpec("1").setIndicator("个人信息").setContent(new Intent(CenterMainActivity.this,Tab1Activity.class)));
        mTabhost.addTab(mTabhost.newTabSpec("2").setIndicator("充值记录").setContent(new Intent(CenterMainActivity.this,Tab2Activity.class)));
        mTabhost.addTab(mTabhost.newTabSpec("3").setIndicator("阈值设置").setContent(new Intent(CenterMainActivity.this,Tab3Activity.class)));

        if(getIntent().getBooleanExtra("tag",false)) {
            mTabhost.setCurrentTabByTag("2");
        }
    }

}
