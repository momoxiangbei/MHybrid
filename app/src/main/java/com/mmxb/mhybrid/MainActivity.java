package com.mmxb.mhybrid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.mmxb.hybridmodule.bridge.JSBridge;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSBridge jsBridge = new JSBridge(new WebView(this));
        jsBridge.registerMethod(TestBridge.class.getSimpleName(), new TestBridge(jsBridge));
    }
}
