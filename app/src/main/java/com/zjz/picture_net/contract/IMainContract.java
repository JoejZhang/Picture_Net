package com.zjz.picture_net.contract;

import com.zjz.picture_net.entity.ComicInfo;

import java.util.ArrayList;

/**
 * Created by zjz on 2018/1/29.
 */

public interface IMainContract {
    interface View{
       void  onShowComicListSucceed(ArrayList<ComicInfo> comicInfoArrayList);
       void  onShowComicListFailed(String reason );
    }

    interface Model{
        void getComicList(final String url , OnModelResultCallBack onModelResultCallBack );

    }

    interface Presenter{
        void showComicList(String url);
    }

    interface OnModelResultCallBack{
        void succeed(final ArrayList<ComicInfo> arrayList);
        void failed(final String reason);
    }

}
