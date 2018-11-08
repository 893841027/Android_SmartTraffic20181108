package cn.com.edu.aib.trafficclient_2017gb.osc.trafficlight;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import cn.com.edu.aib.trafficclient_2017gb.R;

/**
 * Created by teacher on 2018/11/8.
 */

public class TrafficAdapter extends BaseAdapter{

    Activity activity;
    List list;
    HashMap<Integer,Boolean> checked = new HashMap<>();

    public TrafficAdapter(Activity activity, List list) {
        this.activity = activity;
        this.list = list;
        for(int i = 0 ; i < list.size() ; i++){
            checked.put(i,false);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(activity, R.layout.osc_traffic_item,null);
        TextView road = convertView.findViewById(R.id.tra_road);
        TextView red = convertView.findViewById(R.id.tra_red);
        TextView yellow = convertView.findViewById(R.id.tra_yellow);
        TextView green = convertView.findViewById(R.id.tra_green);
        CheckBox check = convertView.findViewById(R.id.tra_check);
        Button set = convertView.findViewById(R.id.tra_set);
        final TrafficData data = (TrafficData) list.get(position);

        road.setText(data.getRoad()+"");
        red.setText(data.getRed()+"");
        green.setText(data.getGreen()+"");
        yellow.setText(data.getYellow()+"");

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checked.put(position,isChecked);
            }
        });

        check.setChecked(checked.get(position));

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View diaview = View.inflate(activity,R.layout.osc_traset_dialog,null);
                final EditText diared = diaview.findViewById(R.id.tradia_red);
                final EditText diayellow = diaview.findViewById(R.id.tradia_yellow);
                final EditText diagreen = diaview.findViewById(R.id.tradia_green);
                TextView diaok = diaview.findViewById(R.id.tradia_ok);
                TextView diacancel = diaview.findViewById(R.id.tradia_cancel);
                final AlertDialog dialog = new AlertDialog.Builder(activity).create();
                dialog.show();
                dialog.setContentView(diaview);

                diared.setText(data.red+"");
                diayellow.setText(data.yellow+"");
                diagreen.setText(data.green+"");

                diaok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        data.setRed(Integer.parseInt(diared.getText().toString()));
                        data.setYellow(Integer.parseInt(diayellow.getText().toString()));
                        data.setGreen(Integer.parseInt(diagreen.getText().toString()));
                        dialog.dismiss();
                        notifyDataSetChanged();
                        Toast.makeText(activity, "设置成功", Toast.LENGTH_SHORT).show();
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

        return convertView;
    }
}
