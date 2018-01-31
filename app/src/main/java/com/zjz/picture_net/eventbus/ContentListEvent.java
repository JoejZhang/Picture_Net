package com.zjz.picture_net.eventbus;

import java.util.ArrayList;

/**
 * Created by zjz on 2018/1/31.
 */

public class ContentListEvent {
    private ArrayList<String> mStringArrayList;

    public ContentListEvent(ArrayList<String> stringArrayList) {
        mStringArrayList = stringArrayList;
    }

    public ArrayList<String> getStringArrayList() {
        return mStringArrayList;
    }
}
