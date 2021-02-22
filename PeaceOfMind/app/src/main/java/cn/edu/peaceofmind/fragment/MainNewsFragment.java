package cn.edu.peaceofmind.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.TitleBar;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.tabbar.TabSegment;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.entity.MultiPage;
import cn.edu.peaceofmind.utils.XToastUtils;

import static com.google.android.material.tabs.TabLayout.MODE_FIXED;

@Page(anim = CoreAnim.none)
public class MainNewsFragment extends XPageFragment {
    @BindView(R.id.tabSegment)
    TabSegment tabSegment;

//    @BindView(R.id.tab_layout)
//    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    String[] pages = MultiPage.getPageNames();
    String[] types ={"头条","社会","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};
    private final int TAB_COUNT = 10;
    private int mCurrentItemCount = TAB_COUNT;
    private MultiPage mDestPage = MultiPage.头条;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_news;
    }

    @Override
    protected TitleBar initTitleBar() {
        return null;
    }

    @Override
    protected void initViews() {
        viewPager.setCurrentItem(mDestPage.getPosition(), false);
        FragmentAdapter<XPageFragment> adapter = new FragmentAdapter<>(getChildFragmentManager());
//        for (String page : pages) {
////            tabLayout.addTab(tabLayout.newTab().setText(page));
//            tabSegment.addTab(new TabSegment.Tab(page));
//            adapter.addFragment(new NewsFragment(page), page);
//        }
        for (int i = 0; i < mCurrentItemCount; i++) {
            tabSegment.addTab(new TabSegment.Tab(pages[i]));
            adapter.addFragment(new NewsFragment(types[i]), pages[i]);
        }
//        viewPager.setOffscreenPageLimit(pages.length - 1);
        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//        tabSegment.setupWithViewPager(viewPager);
//        ViewUtils.setViewsFont(tabLayout);
//        ViewUtils.setViewsFont(tabSegment);

        int space = DensityUtils.dp2px(getContext(), 16);
        tabSegment.setHasIndicator(true);
        tabSegment.setMode(TabSegment.MODE_SCROLLABLE);
        tabSegment.setItemSpaceInScrollMode(space);
        tabSegment.setupWithViewPager(viewPager, false);
        tabSegment.setPadding(space, 0, space, 0);
        tabSegment.addOnTabSelectedListener(new TabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                XToastUtils.toast("select " + pages[index]);
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {

            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }

    @Override
    protected void initListeners() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}