package cn.com.edu.aib.trafficclient_2017gb.osc.peccancy_query;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.com.edu.aib.trafficclient_2017gb.R;

/**
 * Created by teacher on 2018/11/8.
 */

public class LeftAdapter extends BaseAdapter{

    Activity activity;
    List<LeftBean> list;

    public LeftAdapter(Activity activity, List list) {
        this.activity = activity;
        this.list = list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(activity, R.layout.left_item,null);
        TextView chepai = convertView.findViewById(R.id.pecc_l_carnum);
        TextView weigui = convertView.findViewById(R.id.pecc_l_weigui);
        TextView koufen = convertView.findViewById(R.id.pecc_l_koufen);
        TextView fakuan = convertView.findViewById(R.id.pecc_l_fakuan);
        LeftBean item = list.get(position);
        chepai.setText(item.getCarNum()+"");
        fakuan.setText(item.getFukuan()+"");
        koufen.setText(item.getKoufen()+"");
        weigui.setText(item.getWeigui()+"");
        return convertView;
    }
}
