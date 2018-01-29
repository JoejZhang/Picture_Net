package com.zjz.picture_net.presenter;

import android.app.Activity;

import com.zjz.picture_net.contract.IMainContract;
import com.zjz.picture_net.entity.ComicInfo;
import com.zjz.picture_net.model.MainModelImpl;

import java.util.ArrayList;

/**
 * Created by zjz on 2018/1/29.
 */

public class MainPresenterImpl implements IMainContract.Presenter {
    private IMainContract.View mView;
    private IMainContract.Model mModel;
    private Activity mActivity;
    public MainPresenterImpl(IMainContract.View view ,Activity activity) {
        mView = view;
        mModel = new MainModelImpl();
        mActivity = activity;
    }

    @Override
    public void showComicList(String url) {
        mModel.getComicList(url, new IMainContract.OnModelResultCallBack() {
            @Override
            public void succeed(final  ArrayList<ComicInfo> arrayList) {
             mActivity.runOnUiThread(new Runnable() {//切换回主线程
                 @Override
                 public void run() {
                     mView.onShowComicListSucceed(arrayList);
                 }
             });

            }

            @Override
            public void failed(final String reason) {
                mActivity.runOnUiThread(new Runnable() {//切换回主线程
                    @Override
                    public void run() {
                       mView.onShowComicListFailed(reason);
                    }
                });

            }
        });
    }
}
