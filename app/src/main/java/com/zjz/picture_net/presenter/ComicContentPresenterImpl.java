package com.zjz.picture_net.presenter;

import android.app.Activity;

import com.zjz.picture_net.contract.IComicContentContract;
import com.zjz.picture_net.model.ComicContentModelImpl;

import java.util.ArrayList;

/**
 * Created by zjz on 2018/1/29.
 */

public class ComicContentPresenterImpl implements IComicContentContract.Presenter {
    private IComicContentContract.Model mModel;
    private IComicContentContract.View mView;
    private Activity mActivity;

    public ComicContentPresenterImpl(IComicContentContract.View view, Activity activity) {
        mView = view;
        mActivity = activity;
        mModel = new ComicContentModelImpl();
    }

    @Override
    public void showComicList(String url) {
        mModel.getComic(url, new IComicContentContract.OnModelResultCallBack() {
            @Override
            public void succeed(final ArrayList<String> arrayList) {
               mActivity.runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       mView.onShowComicSucceed(arrayList);
                   }
               });
            }

            @Override
            public void failed(final String reason) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.onShowComicFailed(reason);
                    }
                });
            }
        });
    }
}
