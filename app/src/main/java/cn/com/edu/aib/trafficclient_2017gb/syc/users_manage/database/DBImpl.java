package cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.database;

import java.util.ArrayList;
import java.util.HashMap;

import cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.bean.CarBean;
import cn.com.edu.aib.trafficclient_2017gb.syc.personal_center.bean.HistoryBean;
import cn.com.edu.aib.trafficclient_2017gb.syc.users_manage.bean.CarManageBean;

public interface DBImpl {
    // 初始化数据库
    void initData();

    // 查询所有车辆信息 --- 返回ArrayList<CarManageBean>
    ArrayList<CarManageBean> queryAll();

    // 修改充值车辆信息 --- 返回boolean值,传参HashMap(车牌号,充值金额)
    boolean recharge(HashMap<String, String> map);

    // 查询历史充值记录 --- 返回ArrayList<CarManageBean>
    ArrayList<HistoryBean> queryHisstory();

    // 根据车主的姓名-查询车主的车辆
    ArrayList<CarBean> queryCars(String userName);

}
