package cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import cn.com.edu.aib.trafficclient_2017gb.R;
import cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.bean.CarManageBean;
import cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.database.DBDao;

import static android.content.Context.MODE_PRIVATE;

public class CarManageAdapter extends BaseAdapter{

    Context mContext;
    public ArrayList<CarManageBean> mArrList;
    public HashMap<Integer,Boolean> mapChecked = new HashMap<>();


    public CarManageAdapter(Context context,ArrayList<CarManageBean> arrList) {
        this.mContext = context;
        this.mArrList = arrList;

        for(int i = 0;i<mArrList.size();i++){
            mapChecked.put(i,false);
        }
    }


    @Override
    public int getCount() {
        return mArrList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){
            convertView = View.inflate(mContext, R.layout.syc_manage_item,null);
            holder = new ViewHolder();
            holder.tv_id = convertView.findViewById(R.id.tv_id);
            holder.tv_carNum = convertView.findViewById(R.id.tv_carNum);
            holder.ll_item = convertView.findViewById(R.id.ll_item);
            holder.tv_carOwner = convertView.findViewById(R.id.tv_carOwner);
            holder.tv_carMoney = convertView.findViewById(R.id.tv_carMoney);
            holder.cz_checkbox = convertView.findViewById(R.id.cz_checkbox);
            holder.cz_btn = convertView.findViewById(R.id.cz_btn);
            holder.iv_carLogo = convertView.findViewById(R.id.iv_carLogo);

            convertView.setTag(holder);
        }else{
            holder = ((ViewHolder) convertView.getTag());
        }

        CarManageBean cmb = new CarManageBean();
        cmb = mArrList.get(position);

        holder.tv_id.setText(cmb.getId());
        holder.tv_carNum.setText(cmb.getCarNum());
        //车主 : 张三
        holder.tv_carOwner.setText("车主 : "+cmb.getCarOwner());
        holder.tv_carMoney.setText(cmb.getCarMoney());

        if (cmb.getCarType().equals("宝马")){
            holder.iv_carLogo.setImageResource(R.mipmap.baoma);
        }else if (cmb.getCarType().equals("奔驰")){
            holder.iv_carLogo.setImageResource(R.mipmap.benchi);
        }else if (cmb.getCarType().equals("现代")){
            holder.iv_carLogo.setImageResource(R.mipmap.xiandai);
        }else if (cmb.getCarType().equals("雪佛兰")){
            holder.iv_carLogo.setImageResource(R.mipmap.xuefulan);
        }

        //变色警告
        SharedPreferences sp = mContext.getSharedPreferences("syc",MODE_PRIVATE);
        String strWarn = sp.getString("warnNum","-1");
        //Toast.makeText(mContext,strWarn,Toast.LENGTH_SHORT).show();

        if (strWarn.equals("-1")){

        }else{
            int money = Integer.parseInt(cmb.getCarMoney());//
            int warnNum = Integer.parseInt(sp.getString("warnNum",money+""));
            //Toast.makeText(mContext,money+"---"+warnNum,Toast.LENGTH_SHORT).show();

            if (money<warnNum){

                holder.ll_item.setBackgroundColor(Color.parseColor("#FFFDCB01"));
            }else{
                holder.ll_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            }
        }



        /*if (holder.cz_checkbox.isChecked()){
            mapChecked.put(position,true);
        }else{
            mapChecked.put(position,false);
        }*/

        holder.cz_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mapChecked.put(position,isChecked);
            }
        });

        holder.cz_checkbox.setChecked(mapChecked.get(position));

        // 单项充值按钮
        final CarManageBean finalCmb = cmb;
        final CarManageBean finalCmb1 = cmb;
        holder.cz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog myDialog = new AlertDialog.Builder(mContext).create();
                myDialog.show();
                myDialog.getWindow().setContentView(R.layout.syc_manage_dialog);

                TextView dialog_carNum = myDialog.getWindow().findViewById(R.id.dialog_carNum);
                final EditText dialog_czMoney = myDialog.getWindow().findViewById(R.id.dialog_czMoney);
                Button dialog_btn1 = myDialog.getWindow().findViewById(R.id.dialog_btn1);
                Button dialog_btn2 = myDialog.getWindow().findViewById(R.id.dialog_btn2);

                //dialog_carNum.setText(mArrList.get(position).getCarNum());
                dialog_carNum.setText(finalCmb.getCarNum());

                dialog_czMoney.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String strMoney =dialog_czMoney.getText().toString();
                        int[] arrInt = {0,1,2,3,4,5,6,7,8,9};
                        if (strMoney.equals("")){

                        }else{
                            //money = Integer.parseInt(dialog_czMoney.getText().toString());
                            //strNum.matches("[0-9]{1,}");

                            if (strMoney.matches("[0-9]{1,}")){
                                if (Integer.parseInt(strMoney)<1||Integer.parseInt(strMoney)>999){
                                    Toast.makeText(mContext,"你输入的金额有误,请重新输入!",Toast.LENGTH_SHORT).show();
                                    dialog_czMoney.getText().clear();
                                }
                            }else{
                                Toast.makeText(mContext,"含有非法无效字符,请重新输入!",Toast.LENGTH_SHORT).show();
                                dialog_czMoney.getText().clear();
                            }
                        }
                    }
                });


                // 充值 取消 按钮
                dialog_btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBDao dao = new DBDao(mContext);
                        HashMap<String,String> czMap = new HashMap<>();
                        if (dialog_czMoney.getText().toString().equals("")){
                            Toast.makeText(mContext,"充值失败-dialog",Toast.LENGTH_SHORT).show();
                        }else{
                            String dialog_czMon = dialog_czMoney.getText().toString();
                            czMap.put(mArrList.get(position).getCarNum(),dialog_czMon);
                            dao.recharge(czMap);
                        }

                        mArrList.get(position).setCarMoney(Integer.parseInt(finalCmb1.getCarMoney())+Integer.parseInt(dialog_czMoney.getText().toString())+"");
                        notifyDataSetChanged();

                        myDialog.dismiss();


                    }
                });

                dialog_btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
            }
        });

        return convertView;
    }

    class ViewHolder{
        //String id, String carType, String carNum, String carOwner, String carMoney
        TextView tv_id;
        ImageView iv_carLogo;
        TextView tv_carNum;
        TextView tv_carOwner;
        TextView tv_carMoney;
        CheckBox cz_checkbox;
        Button cz_btn;
        LinearLayout ll_item;
    }

}
