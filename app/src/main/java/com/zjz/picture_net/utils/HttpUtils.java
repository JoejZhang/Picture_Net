package com.zjz.picture_net.utils;

import com.zjz.picture_net.constant.Constant;

import java.util.ArrayList;

/**
 * Created by zjz on 2018/1/29.
 */

public class HttpUtils {
    private static ArrayList<String> sUserAgentList = new ArrayList<>() ;

    //获取不同用户代理伪装浏览器
    public static String getUserAgentString() {

        if (sUserAgentList.size() == 0) {
            sUserAgentList = new ArrayList<>();
            sUserAgentList.add(Constant.BROWSER_0);
            sUserAgentList.add(Constant.BROWSER_1);
            sUserAgentList.add(Constant.BROWSER_2);
            sUserAgentList.add(Constant.BROWSER_3);
            sUserAgentList.add(Constant.BROWSER_4);
            sUserAgentList.add(Constant.BROWSER_5);
            sUserAgentList.add(Constant.BROWSER_6);
            sUserAgentList.add(Constant.BROWSER_7);
        }
        int num = (int) (Math.random() * 7);//产生随机数
        return sUserAgentList.get(num);
    }
}
