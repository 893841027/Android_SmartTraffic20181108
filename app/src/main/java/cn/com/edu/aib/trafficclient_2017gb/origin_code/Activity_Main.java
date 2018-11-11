
package cn.com.edu.aib.trafficclient_2017gb.origin_code;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.edu.aib.trafficclient_2017gb.R;
import cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment06.Fragment06;
import cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment07.Fragment07;
import cn.com.edu.aib.trafficclient_2017gb.osc.peccancy_query.PeccancyQuery;
import cn.com.edu.aib.trafficclient_2017gb.osc.roadview.RoadFragment;
import cn.com.edu.aib.trafficclient_2017gb.osc.trafficlight.TrafficFragment;
import cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment06.Fragment06;
import cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment07.Fragment07;
import cn.com.edu.aib.trafficclient_2017gb.syc.bus_query.BusMainActivity;
import cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.CenterMainActivity;
import cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.ManageMainActivity;

/**
 * @author zhaowei
 */
public class Activity_Main extends FragmentActivity {
    private SlidingPaneLayout slidepanel;

    private Fragment fragment;

    private ListView listView;
    SimpleAdapter simpleAdapter;

    ArrayList<HashMap<String, Object>> actionItems;
    SimpleAdapter actionAdapter;

    public TextView tV_title;
    ImageView iVSliding;
    ImageView ivHome;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        slidepanel =  findViewById(R.id.slidingPL);

        listView =  findViewById(R.id.listView1);
        ivHome =  findViewById(R.id.imageView_home);
        ivHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setHome();
            }
        });

        iVSliding = findViewById(R.id.imageView_Sliding);
        tV_title =  findViewById(R.id.tv_title);
        tV_title.setText(getString(R.string.app_title));


        iVSliding.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (slidepanel.isOpen()) {
                    slidepanel.closePane();
                } else {
                    slidepanel.openPane();
                }
            }
        });
        final String[] actionTexts = new String[]{
                "账户管理",
                "公交查询",
                "红绿灯管理",
                "车辆违章",
                "路况查询",
                "生活助手",
                "数据分析",
                "个人中心",
                "创意",
                "用户退出",
        };
        int[] actionImages = new int[]{
                R.drawable.btn_l_star,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_download,
        };

        actionItems = new ArrayList<>();
        for (int i = 0; i < actionImages.length; ++i) {
            HashMap<String, Object> item1 = new HashMap<String, Object>();
            item1.put("action_icon", actionImages[i]);
            item1.put("action_name", actionTexts[i]);
            actionItems.add(item1);
        }
//        String userRole= App.appContext.getSharedPreferences("setting", Context.MODE_PRIVATE).getString("userRole", null);
//        Log.e("userRole", userRole);
//        if (TextUtils.equals("R01",userRole)){
//            actionItems.remove(2);
//        }
        actionAdapter = new SimpleAdapter(getApplicationContext(), actionItems, R.layout.left_list_fragment_item,
                new String[]{"action_icon", "action_name"},
                new int[]{R.id.recharge_method_icon, R.id.recharge_method_name});
        listView.setAdapter(actionAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Map map=actionItems.get(arg2);
                String str= (String) map.get("action_name");
                Log.e("str", str);
                tV_title.setText(str);
                if (str.equals("账户管理")) {

                    Intent intent = new Intent(Activity_Main.this, ManageMainActivity.class);
                    startActivity(intent);

                }else if (str.equals("公交查询")) {
                    tV_title.setText("公交查询");
                    getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new BusMainActivity()).commit();

/*
                    Intent intent = new Intent(Activity_Main.this, BusMainActivity.class);
                    startActivity(intent);*/

                }else if (str.equals("红绿灯管理")) {
                    tV_title.setText("红绿灯管理");
                    getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new TrafficFragment()).commit();

                }else if (str.equals("车辆违章")) {
                    tV_title.setText("车辆违章");
                    getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new PeccancyQuery()).commit();


                }else if (str.equals("路况查询")) {
                    tV_title.setText("路况查询");
                    getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new RoadFragment()).commit();

                } else if (str.equals("生活助手")) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.maincontent, new Fragment06())
                            .commit();

                    tV_title.setText(actionTexts[arg2]);

                } else if (str.equals("数据分析")) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.maincontent, new Fragment07())
                            .commit();

                    tV_title.setText(actionTexts[arg2]);

                }else if (str.equals("个人中心")) {
                    Intent intent = new Intent(Activity_Main.this, CenterMainActivity.class);
                    startActivity(intent);


                }else if (str.equals("创意")) {


                }else if (str.equals("用户退出")) {
                    exitAppDialog();

                } else {
                }
                slidepanel.closePane();
            }
        });

        fragmentManager = getFragmentManager();

        setHome();


    }

    public void setHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new FragmentHome()).commit();
        tV_title.setText(R.string.app_name);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> items = new ArrayList<>();

        int[] listImg = new int[]{R.drawable.icon_trafic, R.drawable.icon_busstop, R.drawable.icon_lamp, R.drawable.icon_parking, R.drawable.icon_light, R.drawable.icon_etc, R.drawable.icon_speed};
        String[] listName = new String[]{"城市交通", "公交站点", "城市环境", "找车位", "红绿灯管理", "ETC管理", "高速车速监控"};
        for (int i = 0; i < listImg.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("itemImage", listImg[i]);
            item.put("itemName", listName[i]);
            items.add(item);
        }
        return items;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 按下键盘上返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            exitAppDialog();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    public void exitAppDialog() {
        new AlertDialog.Builder(this)
                // .setIcon(android.R.drawable.ic_menu_info_detailsp)
                .setTitle("提示").setMessage("你确定要退出吗").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener()

        {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        }).show();

    }


}
