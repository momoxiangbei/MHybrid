package com.mmxb.mhybrid;

import android.util.Log;

import com.mmxb.hybridmodule.bridge.BaseJsCallNative;
import com.mmxb.hybridmodule.bridge.JSBridge;

/**
 * Created by xueying on 2018/5/8.
 */

public class TestBridge extends BaseJsCallNative {

    public TestBridge(JSBridge jsBridge) {
        super(jsBridge);
    }

    @Override
    public void nativeMethod(String callbackId, String jsonParams) {
        Log.d("test", "nativeMethod called");
        jsBridge.nativeCallJS(callbackId, "将结果回调给js");
    }

}
