package cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.com.edu.aib.trafficclient_2017gb.R;
import cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.bean.HistoryBean;

public class RcAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<HistoryBean> mArrHistory;



    public RcAdapter(Context context, ArrayList<HistoryBean> arrHistory) {
        mContext = context;
        mArrHistory = arrHistory;
    }

    @Override
    public int getCount() {
        return mArrHistory.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrHistory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = View.inflate(mContext, R.layout.syc_center_recharge_item,null);
            holder = new ViewHolder();
            holder.tv_date = convertView.findViewById(R.id.rec_date);
            holder.tv_week = convertView.findViewById(R.id.rec_Week);
            holder.tv_czPeo = convertView.findViewById(R.id.rec_czPeo);
            holder.tv_carNum = convertView.findViewById(R.id.rec_carNum);
            holder.tv_czMon = convertView.findViewById(R.id.rec_czMon);
            holder.tv_carMon = convertView.findViewById(R.id.rec_carMon);
            holder.tv_time = convertView.findViewById(R.id.rec_time);

            convertView.setTag(holder);

        }else{
            holder = ((ViewHolder) convertView.getTag());
        }

        HistoryBean hb = new HistoryBean();
        hb = mArrHistory.get(position);

        holder.tv_date.setText(hb.getH_date());
        holder.tv_week.setText(hb.getH_week());
        holder.tv_czPeo.setText("充值人 : "+hb.getH_czPeo());
        holder.tv_carNum.setText("车牌号 : "+hb.getH_carNum());
        holder.tv_czMon.setText("充值 : "+hb.getH_czMon());
        holder.tv_carMon.setText("余额 : "+hb.getH_carMon());
        holder.tv_time.setText(hb.getH_time());


        return convertView;
    }

    class ViewHolder{
        //String h_date, String h_week, String h_czPeo, String h_carNum, String h_czMon, String h_carMon, String h_time
        TextView tv_date;
        TextView tv_week;
        TextView tv_czPeo;
        TextView tv_carNum;
        TextView tv_czMon;
        TextView tv_carMon;
        TextView tv_time;

    }
}
