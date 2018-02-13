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
     *  单例模式，虽然持有了Activity对象，但是在创建下一个Activity对象时
     *  会替换掉上一个Activity对象，上一个Activity对象就可以被回收掉。
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
