package cn.com.edu.aib.trafficclient_2017gb.syc.bus_query.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.edu.aib.trafficclient_2017gb.R;

public class DialogItemView extends LinearLayout {

    public TextView mTv_id;
    public TextView mTv_busId;
    public TextView mTv_peoNun;

    public DialogItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View v = View.inflate(context, R.layout.syc_busquery_dialog_item,null);
        mTv_id = v.findViewById(R.id.tv_id);
        mTv_busId = v.findViewById(R.id.tv_busId);
        mTv_peoNun = v.findViewById(R.id.tv_pNum);
        addView(v);


    }

    public DialogItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DialogItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialogItemView(Context context) {
        this(context, null);
    }
}
