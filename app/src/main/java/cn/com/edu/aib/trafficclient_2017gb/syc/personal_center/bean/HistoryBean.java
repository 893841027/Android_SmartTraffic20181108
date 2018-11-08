package cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.bean;

public class HistoryBean {
    String h_date;
    String h_week;
    String h_czPeo;
    String h_carNum;
    String h_czMon;
    String h_carMon;
    String h_time;

    public HistoryBean() {
    }

    public HistoryBean(String h_date, String h_week, String h_czPeo, String h_carNum, String h_czMon, String h_carMon, String h_time) {
        this.h_date = h_date;
        this.h_week = h_week;
        this.h_czPeo = h_czPeo;
        this.h_carNum = h_carNum;
        this.h_czMon = h_czMon;
        this.h_carMon = h_carMon;
        this.h_time = h_time;
    }

    public String getH_date() {
        return h_date;
    }

    public void setH_date(String h_date) {
        this.h_date = h_date;
    }

    public String getH_week() {
        return h_week;
    }

    public void setH_week(String h_week) {
        this.h_week = h_week;
    }

    public String getH_czPeo() {
        return h_czPeo;
    }

    public void setH_czPeo(String h_czPeo) {
        this.h_czPeo = h_czPeo;
    }

    public String getH_carNum() {
        return h_carNum;
    }

    public void setH_carNum(String h_carNum) {
        this.h_carNum = h_carNum;
    }

    public String getH_czMon() {
        return h_czMon;
    }

    public void setH_czMon(String h_czMon) {
        this.h_czMon = h_czMon;
    }

    public String getH_carMon() {
        return h_carMon;
    }

    public void setH_carMon(String h_carMon) {
        this.h_carMon = h_carMon;
    }

    public String getH_time() {
        return h_time;
    }

    public void setH_time(String h_time) {
        this.h_time = h_time;
    }

    @Override
    public String toString() {
        return "HistoryBean{" +
                "h_date='" + h_date + '\'' +
                ", h_week='" + h_week + '\'' +
                ", h_czPeo='" + h_czPeo + '\'' +
                ", h_carNum='" + h_carNum + '\'' +
                ", h_czMon='" + h_czMon + '\'' +
                ", h_carMon='" + h_carMon + '\'' +
                ", h_time='" + h_time + '\'' +
                '}';
    }
}


