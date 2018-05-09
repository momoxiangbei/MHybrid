package com.mmxb.hybridmodule.webview;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by xueying on 2018/5/9.
 */

public class MWebChromeClient extends WebChromeClient {
    private MWebView webView;

    public MWebChromeClient(MWebView webView) {
        this.webView = webView;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        webView.setTitle(title);
        super.onReceivedTitle(view, title);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        webView.onProgressChanged(newProgress);
        super.onProgressChanged(view, newProgress);
    }
}
