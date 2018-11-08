package cn.com.edu.aib.trafficclient_2017gb.syc.bus_query.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.com.edu.aib.trafficclient_2017gb.R;
import cn.com.edu.aib.trafficclient_2017gb.syc.bus_query.bean.ExpChildBean;
import cn.com.edu.aib.trafficclient_2017gb.syc.bus_query.bean.ExpGroupBean;

public class ExpAdapter extends BaseExpandableListAdapter{

    Context mContext;
    public ArrayList<ExpGroupBean> mGroupArr;

    public ExpAdapter(Context context, ArrayList<ExpGroupBean> groupArr) {
        mContext = context;
        mGroupArr = groupArr;
    }

    @Override
    public int getGroupCount() {
        return mGroupArr.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroupArr.get(groupPosition).getChildArr().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupArr.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroupArr.get(groupPosition).getChildArr().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupViewHolder gHolder;
        if (convertView==null){
            convertView = View.inflate(mContext, R.layout.syc_busquery_g_item,null);
            gHolder = new GroupViewHolder();
            gHolder.g_name = convertView.findViewById(R.id.g_name);
            convertView.setTag(gHolder);
        }else{
            gHolder = ((GroupViewHolder) convertView.getTag());
        }

        ExpGroupBean egb = new ExpGroupBean();
        egb = mGroupArr.get(groupPosition);

        gHolder.g_name.setText(egb.getStationName());


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder cHolder;
        if (convertView==null){
            convertView = View.inflate(mContext, R.layout.syc_busquery_c_item,null);
            cHolder = new ChildViewHolder();
            cHolder.c_carNumPNum = convertView.findViewById(R.id.c_carNumPNum);
            cHolder.c_arriveTime = convertView.findViewById(R.id.c_arriveTime);
            cHolder.c_distance = convertView.findViewById(R.id.c_distance);

            convertView.setTag(cHolder);

        }else{
            cHolder = ((ChildViewHolder) convertView.getTag());
        }

        ExpChildBean ecb = new ExpChildBean();
        ecb = mGroupArr.get(groupPosition).getChildArr().get(childPosition);

        //"1号   ( 101人 )"
        cHolder.c_carNumPNum.setText(ecb.getCarNum()+"   ( "+ecb.getPeople()+"人 )");
        //"5分钟到达"
        cHolder.c_arriveTime.setText(ecb.getTimeArrive()+"分钟到达");
        //"距离站台100米"
        cHolder.c_distance.setText("距离站台"+ecb.getDistance()+"米");


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder{
        TextView g_name;
    }

    class ChildViewHolder{
        TextView c_carNumPNum;
        TextView c_arriveTime;
        TextView c_distance;
    }
}
