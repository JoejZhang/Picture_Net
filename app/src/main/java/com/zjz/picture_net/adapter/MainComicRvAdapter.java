package com.zjz.picture_net.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zjz.picture_net.R;
import com.zjz.picture_net.base.BaseRecyclerViewAdapter;
import com.zjz.picture_net.constant.Constant;
import com.zjz.picture_net.entity.ComicInfo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zjz on 2018/1/28.
 */

public class MainComicRvAdapter extends BaseRecyclerViewAdapter<ComicInfo> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_comic, parent, false);
        return new ComicInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ComicInfoHolder) holder).bindView(mDataList.get(position));
    }

    private class ComicInfoHolder extends BaseRvHolder {
        @BindView(R.id.sv_main_item)
        SimpleDraweeView mSvMainItem;
        @BindView(R.id.tv_main_item_title)
        TextView mTvMainItemTitle;


        @Override
        protected void bindView(ComicInfo comicInfo) {

            Uri uri =  Uri.parse(comicInfo.getImgUrl());
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            mSvMainItem.setController(controller);
            mTvMainItemTitle.setText(comicInfo.getName());
        }

        public ComicInfoHolder(View itemView) {
            super(itemView);
        }


    }
}
