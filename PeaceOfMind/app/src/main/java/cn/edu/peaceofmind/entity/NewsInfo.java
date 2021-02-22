package cn.edu.peaceofmind.entity;

import java.io.Serializable;

public class NewsInfo implements Serializable {
    private Integer id;
    private String uniquekey;//调用api返回的新闻特定key
    private String auther_name;//作者名称
    //top(头条 默认)shehui(社会),guonei(国内),guoji(国际)
    //,yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    private String type;
    private String category;//新闻类型
    private String url;//新闻链接
    private String title;//新闻标题
    private Integer read_zan;
    private Integer read_commentNum;

    public NewsInfo(){

    }

    //TODO 简易数据设计

    public NewsInfo(Integer id, String auther_name, String type, String title) {
        this.id = id;
        this.auther_name = auther_name;
        this.type = type;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getAuther_name() {
        return auther_name;
    }

    public void setAuther_name(String auther_name) {
        this.auther_name = auther_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRead_zan() {
        return read_zan;
    }

    public void setRead_zan(Integer read_zan) {
        this.read_zan = read_zan;
    }

    public Integer getRead_commentNum() {
        return read_commentNum;
    }

    public void setRead_commentNum(Integer read_commentNum) {
        this.read_commentNum = read_commentNum;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "id=" + id +
                ", uniquekey='" + uniquekey + '\'' +
                ", auther_name='" + auther_name + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", read_zan=" + read_zan +
                ", read_commentNum=" + read_commentNum +
                '}';
    }
}
