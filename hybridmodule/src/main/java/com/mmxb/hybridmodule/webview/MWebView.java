package com.mmxb.hybridmodule.webview;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mmxb.hybridmodule.R;
import com.mmxb.hybridmodule.utils.AnimationUtil;

/**
 * 自定的WebView
 * <p>
 * Created by xueying on 2018/5/8.
 */

public class MWebView extends FrameLayout {

    private WebView webView;
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView refreshView;
    private ImageView cancelView;

    private Context context;

    public MWebView(@NonNull Context context) {
        this(context, null);
    }

    public MWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
        initClickListener();
        initWebViewSetting();
        initWebViewClient();
    }


    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_webview, this, true);
        webView = view.findViewById(R.id.webview_wb);
        progressBar = view.findViewById(R.id.progress_bar_pb);
        textView = view.findViewById(R.id.title_tv);
        refreshView = view.findViewById(R.id.refresh_iv);
        cancelView = view.findViewById(R.id.cancel_iv);
    }


    private void initClickListener() {
        refreshView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });
        cancelView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                }
            }
        });
    }


    private void initWebViewSetting() {
        WebSettings ws = webView.getSettings();
        ws.setDefaultTextEncodingName("utf-8");
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setRenderPriority(WebSettings.RenderPriority.HIGH);
        ws.setAllowFileAccess(true);
        ws.setAllowContentAccess(true);
        ws.setAppCacheEnabled(false);
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        ws.setSaveFormData(true);
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setLoadsImagesAutomatically(true);
        ws.setLoadWithOverviewMode(true);
        ws.setUseWideViewPort(true);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ws.setAllowFileAccessFromFileURLs(true);
            ws.setAllowUniversalAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(true);
        }
    }

    private void initWebViewClient() {
        MWebChromeClient webChromeClient = new MWebChromeClient(this);
        webView.setWebChromeClient(webChromeClient);
    }


    public void setTitle(String title) {
        if (title != null) {
            textView.setText(title);
        }
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void onProgressChanged(int newProgress) {
        int currentProgress = progressBar.getProgress();
        if (newProgress >= 100) {  //  加载完成
            progressBar.setProgress(newProgress);
            AnimationUtil.startDismissingAnimaiton(progressBar, newProgress);
        } else {  // 加载中
            AnimationUtil.startUploadingAnimation(progressBar, currentProgress, newProgress);
        }
    }

    public boolean canGoBack() {
        // todo
        return false;
    }

    public void goBack() {
        if (webView != null) {
            webView.goBack();
        }
    }


    public void loadUrl(String url) {
        if (webView != null && url != null) {
            webView.loadUrl(url);
        }
    }

    public void reload() {
        if (webView != null) {
            webView.reload();
        }
    }

    public void reset() {
        if (webView != null) {
            webView.stopLoading();
            webView.clearCache(true);
            webView.clearHistory();
            webView.loadUrl("about:blank");
        }
    }


}
