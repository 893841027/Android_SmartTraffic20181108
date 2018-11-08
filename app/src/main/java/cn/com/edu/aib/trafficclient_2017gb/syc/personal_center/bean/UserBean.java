package cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.bean;

import java.util.ArrayList;

public class UserBean {
    String name;
    String sex;
    String phoneNum;
    String idCartNum;
    String regTime;
    ArrayList<CarBean> carArrList;

    public UserBean() {
    }

    public UserBean(String name, String sex, String phoneNum, String idCartNum, String regTime, ArrayList<CarBean> carArrList) {
        this.name = name;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.idCartNum = idCartNum;
        this.regTime = regTime;
        this.carArrList = carArrList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getIdCartNum() {
        return idCartNum;
    }

    public void setIdCartNum(String idCartNum) {
        this.idCartNum = idCartNum;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public ArrayList<CarBean> getCarArrList() {
        return carArrList;
    }

    public void setCarArrList(ArrayList<CarBean> carArrList) {
        this.carArrList = carArrList;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", idCartNum='" + idCartNum + '\'' +
                ", regTime='" + regTime + '\'' +
                ", carArrList=" + carArrList +
                '}';
    }
}
