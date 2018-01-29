package com.zjz.picture_net.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zjz.picture_net.R;
import com.zjz.picture_net.base.BaseRecyclerViewAdapter;
import com.zjz.picture_net.base.BaseSimpleRecyclerViewAdapter;
import com.zjz.picture_net.entity.ComicInfo;

import butterknife.BindView;

/**
 * Created by zjz on 2018/1/29.
 */

public class Adapter extends BaseSimpleRecyclerViewAdapter<ComicInfo>{
    @Override
    protected int setResId() {
        return R.layout.item_main_comic;
    }

    @Override
    protected BaseRvHolder createConcreteViewHolder(View v) {
       return new ComicInfoHolder(v);
    }

    class ComicInfoHolder extends BaseRvHolder {

        @BindView(R.id.sv_main_item)
        SimpleDraweeView mSvMainItem;
        @BindView(R.id.tv_main_item_title)
        TextView mTvMainItemTitle;


        @Override
        protected void bindView(ComicInfo comicInfo) {
            Log.e("haha", "1\n");
//            Uri uri =  Uri.parse(comicInfo.getImgUrl());
//            DraweeController controller = Fresco.newDraweeControllerBuilder()
//                    .setUri(uri)
//                    .setAutoPlayAnimations(true)
//                    .build();
//            mSvMainItem.setController(controller);
            mTvMainItemTitle.setText(comicInfo.getName());
            Log.e("haha", comicInfo.getName());
        }

        public ComicInfoHolder(View itemView) {
            super(itemView);
        }
    }
}
