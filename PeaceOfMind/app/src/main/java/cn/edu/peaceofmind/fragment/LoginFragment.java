package cn.edu.peaceofmind.fragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.TitleBar;
import com.xuexiang.xui.utils.StatusBarUtils;

import androidx.fragment.app.Fragment;
import cn.edu.peaceofmind.R;

@Page(anim = CoreAnim.none)//设置切换进入当前类时无动画
public class LoginFragment extends XPageFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected TitleBar initTitleBar() {
        return null;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }
}