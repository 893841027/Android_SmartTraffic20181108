package cn.com.edu.aib.trafficclient_2017gb.osc.roadview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.com.edu.aib.trafficclient_2017gb.R;
import cn.com.edu.aib.trafficclient_2017gb.osc.utils.RandomUtil;

public class RoadFragment extends Fragment {

    RandomUtil ran = new RandomUtil();
    private RoadView roadView;
    private ImageView refresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.osc_activity_road_fragment,container,false);
        roadView = view.findViewById(R.id.road_view);
        refresh = view.findViewById(R.id.road_refresh);

        refreshData();
        refreshRightInfo();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshRightInfo();
            }
        });

        Thread ad1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                       while (true) {
                           getActivity().runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   refreshData();
                               }
                           });
                           Thread.sleep(3000);
                       }
                }catch (Exception e){

                }
            }
        });


        ad1.start();

        return view;
    }

    void refreshRightInfo(){
        roadView.date = new SimpleDateFormat("yyyy-M-d").format(new Date());
        roadView.week = new SimpleDateFormat("E").format(new Date());
        roadView.wendu = "温度：" + ran.random(40) + "'C";
        roadView.shidu = "相对湿度：" + ran.random(40) + "%";
        roadView.pm25 = "PM2.5：" + ran.random(500) + "ug/m^3";
        roadView.invalidate();
    }

    void refreshData(){
        HashMap<Integer,RoadData> map = new HashMap<>();

            RoadData data = new RoadData();
            data.setRoadName("学院路");
            data.setLevel(ran.random(5));
            data.setStartX(new int[]{180});
            data.setStartY(new int[]{77});
            data.setEndX(new int[]{232});
            data.setEndY(new int[]{312});
            map.put(1,data);

            data = new RoadData();
            data.setRoadName("联想路");
            data.setLevel(ran.random(5));
            data.setStartX(new int[]{340});
            data.setStartY(new int[]{77});
            data.setEndX(new int[]{392});
            data.setEndY(new int[]{312});
            map.put(2,data);

            data = new RoadData();
            data.setRoadName("医院路");
            data.setLevel(ran.random(5));
            data.setStartX(new int[]{340});
            data.setStartY(new int[]{374});
            data.setEndX(new int[]{392});
            data.setEndY(new int[]{608});
            map.put(3,data);

            data = new RoadData();
            data.setRoadName("幸福路");
            data.setLevel(ran.random(5));
            data.setStartX(new int[]{180});
            data.setStartY(new int[]{374});
            data.setEndX(new int[]{232});
            data.setEndY(new int[]{608});
            map.put(4,data);

            data = new RoadData();
            data.setRoadName("环城高速");
            data.setLevel(ran.random(5));
            data.setStartX(new int[]{657});
            data.setStartY(new int[]{22});
            data.setEndX(new int[]{708});
            data.setEndY(new int[]{663});
            map.put(6,data);

            data = new RoadData();
            data.setRoadName("停车场");
            data.setLevel(ran.random(5));
            data.setStartX(new int[]{476});
            data.setStartY(new int[]{255});
            data.setEndX(new int[]{575});
            data.setEndY(new int[]{424});
            map.put(7,data);

            data = new RoadData();
            data.setRoadName("环城快速路");
            data.setLevel(ran.random(5));
            data.setStartX(new int[]{25,25,72});
            data.setStartY(new int[]{22,22,608});
            data.setEndX(new int[]{675,72,663});
            data.setEndY(new int[]{77,663,657});
            map.put(5,data);

            roadView.map = map;
            roadView.invalidate();
        }


}
