package com.zjz.picture_net.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.zjz.picture_net.R;
import com.zjz.picture_net.base.BaseRecyclerViewAdapter;
import com.zjz.picture_net.entity.ComicInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;

/**
 * Created by zjz on 2018/1/28.
 */

public class MainComicRvAdapter extends BaseRecyclerViewAdapter<ComicInfo> {

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


        @Override
        protected void bindView(ComicInfo comicInfo) {
            Log.e("haha", "1\n");

            try {
                int end = comicInfo.getImgUrl().lastIndexOf("/");
                String head = comicInfo.getImgUrl().substring(0, end + 1);
                String back = URLEncoder.encode(comicInfo.getImgUrl().substring(end + 1), "utf-8");
                mSvMainItem.setImageURI(head + back);
                mTvMainItemTitle.setText(comicInfo.getName());
                Log.e("haha", head +"\n"+back);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
//            DraweeController controller = Fresco.newDraweeControllerBuilder()
//                    .setUri(uri)
//                    .setAutoPlayAnimations(true)
//                    .build();
//            mSvMainItem.setController(controller);
//            mTvMainItemTitle.setText(comicInfo.getName());

//            DraweeController controller = Fresco.newDraweeControllerBuilder()
//                    .setLowResImageRequest(ImageRequest.fromUri(lowResUri))
//                    .setImageRequest(ImageRequest.fromUri(highResUri))
//                    .setOldController(draweeView.getController())
//                    .build();
//            draweeView.setController(controller);


        }

        public ComicInfoHolder(View itemView) {
            super(itemView);
        }


    }
}
