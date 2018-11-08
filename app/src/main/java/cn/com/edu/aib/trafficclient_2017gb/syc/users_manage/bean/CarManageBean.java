package cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.bean;

/**
 * 因为涉及到SQLite数据库,所以这里CarManageBean的设计要根据第一、八道题共同决定
 */
public class CarManageBean {
    String id;      //充值id
    String carType; //充值的车型
    String carNum;  //充值的车牌号
    String carOwner;//车主
    String carMoney;//余额

    String czTF;    //是否充过值
    String czMoney; //充值的金额
    String czPeople;//充值人
    String czDate;  //充值日期
    String czWeek;  //充值周几
    String czTime;  //充值具体时间

    public CarManageBean() {
    }

    public CarManageBean(String id, String carType, String carNum, String carOwner, String carMoney, String czTF, String czMoney, String czPeople, String czDate, String czWeek, String czTime) {
        this.id = id;
        this.carType = carType;
        this.carNum = carNum;
        this.carOwner = carOwner;
        this.carMoney = carMoney;
        this.czTF = czTF;
        this.czMoney = czMoney;
        this.czPeople = czPeople;
        this.czDate = czDate;
        this.czWeek = czWeek;
        this.czTime = czTime;
    }

    public CarManageBean(String id, String carType, String carNum, String carOwner, String carMoney) {
        this.id = id;
        this.carType = carType;
        this.carNum = carNum;
        this.carOwner = carOwner;
        this.carMoney = carMoney;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }

    public String getCarMoney() {
        return carMoney;
    }

    public void setCarMoney(String carMoney) {
        this.carMoney = carMoney;
    }

    public String getCzTF() {
        return czTF;
    }

    public void setCzTF(String czTF) {
        this.czTF = czTF;
    }

    public String getCzMoney() {
        return czMoney;
    }

    public void setCzMoney(String czMoney) {
        this.czMoney = czMoney;
    }

    public String getCzPeople() {
        return czPeople;
    }

    public void setCzPeople(String czPeople) {
        this.czPeople = czPeople;
    }

    public String getCzDate() {
        return czDate;
    }

    public void setCzDate(String czDate) {
        this.czDate = czDate;
    }

    public String getCzWeek() {
        return czWeek;
    }

    public void setCzWeek(String czWeek) {
        this.czWeek = czWeek;
    }

    public String getCzTime() {
        return czTime;
    }

    public void setCzTime(String czTime) {
        this.czTime = czTime;
    }

    @Override
    public String toString() {
        return "CarManageBean{" +
                "id='" + id + '\'' +
                ", carType='" + carType + '\'' +
                ", carNum='" + carNum + '\'' +
                ", carOwner='" + carOwner + '\'' +
                ", carMoney='" + carMoney + '\'' +
                ", czTF='" + czTF + '\'' +
                ", czMoney='" + czMoney + '\'' +
                ", czPeople='" + czPeople + '\'' +
                ", czDate='" + czDate + '\'' +
                ", czWeek='" + czWeek + '\'' +
                ", czTime='" + czTime + '\'' +
                '}';
    }
}
