package com.mmxb.hybridmodule.bridge;

import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.Map;

/**
 * 注入JSBridge到WebView中
 * <p>
 * Created by xueying on 2018/5/8.
 */

public class JSBridge {

    private final String HYBRID_BRIDGE_NAME = "momoBridge";  // 供js调用的JSBridge名称
    private final String NATIVE_ERROR = "NATIVE_ERROR";

    private Map<String, BaseJsCallNative> nativeMethodMap;
    private WebView webView;


    public JSBridge(WebView webView) {
        this.webView = webView;
        nativeMethodMap = new HashMap<>();
        webView.addJavascriptInterface(this, HYBRID_BRIDGE_NAME);
    }

    // note: 每一个供js调用的方法 必须调用此方法进行注册
    public void registerMethod(String methodName, BaseJsCallNative jsCallNative) {
        nativeMethodMap.put(methodName, jsCallNative);
    }

    @JavascriptInterface
    public void JSCallNative(final String methodName, final String callbackId, final String jsonParams) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                if (nativeMethodMap != null && nativeMethodMap.get(methodName) != null) {
                    // 调用native方法
                    nativeMethodMap.get(methodName).nativeMethod(callbackId, jsonParams);
                } else {
                    // 错误回调
                    nativeCallJS(callbackId, NATIVE_ERROR);
                }
            }
        });
    }

    public void nativeCallJS(String callbackId, final String jsonParams) {
        if (webView == null) {
            return;
        }
        webView.post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                    webView.evaluateJavascript(jsonParams, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {

                        }
                    });
                } else {
                    webView.loadUrl("javascript:" + jsonParams);
                }
            }
        });
    }

}
