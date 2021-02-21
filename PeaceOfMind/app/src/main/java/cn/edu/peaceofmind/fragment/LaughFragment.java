package cn.edu.peaceofmind.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.TitleBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.adapter.LaughAdapter;
import cn.edu.peaceofmind.entity.LaughInfo;
import cn.edu.peaceofmind.utils.Utils;

@Page(anim = CoreAnim.none)
public class LaughFragment extends XPageFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private LaughAdapter laughAdapter;
    private  List<LaughInfo> list = new ArrayList<>();
    //翻转部分
    AnimatorSet mRightOutSet, mLeftOutSet;
    boolean mIsShowBack = false;
    @BindView(R.id.main_fl_card_back)
    LinearLayout mainFlCardBack;
    @BindView(R.id.main_fl_card_front)
    RelativeLayout mainFlCardFront;
    @BindView(R.id.main_fl_container)
    RelativeLayout mainFlContainer;
    @BindView(R.id.tv_laugh)
    TextView textView;
    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitleBar() {
        return null;
    }

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_laugh;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        setAnimators();//设置动画
        setCameraDistance();//设置距离
        initLaughInfos();
//        TODO 每次查一页  一次查二十条
//        selectAllJokes(1,20);======================================
        //TODO 点赞数量最高
        // laughOneDay();
        textView.setText("我们都是好孩子，清澈的爱，只为中国。向英雄致敬！你有什么理由不努力呢，岁月静好是因为有人为你负重前行！");
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        laughAdapter = new LaughAdapter(list);
        recyclerView.setAdapter(laughAdapter);
    }
    @Override
    protected void initListeners() {
        //翻转动画监听
        mainFlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //正面朝上
                if (!mIsShowBack) {
                    mRightOutSet.setTarget(mainFlCardBack);
                    mainFlCardBack.setVisibility(View.VISIBLE);
                    mLeftOutSet.setTarget(mainFlCardFront);
                    mRightOutSet.start();
                    mLeftOutSet.start();
                    mIsShowBack = true;
                } else {
                    mRightOutSet.setTarget(mainFlCardFront);
                    mLeftOutSet.setTarget(mainFlCardBack);
                    mRightOutSet.start();
                    mLeftOutSet.start();
                    mIsShowBack = false;
                }
            }
        });
        //下拉刷新
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            initLaughInfos();
            refreshLayout.getLayout().postDelayed(() -> {
                laughAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
            }, 1000);
        });
//        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            initLaughInfos();
            refreshLayout.getLayout().postDelayed(() -> {
                laughAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }, 1000);
        });
//        refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }
    private void initLaughInfos(){
        LaughInfo laughInfo = new LaughInfo(1,getRandomLengthName("11111111"),2);
        list.add(laughInfo);
        LaughInfo laughInfo2 = new LaughInfo(2,getRandomLengthName("22222222"),2);
        list.add(laughInfo2);

        LaughInfo laughInfo3 = new LaughInfo(3,getRandomLengthName("33333333"),2);
        list.add(laughInfo3);
        LaughInfo laughInfo4 = new LaughInfo(4,getRandomLengthName("44444444"),2);
        list.add(laughInfo4);
        LaughInfo laughInfo5 = new LaughInfo(5,getRandomLengthName("55555555"),2);
        list.add(laughInfo5);
        LaughInfo laughInfo6 = new LaughInfo(6,getRandomLengthName("66666666"),2);
        list.add(laughInfo6);
        Collections.shuffle(list);//TODO 打乱数组顺序
    }
    private String getRandomLengthName(String name){
        Random random = new Random();
        int length= random.nextInt(20)+1;  // 产生1-20的随机数
        StringBuilder builder = new StringBuilder();
        for (int i =0;i<length;i++){
            builder.append(name);
        }
        return  builder.toString();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = msg.obj + "";
            switch (msg.what){
                case 0:
                    list.clear();
                    Log.e("笑话",result);
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        for (int i = 0;i<jsonArray.length();i++){
                            JSONObject json = jsonArray.getJSONObject(i);
                            LaughInfo laughInfo = new LaughInfo();
                            laughInfo.setSummary(json.getString("laugh_content"));
                            laughInfo.setPraise(json.getInt("laugh_id"));
                            list.add(laughInfo);
                        }

                        laughAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                case 1:
                    textView.setText("我们都是好孩子，清澈的爱，只为中国。向英雄致敬！你有什么理由不努力呢，岁月静好是因为有人为你负重前行！");
                    break;
            }
        }
    };
    //分页查询笑话
    public void selectAllJokes(int pagenum,int pagesize) {
        new Thread(){
            @Override
            public void run() {
                String result = new Utils().getConnectionResult("joke","listByPage","pagenum="+pagenum
                        +"&&pagesize="+pagesize);
                Message message = new Message();
                message.obj = result;
                handler.sendMessage(message);
            }
        }.start();
    }
    //根据点赞情况获取笑话  展示每日一笑
    public void laughOneDay(){
        new Thread(){
            @Override
            public void run() {
                String result = new Utils().getConnectionResult("joke","");
                Message message = new Message();
                message.obj = result;
                message.what=1;
                handler.sendMessage(message);
            }
        }.start();
    }

    //设置动画
    private void setAnimators() {
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.anim_out);
        mLeftOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.anim_in);
        //设置点击事件
        mRightOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mainFlContainer.setClickable(false);
            }
        });

        mLeftOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mainFlContainer.setClickable(true);
            }
        });
    }

    //改变视角距离，贴近屏幕
    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mainFlCardFront.setCameraDistance(scale);
        mainFlCardBack.setCameraDistance(scale);
    }

    //点击卡片翻转卡片
    public void fipCard(View view) {
        //正面朝上
        if (!mIsShowBack) {
            mRightOutSet.setTarget(mainFlCardBack);
            mLeftOutSet.setTarget(mainFlCardFront);
            mRightOutSet.start();
            mLeftOutSet.start();
            mIsShowBack = true;
        } else {
            mRightOutSet.setTarget(mainFlCardFront);
            mLeftOutSet.setTarget(mainFlCardBack);
            mRightOutSet.start();
            mLeftOutSet.start();
            mIsShowBack = false;
        }
    }
}
