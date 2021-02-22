package cn.edu.peaceofmind.fragment;

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.TitleBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.adapter.NewsAdapter;
import cn.edu.peaceofmind.entity.NewsInfo;
import cn.edu.peaceofmind.utils.Utils;

@Page(anim = CoreAnim.none)
public class NewsFragment extends XPageFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String type;
    private NewsAdapter newsAdapter;
    private List<NewsInfo> list = new ArrayList<>();

    public NewsFragment(String type) {
        this.type =type;
    }

    @Override
    protected TitleBar initTitleBar() {
        return null;
    }

    @Override
    protected int getLayoutId() { return R.layout.fragment_news; }

    @Override
    protected void initViews() {
//        initLaughInfos();
        selectNewsByType();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(getContext(),list);
        recyclerView.setAdapter(newsAdapter);
    }

    @Override
    protected void initListeners() {
        //下拉刷新
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            //模拟了网络请求
//            initLaughInfos();
            selectNewsByType();
            refreshLayout.getLayout().postDelayed(() -> {
                newsAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
            }, 1000);
        });
//        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            // 模拟了网络请求
//            initLaughInfos();
            selectNewsByType();
            refreshLayout.getLayout().postDelayed(() -> {
                newsAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }, 1000);
        });
    }
    //前端测试数据
//    private void initLaughInfos(){
//        NewsInfo laughInfo = new NewsInfo(1,type,"第一条数据","少时诵诗书");
//        list.add(laughInfo);
//        NewsInfo laughInfo2 = new NewsInfo(2,type,"第一条数据","是第三方发");
//        list.add(laughInfo2);
//        NewsInfo laughInfo3 = new NewsInfo(3,type,"第一条数据","额五日翁认为");
//        list.add(laughInfo3);
//    }

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
                    list.add(newsInfo);
                }
                newsAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    //根据类别查询新闻
    public void selectNewsByType() {
        new Thread(){
            @Override
            public void run() {
                String result = new Utils().getConnectionResult("read","list","type="+type);
                Message message = new Message();
                message.obj = result;
                handler.sendMessage(message);
            }
        }.start();
    }
}