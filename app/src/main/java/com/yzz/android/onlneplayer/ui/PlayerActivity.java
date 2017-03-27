package com.yzz.android.onlneplayer.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.yzz.android.onlneplayer.R;

/**
 * @name OnLnePlayer
 * @class name：com.yzz.android.onlneplayer
 * @anthor yzz
 * @Email:yzzandroid@163.com
 * @time 2017/3/26 0026 下午 6:52
 */
public class PlayerActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.player);
    }
}

