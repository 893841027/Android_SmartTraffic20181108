package cn.com.edu.aib.trafficclient_2017gb.osc.roadview;

/**
 * Created by teacher on 2018/11/8.
 */

public class RoadData {
    String roadName;
    int level;
    int startX[];
    int startY[];
    int endX[];
    int endY[];

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int[] getStartX() {
        return startX;
    }

    public void setStartX(int[] startX) {
        this.startX = startX;
    }

    public int[] getStartY() {
        return startY;
    }

    public void setStartY(int[] startY) {
        this.startY = startY;
    }

    public int[] getEndX() {
        return endX;
    }

    public void setEndX(int[] endX) {
        this.endX = endX;
    }

    public int[] getEndY() {
        return endY;
    }

    public void setEndY(int[] endY) {
        this.endY = endY;
    }
}
