package com.mmxb.hybridmodule.webview;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by xueying on 2018/5/9.
 */

public class MWebViewClient extends WebViewClient {

    private MWebView webView;

    public MWebViewClient(MWebView webView) {
        this.webView = webView;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        webView.getProgressBar().setAlpha(1.0f);
        webView.getProgressBar().setVisibility(View.VISIBLE);
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }
}
