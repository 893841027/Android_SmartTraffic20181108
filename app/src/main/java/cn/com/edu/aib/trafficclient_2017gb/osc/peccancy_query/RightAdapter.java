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

public class RightAdapter extends BaseAdapter{

    Activity activity;
    List<RightBean> list;

    public RightAdapter(Activity activity, List list) {
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
        convertView = View.inflate(activity, R.layout.right_item,null);
        TextView date = convertView.findViewById(R.id.pecc_r_date);
        TextView content = convertView.findViewById(R.id.pecc_r_content);
        TextView fakuan = convertView.findViewById(R.id.pecc_r_fakuan);
        TextView koufen = convertView.findViewById(R.id.pecc_r_koufen);
        TextView pos = convertView.findViewById(R.id.pecc_r_position);

        RightBean item = list.get(position);
        date.setText(item.getDate().toString());
        content.setText(item.getContent().toString());
        fakuan.setText(item.getFukuan()+"");
        koufen.setText(item.getKoufen()+"");
        pos.setText(item.getPosition().toString());

        return convertView;
    }
}
