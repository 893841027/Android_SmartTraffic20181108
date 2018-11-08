package cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment07;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.com.edu.aib.trafficclient_2017gb.R;

public class FragmentItem1 extends Fragment {

    private PieChart pieChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment07_item_1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {
        View view = getView();
        if (view == null)
            return;

        pieChart = view.findViewById(R.id.piechart);

        initPieChartData();
    }

    private void initPieChartData() {

        List<String> xValues = new ArrayList<>();
        List<Entry> yVals = new ArrayList<>();

        xValues.add("无违章");
        xValues.add("有违章");

        yVals.add(new Entry(28.6f, 0));
        yVals.add(new Entry(71.3f, 0));

        PieDataSet pieDataSet = new PieDataSet(yVals, "有无违章");

        int[] colors = new int[]{Color.BLUE, Color.RED};
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(xValues, pieDataSet);
        pieChart.setData(pieData);
    }


}
