package com.zjz.picture_net.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import okhttp3.OkHttpClient;

/**
 * Created by zjz on 2018/1/28.
 */

public class MyAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ImagePipelineConfig config =  OkHttpImagePipelineConfigFactory.newBuilder(this,new OkHttpClient()).build();
        Fresco.initialize(this,config);
     //   Fresco.initialize(this);
    }
}
