package cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.edu.aib.trafficclient_2017gb.R;

public class CarItemView extends LinearLayout{

    public ImageView mV_carLogo;
    public TextView mV_carNum;

    public CarItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        View v = View.inflate(context, R.layout.syc_center_view_item,null);
        mV_carLogo = v.findViewById(R.id.v_carLogo);
        mV_carNum = v.findViewById(R.id.v_carNum);

        addView(v);


    }

    public CarItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CarItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarItemView(Context context) {
        this(context, null);
    }
}
