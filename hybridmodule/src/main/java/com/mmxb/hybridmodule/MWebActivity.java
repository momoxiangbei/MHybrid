package com.mmxb.hybridmodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by xueying on 2018/5/9.
 */

public class MWebActivity extends AppCompatActivity {

    private MWebFragment webFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_layout);

        webFragment = new MWebFragment();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            webFragment.setArguments(bundle);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, webFragment)
                .commit();

    }

    @Override
    public void onBackPressed() {
        // 返回时，先在webView中返回上一页
        if (!webFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
