package com.zjz.picture_net.contract;

import com.zjz.picture_net.entity.ComicCatalogAndUrl;
import com.zjz.picture_net.entity.ComicDescription;
import com.zjz.picture_net.entity.ComicInfo;

import java.util.ArrayList;

/**
 * Created by zjz on 2018/1/29.
 */

public interface IComicCatalogContract {
    interface View{
        void  onShowComicDescriptionSucceed(ComicDescription comicDescription);
        void  onShowComicDescriptionFailed(String reason);
        void  onShowComicCatalogSucceed(ArrayList<ComicCatalogAndUrl> comicInfoArrayList);
        void  onShowComicCatalogFailed(String reason );
    }

    interface Model{
        void getComicInformation(final String url , IComicCatalogContract.OnModelResultCallBack onModelResultCallBack );

    }

    interface Presenter{
        void showComicInformation(String url);
    }

    interface OnModelResultCallBack{
        void succeedDescription(final ComicDescription comicDescription);
        void succeedCatalog(final ArrayList<ComicCatalogAndUrl> arrayList);
        void failedDescription(final String reason);
        void failedCatalog(final String reason);
    }
}
