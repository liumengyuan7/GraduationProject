package cn.edu.peaceofmind.activity;

import androidx.appcompat.app.AppCompatActivity;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.entity.ReadInfo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.core.AgentWeb;
import com.xuexiang.xui.utils.StatusBarUtils;

public class ReadDetailActivity extends AppCompatActivity {
    private ReadInfo readInfo;
    private TextView textView;
    private AgentWeb agentWeb;
    private LinearLayout linWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_detail);
        StatusBarUtils.translucent(this, Color.TRANSPARENT);
        StatusBarUtils.setStatusBarLightMode(this);
//        textView=findViewById(R.id.tvView);
//        Intent intent = getIntent();
//        readInfo = (ReadInfo) intent.getSerializableExtra("dynamic");
//        textView.setText(readInfo.toString());
//        System.out.println(readInfo.toString());
        linWeb = findViewById(R.id.lin_web);
        agentWeb=AgentWeb.with(this)
                .setAgentWebParent(linWeb, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()//进度条
                .createAgentWeb()
                .ready()
                .go("https://baidu.com");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (agentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}