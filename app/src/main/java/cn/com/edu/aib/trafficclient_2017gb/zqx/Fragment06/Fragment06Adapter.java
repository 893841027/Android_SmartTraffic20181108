package cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment06;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author：  HyZhan
 * created： 2018/11/8 14:57
 * desc：    TODO
 */

public class Fragment06Adapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public Fragment06Adapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
