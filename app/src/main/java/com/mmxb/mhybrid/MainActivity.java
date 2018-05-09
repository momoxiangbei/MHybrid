package com.mmxb.mhybrid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.mmxb.hybridmodule.MWebActivity;
import com.mmxb.hybridmodule.bridge.JSBridge;

import org.w3c.dom.Text;

import static com.mmxb.hybridmodule.data.WebViewConstant.WEB_VIEW_URL;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        context = this;


        JSBridge jsBridge = new JSBridge(new WebView(this));
        jsBridge.registerMethod(TestBridge.class.getSimpleName(), new TestBridge(jsBridge));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MWebActivity.class);
                intent.putExtra(WEB_VIEW_URL, "https://www.baidu.com");
                startActivity(intent);
            }
        });


    }
}
