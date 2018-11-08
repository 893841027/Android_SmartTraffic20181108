package cn.com.edu.aib.trafficclient_2017gb.syc.bus_query.bean;

import java.util.ArrayList;

public class ExpGroupBean {
    String stationName;
    ArrayList<ExpChildBean> childArr;

    public ExpGroupBean() {
    }

    public ExpGroupBean(String stationName, ArrayList<ExpChildBean> childArr) {
        this.stationName = stationName;
        this.childArr = childArr;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public ArrayList<ExpChildBean> getChildArr() {
        return childArr;
    }

    public void setChildArr(ArrayList<ExpChildBean> childArr) {
        this.childArr = childArr;
    }

    @Override
    public String toString() {
        return "ExpGroupBean{" +
                "stationName='" + stationName + '\'' +
                ", childArr=" + childArr +
                '}';
    }
}
