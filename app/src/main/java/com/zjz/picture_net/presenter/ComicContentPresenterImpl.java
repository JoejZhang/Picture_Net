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
    /**
     *  单例模式，虽然持有了Activity对象，但是在Activity的onDestroy方法被调用的时候，
     *  会调用uninstallView方法取消对Activity对象的引用，以支持Activity对象可以被GC回收，防止Activity内存泄漏
     */

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
    public void uninstallView(){
        mView = null;
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

    @Override
    public void stopThread() {
        mModel.stopThread();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showComicSucceed(ContentListEvent contentListEvent) {
        mView.onShowComicSucceed(contentListEvent.getStringArrayList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showComicFailed(ContentReasonEvent contentReasonEvent) {
        mView.onShowComicFailed(contentReasonEvent.getReason());
    }
}
