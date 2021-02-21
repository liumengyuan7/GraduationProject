package cn.edu.peaceofmind.fragment;

import android.view.View;
import android.widget.TextView;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.TitleBar;
import com.xuexiang.xui.adapter.recyclerview.XLinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.adapter.LaughAdapter;
import cn.edu.peaceofmind.entity.LaughInfo;

@Page(anim = CoreAnim.none)
public class TodayFragment extends XPageFragment implements CalendarView.OnCalendarSelectListener, CalendarView.OnYearChangeListener {
    @BindView(R.id.tv_month_day)
    TextView mTextMonthDay;
    @BindView(R.id.tv_year)
    TextView mTextYear;
    @BindView(R.id.tv_lunar)
    TextView mTextLunar;
    @BindView(R.id.tv_current_day)
    TextView mTextCurrentDay;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.calendarLayout)
    CalendarLayout mCalendarLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private int mYear;
    @Override
    protected TitleBar initTitleBar() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_today;
    }

    @Override
    protected void initViews() {
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
        mCalendarView.setFixMode();

        initCalendarView();

        initRecyclerView();
    }

    @OnClick({R.id.tv_month_day, R.id.fl_current})
    public void onViewClicked(View view) {
        switch(view.getId()) {
            case R.id.tv_month_day:
                if (!mCalendarLayout.isExpand()) {
                    mCalendarLayout.expand();
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
                break;
            case R.id.fl_current:
                mCalendarView.scrollToCurrent();
                break;
            default:
                break;
        }
    }

    @Override
    protected void initListeners() {
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }


    private void initRecyclerView() {
        recyclerView.setLayoutManager(new XLinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        List<LaughInfo> list = new ArrayList<>();
        LaughAdapter mAdapter= new LaughAdapter(list);
        LaughInfo laughInfo = new LaughInfo(1,getRandomLengthName("String name"),2);
        list.add(laughInfo);
        LaughInfo laughInfo2 = new LaughInfo(2,getRandomLengthName("aaa"),2);
        list.add(laughInfo2);

        LaughInfo laughInfo3 = new LaughInfo(3,getRandomLengthName("bbbbbb"),2);
        list.add(laughInfo3);
        LaughInfo laughInfo4 = new LaughInfo(4,getRandomLengthName("cccccc"),2);
        list.add(laughInfo4);
        LaughInfo laughInfo5 = new LaughInfo(5,getRandomLengthName("sssss"),2);
        list.add(laughInfo5);
        LaughInfo laughInfo6 = new LaughInfo(6,getRandomLengthName("dddddddddd"),2);
        list.add(laughInfo6);
        recyclerView.setAdapter(mAdapter );
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
    private void initCalendarView() {
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        Map<String, Calendar> map = new HashMap<>();
        map.put(getSchemeCalendar(year, month, 3, 0xFF40db25, "假").toString(),
                getSchemeCalendar(year, month, 3, 0xFF40db25, "假"));
        map.put(getSchemeCalendar(year, month, 6, 0xFFe69138, "事").toString(),
                getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
        map.put(getSchemeCalendar(year, month, 9, 0xFFdf1356, "议").toString(),
                getSchemeCalendar(year, month, 9, 0xFFdf1356, "议"));
        map.put(getSchemeCalendar(year, month, 13, 0xFFedc56d, "记").toString(),
                getSchemeCalendar(year, month, 13, 0xFFedc56d, "记"));
        map.put(getSchemeCalendar(year, month, 14, 0xFFedc56d, "记").toString(),
                getSchemeCalendar(year, month, 14, 0xFFedc56d, "记"));
        map.put(getSchemeCalendar(year, month, 15, 0xFFaacc44, "假").toString(),
                getSchemeCalendar(year, month, 15, 0xFFaacc44, "假"));
        map.put(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记").toString(),
                getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记"));
        map.put(getSchemeCalendar(year, month, 25, 0xFF13acf0, "假").toString(),
                getSchemeCalendar(year, month, 25, 0xFF13acf0, "假"));
        map.put(getSchemeCalendar(year, month, 27, 0xFF13acf0, "多").toString(),
                getSchemeCalendar(year, month, 27, 0xFF13acf0, "多"));
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        calendar.addScheme(new Calendar.Scheme());
        calendar.addScheme(0xFF008800, "假");
        calendar.addScheme(0xFF008800, "节");
        return calendar;
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }
}
