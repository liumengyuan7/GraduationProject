package cn.edu.peaceofmind.activity;

import androidx.appcompat.app.AppCompatActivity;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.fragment.LoginFragment;

import android.os.Bundle;

import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xui.utils.StatusBarUtils;

public class MainActivity extends XPageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        openPage(LoginFragment.class);
        StatusBarUtils.translucent(this);
        setContentView(R.layout.activity_main);
    }
}