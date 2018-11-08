package cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment07;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.com.edu.aib.trafficclient_2017gb.R;
import cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment06.Fragment06Adapter;

public class Fragment07 extends Fragment {

    ImageView circle1;
    ImageView circle2;
    ImageView circle3;
    ImageView circle4 ;
    ImageView circle5;
    ImageView circle6;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment07, container, false);
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

        circle1 = view.findViewById(R.id.iv_circle1);
        circle2 = view.findViewById(R.id.iv_circle2);
        circle3 = view.findViewById(R.id.iv_circle3);
        circle4 = view.findViewById(R.id.iv_circle4);
        circle5 = view.findViewById(R.id.iv_circle5);
        circle6 = view.findViewById(R.id.iv_circle6);

        List<Fragment> fragments = initFragments();

        ViewPager viewPager = view.findViewById(R.id.viewpager);
        Fragment06Adapter adapter = new Fragment06Adapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                switchPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    public void switchPoint(int position){

        circle1.setImageResource(R.drawable.circle_white);
        circle2.setImageResource(R.drawable.circle_white);
        circle3.setImageResource(R.drawable.circle_white);
        circle4.setImageResource(R.drawable.circle_white);
        circle5.setImageResource(R.drawable.circle_white);
        circle6.setImageResource(R.drawable.circle_white);

        switch (position){
            case 0:
                circle1.setImageResource(R.drawable.circle_blue);
                break;
            case 1:
                circle2.setImageResource(R.drawable.circle_blue);
                break;
            case 2:
                circle3.setImageResource(R.drawable.circle_blue);
                break;
            case 3:
                circle4.setImageResource(R.drawable.circle_blue);
                break;
            case 4:
                circle5.setImageResource(R.drawable.circle_blue);
                break;
            case 5:
                circle6.setImageResource(R.drawable.circle_blue);
                break;
        }
    }


    public List<Fragment> initFragments() {
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new FragmentItem1());
        fragments.add(new FragmentItem2());
        fragments.add(new FragmentItem3());
        fragments.add(new FragmentItem4());
        fragments.add(new FragmentItem5());
        fragments.add(new FragmentItem6());

        return fragments;
    }
}
