package cn.edu.peaceofmind.fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.TitleBar;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.tabbar.TabSegment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.adapter.NewsAdapter;
import cn.edu.peaceofmind.entity.MultiPage;
import cn.edu.peaceofmind.entity.NewsInfo;
import cn.edu.peaceofmind.utils.Utils;
import cn.edu.peaceofmind.utils.XToastUtils;

@Page(anim = CoreAnim.none)
public class NewFragment extends XPageFragment {

    @BindView(R.id.tabSegment)
    TabSegment mTabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;
    String[] pages = MultiPage.getPageNames();
    private final int TAB_COUNT = 10;
    private int mCurrentItemCount = TAB_COUNT;
    private MultiPage mDestPage = MultiPage.头条;
    private Map<MultiPage, View> mPageMap = new HashMap<>();
    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return mCurrentItemCount;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            MultiPage page = MultiPage.getPage(position);
            View view = getPageView(page);
            view.setTag(page);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(view, params);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            View view = (View) object;
            Object page = view.getTag();
            if (page instanceof MultiPage) {
                int pos = ((MultiPage) page).getPosition();
                if (pos >= mCurrentItemCount) {
                    return POSITION_NONE;
                }
                return POSITION_UNCHANGED;
            }
            return POSITION_NONE;
        }
    };

    private View getPageView(MultiPage page) {
        View view = mPageMap.get(page);
        if (view == null) {
            TextView textView = new TextView(getContext());
            textView.setTextAppearance(getContext(), R.style.TextStyle_Content_Match);
            textView.setGravity(Gravity.CENTER);
            textView.setText(String.format("这个是%s页面的内容", page.name()));
            view = textView;
            mPageMap.put(page, view);
        }
        return view;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new;
    }

    @Override
    protected TitleBar initTitleBar() {
        return null;
    }


    @Override
    protected void initViews() {
        mContentViewPager.setAdapter(mPagerAdapter);
        mContentViewPager.setCurrentItem(mDestPage.getPosition(), false);
        for (int i = 0; i < mCurrentItemCount; i++) {
            mTabSegment.addTab(new TabSegment.Tab(pages[i]));
        }
        int space = DensityUtils.dp2px(getContext(), 16);
        mTabSegment.setHasIndicator(true);
        mTabSegment.setMode(TabSegment.MODE_SCROLLABLE);
        mTabSegment.setItemSpaceInScrollMode(space);
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.setPadding(space, 0, space, 0);
        mTabSegment.addOnTabSelectedListener(new TabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                XToastUtils.toast("select " + pages[index]);
            }

            @Override
            public void onTabUnselected(int index) {
                XToastUtils.toast("unSelect " + pages[index]);
            }

            @Override
            public void onTabReselected(int index) {
                XToastUtils.toast("reSelect " + pages[index]);
            }

            @Override
            public void onDoubleTap(int index) {
                XToastUtils.toast("double tap " + pages[index]);
            }
        });
    }

    @Override
    protected void initListeners() {

    }
    private List<NewsInfo> newsInfoList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = msg.obj + "";
//            list.clear();
            try {
                Log.e("新闻",result);
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0;i<jsonArray.length();i++){
                    JSONObject json = jsonArray.getJSONObject(i);
                    NewsInfo newsInfo = new NewsInfo();
                    newsInfo.setId(json.getInt("id"));
                    newsInfo.setAuther_name(json.getString("auther_name"));
                    newsInfo.setCategory(json.getString("category"));
                    newsInfo.setTitle(json.getString("title"));
                    newsInfo.setRead_zan(json.getInt("read_zan"));
                    newsInfo.setUrl(json.getString("url"));
                    newsInfoList.add(newsInfo);
                }
                newsAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    //根据类别查询新闻
    public void selectNewsByType(String type) {
        new Thread(){
            @Override
            public void run() {
                String result = new Utils().getConnectionResult("read","list","type="+type);
                Message message = new Message();
                message.obj = result;
                message.what=0;
                handler.sendMessage(message);
            }
        }.start();
    }
}
