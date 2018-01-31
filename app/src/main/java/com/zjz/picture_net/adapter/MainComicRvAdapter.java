package com.zjz.picture_net.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zjz.picture_net.R;
import com.zjz.picture_net.base.BaseRecyclerViewAdapter;
import com.zjz.picture_net.entity.ComicInfo;

import butterknife.BindView;

/**
 * Created by zjz on 2018/1/28.
 */

public class MainComicRvAdapter extends BaseRecyclerViewAdapter<ComicInfo> {


    private int mImgWidth;//动态设置图片宽高

    public MainComicRvAdapter(int imgWidth) {
        mImgWidth = imgWidth;
    }

    @Override
    public ComicInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_comic, parent, false);
        return new ComicInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ComicInfoHolder) holder).bindView(mDataList.get(position));
    }

    class ComicInfoHolder extends BaseRvHolder {

        @BindView(R.id.sv_main_item)
        SimpleDraweeView mSvMainItem;
        @BindView(R.id.tv_main_item_title)
        TextView mTvMainItemTitle;


        protected void bindView(ComicInfo comicInfo) {

            mSvMainItem.setImageURI(comicInfo.getImgUrl());
            mTvMainItemTitle.setText(comicInfo.getName());
        }

        private ComicInfoHolder(View itemView) {
            super(itemView);

            ViewGroup.LayoutParams params = mSvMainItem.getLayoutParams();
            params.width = (int) (mImgWidth * 7 /25);
            params.height = (int) (mImgWidth * 28 / 75);
            mSvMainItem.setLayoutParams(params);
        }


    }
}
