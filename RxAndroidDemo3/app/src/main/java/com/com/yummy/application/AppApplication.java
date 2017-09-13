package com.com.yummy.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.okhttp.OkHttpClient;


/**
 * Created by Administrator on 2016-6-27.
 */
public class AppApplication extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        OkHttpClient okHttpClient=new OkHttpClient();
        ImagePipelineConfig config= OkHttpImagePipelineConfigFactory.newBuilder((Context)AppApplication.this, okHttpClient).build();
        Fresco.initialize(this,config);

    }
}
