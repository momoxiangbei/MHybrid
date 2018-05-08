package com.mmxb.hybridmodule.bridge;

/**
 * 供js调用的方法的基类
 * <p>
 * Created by xueying on 2018/5/8.
 */

public abstract class BaseJsCallNative {

    protected JSBridge jsBridge;

    public BaseJsCallNative(JSBridge jsBridge) {
        this.jsBridge = jsBridge;
    }

    public abstract void nativeMethod(String callbackId, String jsonParams);

}
