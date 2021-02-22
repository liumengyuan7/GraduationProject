package cn.edu.peaceofmind.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.entity.NewsInfo;
import cn.edu.peaceofmind.fragment.LoginFragment;
import cn.edu.peaceofmind.fragment.MainNewsFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.just.agentweb.core.AgentWeb;
import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xui.widget.alpha.XUIAlphaImageView;

public class NewsWebActivity extends XPageActivity {
    private NewsInfo newsInfo;
    private AgentWeb agentWeb;
    private LinearLayout linWeb;
    private XUIAlphaImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);
        StatusBarUtils.translucent(this, Color.TRANSPARENT);
        StatusBarUtils.setStatusBarLightMode(this);
        Intent intent = getIntent();
        newsInfo = (NewsInfo) intent.getSerializableExtra("newsInfo");
        System.out.println(newsInfo.toString());
        linWeb = findViewById(R.id.lin_web);
        ivBack = findViewById(R.id.iv_back);
        agentWeb=AgentWeb.with(this)
                .setAgentWebParent(linWeb, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()//进度条
                .createAgentWeb()
                .ready()
                .go(newsInfo.getUrl());
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                openPage(MainNewsFragment.class);
            }
        });
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