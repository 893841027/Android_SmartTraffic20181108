package cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.bean;

public class CarBean {
    String carType;
    String carNum;

    public CarBean() {
    }

    public CarBean(String carType, String carNum) {
        this.carType = carType;
        this.carNum = carNum;
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

    @Override
    public String toString() {
        return "CarBean{" +
                "carType='" + carType + '\'' +
                ", carNum='" + carNum + '\'' +
                '}';
    }
}
