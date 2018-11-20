package cn.com.edu.aib.trafficclient_2017gb.osc.trafficlight;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cn.com.edu.aib.trafficclient_2017gb.R;
import cn.com.edu.aib.trafficclient_2017gb.osc.utils.RandomUtil;

public class TrafficFragment extends Fragment {

    List<TrafficData> list = new ArrayList<>();
    List<String> sp_list = new ArrayList<>();
    private ListView listView;
    private Spinner spinner;
    private TrafficAdapter adapter;
    String select = "路口升序";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.osc_activity_traffic,container,false);
        listView = view.findViewById(R.id.tra_listview);
        spinner = view.findViewById(R.id.tra_spinner);
        Button query = view.findViewById(R.id.tra_query);
        Button muiltRecharge = view.findViewById(R.id.tra_muiltRecharge);

        loadData();
        load_spList();

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(select.equals(sp_list.get(0))){
                    RoadAsc();

                }else if(select.equals(sp_list.get(1))){
                    RoadDesc();

                }else if(select.equals(sp_list.get(2))){
                    RedAsc();

                }else if(select.equals(sp_list.get(3))){
                    RedDesc();

                }else if(select.equals(sp_list.get(4))){
                    YellowAsc();

                }else if(select.equals(sp_list.get(5))){
                    YellowDesc();

                }else if(select.equals(sp_list.get(6))){
                    GreenAsc();

                }else if(select.equals(sp_list.get(7))){
                    GreenDesc();

                }
            }
        });

        muiltRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View diaview = View.inflate(getActivity(),R.layout.osc_traset_dialog,null);
                final EditText diared = diaview.findViewById(R.id.tradia_red);
                final EditText diayellow = diaview.findViewById(R.id.tradia_yellow);
                final EditText diagreen = diaview.findViewById(R.id.tradia_green);
                TextView diaok = diaview.findViewById(R.id.tradia_ok);
                TextView diacancel = diaview.findViewById(R.id.tradia_cancel);
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                dialog.show();
                dialog.setContentView(diaview);

                diared.setText("10");
                diayellow.setText("10");
                diagreen.setText("10");

                diaok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        HashMap<Integer,Boolean> map = adapter.checked;
                        Set<Integer> key = map.keySet();
                        for(Integer k : key){
                            if(map.get(k)){
                                TrafficData data = (TrafficData) adapter.list.get(k);
                                data.setRed(Integer.parseInt(diared.getText().toString()));
                                data.setYellow(Integer.parseInt(diayellow.getText().toString()));
                                data.setGreen(Integer.parseInt(diagreen.getText().toString()));
                                adapter.notifyDataSetChanged();

                            }
                        }
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT).show();
                    }
                });

                diacancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        });

        return view;
    }


    void loadData(){
        TrafficData data;
        RandomUtil ran = new RandomUtil();
        for(int i = 0 ; i < 5 ; i++){
            data = new TrafficData();
            data.setRoad(i+1);
            data.setGreen(ran.random(50));
            data.setRed(ran.random(100));
            data.setYellow(ran.random(10));
            list.add(data);
        }

        adapter = new TrafficAdapter(getActivity(),list);
        listView.setAdapter(adapter);
    }


    void load_spList(){
        sp_list.add("路口升序");
        sp_list.add("路口降序");
        sp_list.add("红灯升序");
        sp_list.add("红灯降序");
        sp_list.add("黄灯升序");
        sp_list.add("黄灯降序");
        sp_list.add("绿灯升序");
        sp_list.add("绿灯降序");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,sp_list);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select = sp_list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void RedAsc(){
        Collections.sort(adapter.list, new Comparator<TrafficData>() {
            @Override
            public int compare(TrafficData o1, TrafficData o2) {
                return o1.getRed() - o2.getRed();
            }
        });
        adapter.notifyDataSetChanged();
    }

    void RedDesc(){
        Collections.sort(adapter.list, new Comparator<TrafficData>() {
            @Override
            public int compare(TrafficData o1, TrafficData o2) {
                return o2.getRed() - o1.getRed();
            }
        });
        adapter.notifyDataSetChanged();
    }

    void YellowAsc(){
        Collections.sort(adapter.list, new Comparator<TrafficData>() {
            @Override
            public int compare(TrafficData o1, TrafficData o2) {
                return o1.getYellow() - o2.getYellow();
            }
        });
        adapter.notifyDataSetChanged();
    }

    void YellowDesc(){
        Collections.sort(adapter.list, new Comparator<TrafficData>() {
            @Override
            public int compare(TrafficData o1, TrafficData o2) {
                return o2.getYellow() - o1.getYellow();
            }
        });
        adapter.notifyDataSetChanged();
    }

    void GreenAsc(){
        Collections.sort(adapter.list, new Comparator<TrafficData>() {
            @Override
            public int compare(TrafficData o1, TrafficData o2) {
                return o1.getGreen() - o2.getGreen();
            }
        });
        adapter.notifyDataSetChanged();
    }

    void GreenDesc(){
        Collections.sort(adapter.list, new Comparator<TrafficData>() {
            @Override
            public int compare(TrafficData o1, TrafficData o2) {
                return o2.getGreen() - o1.getGreen();
            }
        });
        adapter.notifyDataSetChanged();
    }

    void RoadAsc(){
        Collections.sort(adapter.list, new Comparator<TrafficData>() {
            @Override
            public int compare(TrafficData o1, TrafficData o2) {
                return o1.getRoad() - o2.getRoad();
            }
        });
        adapter.notifyDataSetChanged();
    }

    void RoadDesc(){
        Collections.sort(adapter.list, new Comparator<TrafficData>() {
            @Override
            public int compare(TrafficData o1, TrafficData o2) {
                return o2.getRoad() - o1.getRoad();
            }
        });
        adapter.notifyDataSetChanged();
    }

}
