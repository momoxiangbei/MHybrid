package com.mmxb.hybridmodule;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mmxb.hybridmodule.webview.MWebView;

import static com.mmxb.hybridmodule.data.WebViewConstant.WEB_VIEW_URL;


/**
 * Created by xueying on 2018/5/9.
 */

public class MWebFragment extends Fragment {
    private FrameLayout webViewContainer;

    private MWebView webView;
    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            url = bundle.getString(WEB_VIEW_URL);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_layout, container, false);
        webViewContainer = view.findViewById(R.id.webview_container);
        webView = new MWebView(this.getActivity());
        webView.loadUrl(url);
        webViewContainer.addView(webView);
        return view;
    }


    public boolean onBackPressed() {
        if (webView != null) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return false;
    }
}
