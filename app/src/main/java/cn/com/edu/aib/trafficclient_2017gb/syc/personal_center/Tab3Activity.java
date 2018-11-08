package cn.com.edu.aib.trafficclient_2017gb.syc.personal_center;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.edu.aib.trafficclient_2017gb.R;

public class Tab3Activity extends Activity {
    private TextView alarm_now;
    private EditText alarm_set;
    private SharedPreferences mSp;
    private SharedPreferences.Editor mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syc_center_tab3);

        initView();

        mSp = getSharedPreferences("syc",MODE_PRIVATE);
        mEdit = mSp.edit();

        if (mSp.getString("warnNum","-1").equals("-1")){
            alarm_now.setText("当前 1-4 号小车账户余额告警阈值未设置！");
        }else{
            alarm_now.setText("当前 1-4 号小车账户余额告警阈值为  "+mSp.getString("warnNum","-1")+"  元");
        }


    }

    private void initView() {
        alarm_now = ((TextView) findViewById(R.id.alarm_now));
        alarm_set = ((EditText) findViewById(R.id.alarm_set));
    }

    public void setAlarmNum(View view) {


        //if(!alarm_set.getText().toString().equals("")||alarm_set.getText().toString().length()>0){
        if(!alarm_set.getText().toString().equals("")){
            //不为空
            mEdit.putString("warnNum",alarm_set.getText().toString().trim());
            mEdit.commit();
            //当前 1-4 号小车账户余额告警阈值为  50  元
            alarm_now.setText("当前 1-4 号小车账户余额告警阈值为  "+mSp.getString("warnNum","-1")+"  元");
            Toast.makeText(getApplicationContext(),"设置成功!",Toast.LENGTH_SHORT).show();
        }else{
            //为空
            Toast.makeText(getApplicationContext(),"设置失败!",Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(getApplicationContext(),alarm_now.getText(),Toast.LENGTH_SHORT).show();
    }

}
