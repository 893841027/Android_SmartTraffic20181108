package cn.com.edu.aib.trafficclient_2017gb.osc.peccancy_query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.com.edu.aib.trafficclient_2017gb.R;
import cn.com.edu.aib.trafficclient_2017gb.osc.utils.SQLHelper;

/**
 * Created by teacher on 2018/11/8.
 */

public class PeccancyQuery extends Fragment{

    List<LeftBean> leftlist = new ArrayList<>();
    List<RightBean> rightlist = new ArrayList<>();
    private ListView leftview;
    private ListView rightview;
    SQLHelper sqlHelper;
    private RightAdapter rightAdapter;
    private LeftAdapter leftAdapter;
    private RelativeLayout queryUI;
    private Button queryok;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.osc_peccancy_activity,container,false);
        ImageView add = view.findViewById(R.id.pecc_add);
        queryUI = view.findViewById(R.id.pecc_query);
        queryok = view.findViewById(R.id.pecc_queryok);
        final EditText query_carnum = view.findViewById(R.id.pecc_query_carnum);


        sqlHelper = new SQLHelper(getActivity());

        leftview = view.findViewById(R.id.pecc_leftlist);
        rightview = view.findViewById(R.id.pecc_rightlist);

        leftAdapter = new LeftAdapter(getActivity(),leftlist);
        leftview.setAdapter(leftAdapter);

        rightAdapter = new RightAdapter(getActivity(),rightlist);
        rightview.setAdapter(rightAdapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryUI.setVisibility(View.VISIBLE);
            }
        });

        queryok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // addquery(query_carnum.getText().toString());
                queryUI.setVisibility(View.GONE);
            }
        });

        return view;
    }

    void addquery(String in_catnum){
        boolean exsiti = false;
        if(leftlist.size()>0){
            leftlist.clear();
        }
        final SQLiteDatabase db = sqlHelper.getReadableDatabase();
        final Cursor cursor = db.rawQuery("select carnum,count(fukuan) as fukuan,count(koufen) as foufen from peccancy group by carnum",null);
        cursor.moveToFirst();

        for(int i = 0 ; i < cursor.getCount() ; i++){
            LeftBean leftBean = new LeftBean();
            String carnum = cursor.getString(cursor.getColumnIndex("carnum"));
            if(carnum.equals(in_catnum)){
                exsiti = true;
            }
            leftBean.setCarNum(carnum);
            leftBean.setFukuan(cursor.getInt(cursor.getColumnIndex("fukuan")));
            leftBean.setKoufen(cursor.getInt(cursor.getColumnIndex("koufen")));
            leftlist.add(leftBean);
        }

        //存在车牌
        if(exsiti){
            queryUI.setVisibility(View.GONE);
        }else{
            Toast.makeText(getActivity(), "没有该车辆", Toast.LENGTH_SHORT).show();
            return;
        }

        leftAdapter.notifyDataSetChanged();


        leftview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String carnum = leftAdapter.list.get(position).getCarNum();
                Cursor cursor1 = db.rawQuery("select * from peccancy where carnum like \'?\'",new String[]{carnum});
                if(rightlist.size()>0){
                    rightlist.clear();
                }
                if(cursor1.getCount()>0){
                    cursor1.moveToFirst();
                    for(int i = 0 ; i < cursor1.getCount() ; i++){
                        RightBean rightBean = new RightBean();
                        rightBean.setContent(cursor1.getString(cursor.getColumnIndex("content")));
                        rightBean.setDate(cursor1.getString(cursor.getColumnIndex("date")));
                        rightBean.setFukuan(cursor1.getInt(cursor.getColumnIndex("fukuan")));
                        rightBean.setKoufen(cursor1.getInt(cursor.getColumnIndex("koufen")));
                        rightBean.setHandle("未处理");
                        rightBean.setPosition(cursor1.getString(cursor.getColumnIndex("position")));
                        rightlist.add(rightBean);
                    }
                }else{
                    //todo
                }
                rightAdapter.notifyDataSetChanged();
            }
        });

    }

}
