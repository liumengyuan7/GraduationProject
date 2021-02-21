package cn.edu.peaceofmind.entity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;


import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cn.edu.peaceofmind.R;
import cn.edu.peaceofmind.utils.Utils;
import cn.edu.peaceofmind.utils.XToastUtils;

/**
 * 演示数据
 *
 * @author xuexiang
 * @since 2018/11/23 下午5:52
 */
public class DemoDataProvider {
    public static String[] titles = new String[]{
        "伪装者:胡歌演绎'痞子特工'",
        "无心法师:生死离别!月牙遭虐杀",
        "花千骨:尊上沦为花千骨",
    };
    public static String[] urls = new String[]{//640*360 360/640=0.5625
        "http://photocdn.sohu.com/tvmobilemvms/20150907/144160323071011277.jpg",//伪装者:胡歌演绎"痞子特工"
        "http://photocdn.sohu.com/tvmobilemvms/20150907/144158380433341332.jpg",//无心法师:生死离别!月牙遭虐杀
        "http://photocdn.sohu.com/tvmobilemvms/20150907/144160286644953923.jpg",//花千骨:尊上沦为花千骨
    };

    public static List<BannerItem> getBannerList() {
        List<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            BannerItem item = new BannerItem();
            item.imgUrl = urls[i];
            item.title = titles[i];

            list.add(item);
        }
        return list;
    }
//=====================================================================================================
    /**
     * 用于占位的空信息   阅读页面
     *
     * @return
     */

    public static List<ReadInfo> getDemoNewInfos() {
        List<ReadInfo> list = new ArrayList<>();
        list.add(new ReadInfo("Android源码分析--Android系统启动")
                .setSummary("其实Android系统的启动最主要的内容无非是init、Zygote、SystemServer这三个进程的启动，他们一起构成的铁三角是Android系统的基础。")
        );
//                .setDetailUrl("https://juejin.im/post/5c6fc0cdf265da2dda694f05")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2019/2/22/16914891cd8a950a?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));

        list.add(new ReadInfo("XUI 一个简洁而优雅的Android原生UI框架，解放你的双手")
                .setSummary("涵盖绝大部分的UI组件：TextView、Button、EditText、ImageView、Spinner、Picker、Dialog、PopupWindow、ProgressBar、LoadingView、StateLayout、FlowLayout、Switch、Actionbar、TabBar、Banner、GuideView、BadgeView、MarqueeView、WebView、SearchView等一系列的组件和丰富多彩的样式主题。\n")
        );
//                .setDetailUrl("https://juejin.im/post/5c3ed1dae51d4543805ea48d")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2019/1/16/1685563ae5456408?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));

        list.add(new ReadInfo("写给即将面试的你")
                .setSummary("最近由于公司业务发展，需要招聘技术方面的人才，由于我在技术方面比较熟悉，技术面的任务就交给我了。今天我要分享的就和面试有关，主要包含技术面的流程、经验和建议，避免大家在今后的面试过程中走一些弯路，帮助即将需要跳槽面试的人。")
        );
//                .setDetailUrl("https://juejin.im/post/5ca4df966fb9a05e4e58320c")
//                .setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554629219186&di=6cdab5cfceaae1f7e6d78dbe79104c9f&imgtype=0&src=http%3A%2F%2Fimg.qinxue365.com%2Fuploads%2Fallimg%2F1902%2F4158-1Z22FZ64E00.jpg"));

        list.add(new ReadInfo("XUpdate 一个轻量级、高可用性的Android版本更新框架")
                .setSummary("XUpdate 一个轻量级、高可用性的Android版本更新框架。本框架借鉴了AppUpdate中的部分思想和UI界面，将版本更新中的各部分环节抽离出来，形成了如下几个部分：")
        );
//                .setDetailUrl("https://juejin.im/post/5b480b79e51d45190905ef44")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2018/7/13/16492d9b7877dc21?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));

        list.add(new ReadInfo("XHttp2 一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp进行组装")
                .setSummary("一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp组合进行封装。还不赶紧点击使用说明文档，体验一下吧！")
        );
        return list;
    }
    /**
     * 用于占位的空信息   笑话页面
     *
     * @return
     */

    public static List<LaughInfo> getDemoLaughInfos() {
        List<LaughInfo> list = new ArrayList<>();
        list.add(new LaughInfo("这是第一个笑话")
                .setSummary("其实是假的，哈哈哈哈哈。")
        );
//                .setDetailUrl("https://juejin.im/post/5c6fc0cdf265da2dda694f05")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2019/2/22/16914891cd8a950a?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));

        list.add(new LaughInfo("这是第二个笑话")
                .setSummary("这是真的\n")
        );
//                .setDetailUrl("https://juejin.im/post/5c3ed1dae51d4543805ea48d")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2019/1/16/1685563ae5456408?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
        return list;
    }
    public static List<AdapterItem> getGridItems(Context context) {
        return getGridItems(context, R.array.grid_titles_entry, R.array.grid_icons_entry);
    }


    private static List<AdapterItem> getGridItems(Context context, int titleArrayId, int iconArrayId) {
        List<AdapterItem> list = new ArrayList<>();
        String[] titles = ResUtils.getStringArray(titleArrayId);
        Drawable[] icons = ResUtils.getDrawableArray(context, iconArrayId);
        for (int i = 0; i < titles.length; i++) {
            list.add(new AdapterItem(titles[i], icons[i]));
        }
        return list;
    }

    /**
     * 用于占位的空信息
     *
     * @return
     */

    public static List<ReadInfo> getEmptyNewInfo() {
        List<ReadInfo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new ReadInfo());
        }
        return list;
    }
    /**
     * 用于占位的空信息
     *
     * @return
     */
//    public static List<LaughInfo> list = new ArrayList<>();
//    public static List<LaughInfo> getEmptyLaughInfo() {
////        selectAllJokes();
//        return list;
//    }
}
