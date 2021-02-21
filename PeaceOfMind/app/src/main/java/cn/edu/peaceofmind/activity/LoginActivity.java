package cn.edu.peaceofmind.activity;

import cn.edu.peaceofmind.fragment.LoginFragment;

import android.graphics.Color;
import android.os.Bundle;

import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xui.utils.StatusBarUtils;

import static android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
import static android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

public class LoginActivity extends XPageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.translucent(this,Color.TRANSPARENT);
        StatusBarUtils.setStatusBarLightMode(this);
        openPage(LoginFragment.class, getIntent().getExtras());
    }

}