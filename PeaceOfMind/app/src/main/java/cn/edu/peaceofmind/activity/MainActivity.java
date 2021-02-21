package cn.edu.peaceofmind.activity;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.fragment.LaughFragment;
import cn.edu.peaceofmind.fragment.NewFragment;
import cn.edu.peaceofmind.fragment.ReadFragment;
import cn.edu.peaceofmind.fragment.TodayFragment;
import cn.edu.peaceofmind.utils.XToastUtils;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

public class MainActivity extends XPageActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener, Toolbar.OnMenuItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    /**
     * 底部导航栏
     */
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    /**
     * 侧边栏
     */
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private String[] mTitles;

//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_main;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.translucent(this,Color.TRANSPARENT);
        StatusBarUtils.setStatusBarLightMode(this);
        setContentView(R.layout.activity_main);
        initViews();

        initListeners();
    }
    private void initViews() {
        viewPager = findViewById(R.id.view_pager);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navView = findViewById(R.id.nav_view);


        mTitles = ResUtils.getStringArray(R.array.home_titles);

        toolbar.setTitle(mTitles[0]);

        setSupportActionBar(toolbar);
//        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);

        initHeader();
        //主页内容填充

        XPageFragment[] fragments = new XPageFragment[]{
                new ReadFragment(),
                new LaughFragment(),
                new NewFragment(),
                new TodayFragment()
//                new TabLayoutViewPager2Fragment()
        };
        com.xuexiang.xui.adapter.FragmentAdapter<XPageFragment> adapter = new FragmentAdapter<>(getSupportFragmentManager(), fragments);
        viewPager.setOffscreenPageLimit(mTitles.length - 1);
        viewPager.setAdapter(adapter);
    }
    public static boolean isColorDark(@ColorInt int color) {
        double darkness =
                1
                        - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color))
                        / 255;
        return darkness >= 0.382;
    }
    private void initHeader() {
        navView.setItemIconTintList(null);
        View headerView = navView.getHeaderView(0);
        LinearLayout navHeader = headerView.findViewById(R.id.nav_header);
        RadiusImageView ivAvatar = headerView.findViewById(R.id.iv_avatar);
        TextView tvAvatar = headerView.findViewById(R.id.tv_avatar);
        TextView tvSign = headerView.findViewById(R.id.tv_sign);

        if (isColorDark(ThemeUtils.resolveColor(this, R.attr.colorAccent))) {
            tvAvatar.setTextColor(Color.WHITE);
            tvSign.setTextColor(Color.WHITE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivAvatar.setImageTintList(ResUtils.getColors(R.color.xui_config_color_white));
            }
        } else {
            tvAvatar.setTextColor(ThemeUtils.resolveColor(this, R.attr.xui_config_color_title_text));
            tvSign.setTextColor(ThemeUtils.resolveColor(this, R.attr.xui_config_color_explain_text));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivAvatar.setImageTintList(ResUtils.getColors(R.color.xui_config_color_gray_3));
            }
        }

        // TODO: 2019-10-09 初始化数据
        ivAvatar.setImageResource(R.drawable.ic_default_head);
        tvAvatar.setText(R.string.app_name);
        tvSign.setText("这个家伙很懒，什么也没有留下～～");
        navHeader.setOnClickListener(this);
    }

    protected void initListeners() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //=================================================

        toolbar.setNavigationIcon(R.mipmap.about_logo);//=====================================
        //侧边栏点击事件
        navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.isCheckable()) {
                drawerLayout.closeDrawers();
                return handleNavigationItemSelected(menuItem);
            } else {
                switch (menuItem.getItemId()) {
//                    case R.id.nav_settings:
//                        openNewPage(SettingsFragment.class);
//                        break;
//                    case R.id.nav_about:
//                        openNewPage(AboutFragment.class);
//                        break;
                    default:
                        XToastUtils.toast("点击了:" + menuItem.getTitle());
                        break;
                }
            }
            return true;
        });

        //主页事件监听
        viewPager.addOnPageChangeListener(this);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    /**
     * 处理侧边栏点击事件
     *
     * @param menuItem
     * @return
     */
    private boolean handleNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = arrayIndexOf(mTitles, menuItem.getTitle());
        if (index != -1) {
            toolbar.setTitle(menuItem.getTitle());
            viewPager.setCurrentItem(index, false);
            return true;
        }
        return false;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_privacy:
//                Utils.showPrivacyDialog(this, null);
//                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_header:
                XToastUtils.toast("点击头部！");
                break;
            default:
                break;
        }
    }

    //=============ViewPager===================//

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        MenuItem item = bottomNavigation.getMenu().getItem(position);
        toolbar.setTitle(item.getTitle());
        item.setChecked(true);

        updateSideNavStatus(item);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    //================Navigation================//

    /**
     * 底部导航栏点击事件
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = arrayIndexOf(mTitles, menuItem.getTitle());
        if (index != -1) {
            toolbar.setTitle(menuItem.getTitle());
//            bottomNavigation
            viewPager.setCurrentItem(index, false);

            updateSideNavStatus(menuItem);
            return true;
        }
        return false;
    }
    /**
     * 搜索条目在集合数组中的索引位置
     *
     * @param array  搜索的数组
     * @param search 搜索的内容
     * @return 索引位置 or `-1`
     */
    public static <E> int arrayIndexOf(@NonNull final E[] array, @NonNull final E search) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(search)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 更新侧边栏菜单选中状态
     *
     * @param menuItem
     */
    private void updateSideNavStatus(MenuItem menuItem) {
        MenuItem side = navView.getMenu().findItem(menuItem.getItemId());
        if (side != null) {
            side.setChecked(true);
        }
    }

    /**
     * 菜单、返回键响应
     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            ClickUtils.exitBy2Click(2000, this);
//        }
//        return true;
//    }

//    @Override
//    public void onRetry() {
//        XToastUtils.toast("再按一次退出程序");
//    }
//
//    @Override
//    public void onExit() {
//        XUtil.exitApp();
//    }

    /**
     * 根据屏幕的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}