package cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment06;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.com.edu.aib.trafficclient_2017gb.R;

/**
 * 生活助手
 */
public class Fragment06 extends Fragment {

    private LineChart lineChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment06, container, false);
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

        ImageView imageView = view.findViewById(R.id.iv_refresh);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLineChartData();
                lineChart.invalidate();
            }
        });

        List<Fragment> fragments = initFragments();

        ViewPager viewPager = view.findViewById(R.id.viewpager);
        Fragment06Adapter adapter = new Fragment06Adapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);


        lineChart = view.findViewById(R.id.lineChart);
        initLineChartData();

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(false);
        xAxis.setDrawGridLines(false);

        YAxis lyAxis = lineChart.getAxisLeft();
        lyAxis.setDrawLabels(false);
        lyAxis.setDrawAxisLine(false);

        YAxis ryAxis = lineChart.getAxisRight();
        ryAxis.setEnabled(false);

    }

    private void initLineChartData() {

        List<String> xValues = new ArrayList<>();
        List<Entry> yValues = new ArrayList<>();
        List<Entry> yValues2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            float y = new Random().nextInt() * 20 + 1;
            float y2 = new Random().nextInt() * 20 + 1;
            yValues.add(new Entry(y, i));
            yValues2.add(new Entry(y2, i));
            xValues.add(i + "");
        }

        LineDataSet lineDataSet = new LineDataSet(yValues, "");
        lineDataSet.setColor(Color.BLUE);

        LineDataSet lineDataSet2 = new LineDataSet(yValues2, "");
        lineDataSet2.setColor(Color.RED);

        List<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        lineDataSets.add(lineDataSet2);

        LineData lineData = new LineData(xValues, lineDataSets);
        lineChart.setData(lineData);
    }


    public List<Fragment> initFragments() {
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new AirFragment());
        fragments.add(new WenDuFragment());
        fragments.add(new WenDuFragment());
        fragments.add(new WenDuFragment());

        return fragments;
    }

//    public void refresh(View view){
//        Toast.makeText(getActivity(), "111", Toast.LENGTH_SHORT).show();
//    }
}
