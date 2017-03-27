package com.yzz.android.onlneplayer.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yzz.android.onlneplayer.R;
import com.yzz.android.onlneplayer.net.BasCallBackImpl;
import com.yzz.android.onlneplayer.net.OkCallback;
import com.yzz.android.onlneplayer.net.OkUtils;
import com.yzz.android.onlneplayer.reflect.YzzAnn;
import com.yzz.android.onlneplayer.reflect.YzzAnnotation;

import java.io.InputStream;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @YzzAnnotation(id = R.id.img)
    private ImageView mImageView;
    @YzzAnnotation(id = R.id.btn,click = true)
    private Button button;
    @YzzAnnotation(id = R.id.player,click = true)
    private Button player;
    public static final String URL = "http://img31.mtime.cn/pi/2015/02/07/111037.12992551_1000X1000.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YzzAnn<MainActivity> yzz = new YzzAnn<>();
        yzz.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                downloadImag();
                break;
            case R.id.player:
                //进入播放界面
                startActivity(new Intent(this,PlayerActivity.class));
                break;
        }
    }

    public void downloadImag(){
        OkUtils.getInstance().doGet(URL, OkCallback.getInstance().callback(new BasCallBackImpl() {
            @Override
            public void success(Response response) {
                final InputStream in = response.body().byteStream();
                final Bitmap map= BitmapFactory.decodeStream(in);
                mImageView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (in == null) return;
                        mImageView.setImageBitmap(map);
                    }
                });
            }
        }));
    }
}
