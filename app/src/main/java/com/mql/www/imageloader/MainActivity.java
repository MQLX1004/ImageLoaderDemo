package com.mql.www.imageloader;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageView;
    private Button mButton;
    private Button mSButton;
    private Button mSizeButton;
    private Button mDisplayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView)findViewById(R.id.ImageLoader);
        mButton = (Button)findViewById(R.id.loader);
        mSButton = (Button)findViewById(R.id.SimpleLoader);
        mSizeButton = (Button)findViewById(R.id.SizeLoader);
        mDisplayButton = (Button)findViewById(R.id.DisplayLoader);

        //加载一个Imageloader的默认配置
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        //使用配置初始化ImageLoader
        ImageLoader.getInstance().init(configuration);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loading();
            }
        });

        mSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleLoading();
            }
        });

        mSizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SizeLoading();
            }
        });

        mDisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayLoading();
            }
        });
    }

    private void Loading(){
        String imageURL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504711276988&di=1cd26ef686a989b53febfe943d6deec3&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F3%2F57e257c14a711.jpg";

        ImageLoader.getInstance().loadImage(imageURL, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                mImageView.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }
    private void SimpleLoading(){
        String imageURL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504712211036&di=9c47bc652409208f95e1c9b0e1379a89&imgtype=0&src=http%3A%2F%2Fimg2.3lian.com%2F2014%2Ff5%2F54%2Fd%2F73.jpg";

        ImageLoader.getInstance().loadImage(imageURL, new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                mImageView.setImageBitmap(loadedImage);
            }
        });
    }
    private void SizeLoading(){
        String imageURL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504715823343&di=77ad29f9ac32b27550512022d343edfa&imgtype=0&src=http%3A%2F%2Fbizhi.zhuoku.com%2F2013%2F05%2F23%2Fxiaoqingxin%2Fxiaoqingxin021.jpg";

        ImageSize size = new ImageSize(100,100);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().loadImage(imageURL, size, options, new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                mImageView.setImageBitmap(loadedImage);
            }
        });
    }
    private void DisplayLoading(){
        String imageURL = "http://img1.3lian.com/2015/a1/107/d/280.jpg";

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(imageURL, mImageView, options);
    }
}
