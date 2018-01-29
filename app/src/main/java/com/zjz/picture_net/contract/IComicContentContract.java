package com.zjz.picture_net.contract;

import com.zjz.picture_net.entity.ComicInfo;

import java.util.ArrayList;

/**
 * Created by zjz on 2018/1/29.
 */

public interface IComicContentContract {
    interface View{
        void  onShowComicSucceed(ArrayList<String> arrayList);
        void  onShowComicFailed(String reason );
    }

    interface Model{
        void getComic(final String url , IComicContentContract.OnModelResultCallBack onModelResultCallBack );

    }

    interface Presenter{
        void showComicList(String url);
    }

    interface OnModelResultCallBack{
        void succeed(final ArrayList<String> arrayList);
        void failed(final String reason);
    }
}
