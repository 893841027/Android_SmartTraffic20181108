package cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment07;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.com.edu.aib.trafficclient_2017gb.R;

public class FragmentItem6 extends Fragment {

    private HorizontalBarChart barChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment07_item_6, container, false);
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

        barChart = view.findViewById(R.id.barchart);

        initBarChartData();

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis lyAxis = barChart.getAxisLeft();
        lyAxis.setDrawAxisLine(false);
        lyAxis.setStartAtZero(true);

        YAxis ryAxis = barChart.getAxisRight();
        ryAxis.setEnabled(false);

    }

    private void initBarChartData() {

        List<String> xValues = new ArrayList<>();
        List<BarEntry> yValues = new ArrayList<>();

        xValues.add("超速行驶");
        xValues.add("违规驶入导向车道");
        xValues.add("违规停车拒绝驶离");
        xValues.add("违反禁止标线指示");
        xValues.add("违反信号灯规定");
        xValues.add("机动车不走机动车道");
        xValues.add("不按规定系安全带");
        xValues.add("违反禁令标志指示");
        xValues.add("违规使用专用车道");
        xValues.add("机动车逆向行驶");

        List<Integer> colors = new ArrayList<>();
        for (int i = 0; i < xValues.size(); i++) {
            Integer t = new Random().nextInt(100);
            yValues.add(new BarEntry(t, i));

            Integer r = new Random().nextInt(255);
            Integer g = new Random().nextInt(255);
            Integer b = new Random().nextInt(255);

            colors.add(Color.rgb(r, g, b));
        }

        BarDataSet barDataSet = new BarDataSet(yValues, "违章车辆的违章次数占比分布图统计");

        barDataSet.setColors(colors);
        BarData barData = new BarData(xValues);

        barData.addDataSet(barDataSet);
        barChart.setData(barData);
    }


}
