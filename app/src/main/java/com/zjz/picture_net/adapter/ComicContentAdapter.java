package com.zjz.picture_net.adapter;

import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zjz.picture_net.R;
import com.zjz.picture_net.base.BaseSimpleRecyclerViewAdapter;

import butterknife.BindView;

/**
 * Created by zjz on 2018/1/29.
 */

public class ComicContentAdapter extends BaseSimpleRecyclerViewAdapter<String> {


    @Override
    protected int setResId() {
        return R.layout.item_content_rv;
    }

    @Override
    protected BaseRvHolder createConcreteViewHolder(View v) {
        return new ComicContentHolder(v);
    }

    class ComicContentHolder extends BaseRvHolder {

        @BindView(R.id.sv_content_item)
        SimpleDraweeView mSvContentItem;

        @Override
        protected void bindView(String url) {
            mSvContentItem.setImageURI(url);
        }

        public ComicContentHolder(View itemView) {
            super(itemView);
        }
    }
}
