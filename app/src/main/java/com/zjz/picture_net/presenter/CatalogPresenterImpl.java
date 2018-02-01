package com.zjz.picture_net.presenter;

import android.app.Activity;

import com.zjz.picture_net.contract.IComicCatalogContract;
import com.zjz.picture_net.entity.ComicCatalogAndUrl;
import com.zjz.picture_net.entity.ComicDescription;
import com.zjz.picture_net.model.CatalogModelImpl;
import com.zjz.picture_net.ui.ComicCatalogActivity;

import java.util.ArrayList;

/**
 * Created by zjz on 2018/1/29.
 */

public class CatalogPresenterImpl implements IComicCatalogContract.Presenter {
    private IComicCatalogContract.Model mModel;
    private IComicCatalogContract.View mView;
    private Activity mActivity;

    public CatalogPresenterImpl(IComicCatalogContract.View view, Activity activity) {
        mView = view;
        mActivity = activity;
        mModel = new CatalogModelImpl();
    }

    @Override
    public void showComicInformation(String url) {
        mModel.getComicInformation(url, new IComicCatalogContract.OnModelResultCallBack() {

            @Override
            public void succeedDescription(final ComicDescription comicDescription) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.onShowComicDescriptionSucceed(comicDescription);
                    }
                });
            }

            @Override
            public void succeedCatalog(final ArrayList<ComicCatalogAndUrl> arrayList) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.onShowComicCatalogSucceed(arrayList);
                    }
                });
            }

            @Override
            public void failedDescription(final String reason) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.onShowComicDescriptionFailed(reason);
                    }
                });
            }

            @Override
            public void failedCatalog(final String reason) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       mView.onShowComicCatalogFailed(reason);
                    }
                });
            }
        });


    }
}
