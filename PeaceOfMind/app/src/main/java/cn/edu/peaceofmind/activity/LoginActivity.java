package cn.edu.peaceofmind.activity;

import cn.edu.peaceofmind.fragment.LoginFragment;

import android.graphics.Color;
import android.os.Bundle;

import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xui.utils.StatusBarUtils;

public class LoginActivity extends XPageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.translucent(this);
        openPage(LoginFragment.class, getIntent().getExtras());
    }

}