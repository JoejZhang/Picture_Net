package com.zjz.picture_net.adapter;

import android.view.View;
import android.widget.TextView;

import com.zjz.picture_net.R;
import com.zjz.picture_net.base.BaseSimpleRecyclerViewAdapter;
import com.zjz.picture_net.entity.ComicCatalogAndUrl;

import butterknife.BindView;

/**
 * Created by zjz on 2018/1/29.
 */

public class ComicCatalogAdapter extends BaseSimpleRecyclerViewAdapter<ComicCatalogAndUrl> {


    @Override
    protected int setResId() {
        return R.layout.item_catalog_rv;
    }

    @Override
    protected BaseRvHolder createConcreteViewHolder(View v) {
        return new ComicCatalogHolder(v);
    }

    class ComicCatalogHolder extends BaseRvHolder {
        @BindView(R.id.tv_item_catalog)
        TextView mTvItemCatalog;
        @Override
        protected void bindView(ComicCatalogAndUrl comicCatalogAndUrl) {
            mTvItemCatalog.setText(comicCatalogAndUrl.getTitle());
        }

         ComicCatalogHolder(View itemView) {
            super(itemView);
        }
    }
}
