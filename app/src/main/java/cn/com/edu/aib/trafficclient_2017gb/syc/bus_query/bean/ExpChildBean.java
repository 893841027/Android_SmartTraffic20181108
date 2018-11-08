package cn.com.edu.aib.trafficclient_2017gb.syc.bus_query.bean;

public class ExpChildBean {
    String carNum;
    int people;
    int timeArrive;
    int distance;

    public ExpChildBean() {
    }

    public ExpChildBean(String carNum, int people, int timeArrive, int distance) {
        this.carNum = carNum;
        this.people = people;
        this.timeArrive = timeArrive;
        this.distance = distance;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getTimeArrive() {
        return timeArrive;
    }

    public void setTimeArrive(int timeArrive) {
        this.timeArrive = timeArrive;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "ExpChildBean{" +
                "carNum='" + carNum + '\'' +
                ", people=" + people +
                ", timeArrive=" + timeArrive +
                ", distance=" + distance +
                '}';
    }
}
