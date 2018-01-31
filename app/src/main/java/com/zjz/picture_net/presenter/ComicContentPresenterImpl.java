package com.zjz.picture_net.presenter;

import android.app.Activity;
import android.util.Log;

import com.zjz.picture_net.contract.IComicContentContract;
import com.zjz.picture_net.eventbus.ContentListEvent;
import com.zjz.picture_net.eventbus.ContentReasonEvent;
import com.zjz.picture_net.model.ComicContentModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by zjz on 2018/1/29.
 */

public class ComicContentPresenterImpl implements IComicContentContract.Presenter {
    private IComicContentContract.Model mModel;
    private IComicContentContract.View mView;

    private static volatile ComicContentPresenterImpl instance;

    public static ComicContentPresenterImpl getInstance(IComicContentContract.View view) {

        if (instance == null) {
            synchronized (ComicContentPresenterImpl.class) {
                if (instance == null) {
                    instance = new ComicContentPresenterImpl(view);
                }
            }
        }
        instance.setView(view);
        return instance;
    }

    private ComicContentPresenterImpl(IComicContentContract.View view) {
        mView = view;
        mModel = new ComicContentModelImpl();
        EventBus.getDefault().register(this);
    }

    public void setView(IComicContentContract.View view) {
        mView = view;
    }

    @Override
    public void showComicList(String url) {
        mModel.getComic(url, new IComicContentContract.OnModelResultCallBack() {
            @Override
            public void succeed(final ArrayList<String> arrayList) {

            }

            @Override
            public void failed(final String reason) {

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showComicSucceed(ContentListEvent contentListEvent) {
        Log.e("haha","xix"+contentListEvent.getStringArrayList().size());
        for(int i = 0;i<contentListEvent.getStringArrayList().size();i++){
            Log.e("haha","xix"+contentListEvent.getStringArrayList().get(i));
        }
        mView.onShowComicSucceed(contentListEvent.getStringArrayList());
        Log.e("haha", "调用了");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showComicFailed(ContentReasonEvent contentReasonEvent) {
        mView.onShowComicFailed(contentReasonEvent.getReason());
        Log.e("haha", "失败调用了");
    }
}
