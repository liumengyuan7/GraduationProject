package cn.edu.peaceofmind.fragment;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.TitleBar;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.adapter.ReadAdapter;
import cn.edu.peaceofmind.entity.ReadInfo;

@Page(anim = CoreAnim.none)
public class ReadFragment extends XPageFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ReadAdapter readAdapter;
    private List<ReadInfo> list = new ArrayList<>();
//    private SimpleDelegateAdapter<ReadInfo> mNewsAdapter;

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
        return R.layout.fragment_read;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
//        selectAllJokes();======================================
        initLaughInfos();
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        recyclerView.setLayoutManager(virtualLayoutManager);
        readAdapter = new ReadAdapter(getContext(),list);
        recyclerView.setAdapter(readAdapter);
    }

    @Override
    protected void initListeners() {
//        readAdapter.setBtnOnclick(new ReadAdapter.btnOnclick() {
//            @Override
//            public void click(View view, int index) {
//                switch (view.getId()){
//                    case R.id.card_view:
//                        Intent intent = new Intent(getContext(), ReadDetailActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("dynamic",list.get(index));
//                        intent.putExtras(bundle);
//                        startActivity(intent);
//                        break;
//                }
//            }
//        });
        //下拉刷新
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            initLaughInfos();
            refreshLayout.getLayout().postDelayed(() -> {
                readAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
            }, 1000);
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            initLaughInfos();
            refreshLayout.getLayout().postDelayed(() -> {
                readAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }, 1000);
        });
        refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果


    }
    private void initLaughInfos(){
        ReadInfo laughInfo = new ReadInfo("String name");
        laughInfo.setDetailUrl("https://mini.eastday.com//mobile//210218200939611795237.html");
        list.add(laughInfo);
        ReadInfo laughInfo2 = new ReadInfo("aaa");
        list.add(laughInfo2);

        ReadInfo laughInfo3 = new ReadInfo("bbbbbb");
        list.add(laughInfo3);
        ReadInfo laughInfo4 = new ReadInfo("cccccc");
        list.add(laughInfo4);
        ReadInfo laughInfo5 = new ReadInfo("sssss");
        list.add(laughInfo5);
        ReadInfo laughInfo6 = new ReadInfo("dddddddddd");
        list.add(laughInfo6);

    }
}
