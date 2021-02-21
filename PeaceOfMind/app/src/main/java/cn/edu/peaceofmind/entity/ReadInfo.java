package cn.edu.peaceofmind.entity;

import java.io.Serializable;

public class ReadInfo  implements Serializable {
    //用户名
    private String UserName = "yuanyuan";
    //标题
    private String Title;
    //摘要
    private String Summary;
    /**
     * 图片
     */
    private String ImageUrl;
    /**
     * 点赞数
     */
    private int Praise;
    /**
     * 评论数
     */
    private int Comment;
    /**
     * 阅读量
     */
    private int Read;
    /**
     * 新闻的详情地址
     */
    private String DetailUrl;

    public ReadInfo(){

    }

    public ReadInfo(String userName, String title, String summary, String imageUrl, int praise, int comment, int read, String detailUrl) {
        UserName = userName;
        Title = title;
        Summary = summary;
        ImageUrl = imageUrl;
        Praise = praise;
        Comment = comment;
        Read = read;
        DetailUrl = detailUrl;
    }

    public ReadInfo(String title) {
        Title = title;
        Praise = (int) (Math.random() * 100 + 5);
        Comment = (int) (Math.random() * 50 + 5);
        Read = (int) (Math.random() * 500 + 50);
    }

    public String getUserName() {
        return UserName;
    }

    public ReadInfo setUserName(String userName) {
        UserName = userName;
        return this;
    }

    public String getTitle() {
        return Title;
    }

    public ReadInfo setTitle(String title) {
        Title = title;
        return this;
    }

    public String getSummary() {
        return Summary;
    }

    public ReadInfo setSummary(String summary) {
        Summary = summary;
        return this;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public ReadInfo setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
        return this;
    }

    public int getPraise() {
        return Praise;
    }

    public ReadInfo setPraise(int praise) {
        Praise = praise;
        return this;
    }

    public int getComment() {
        return Comment;
    }

    public ReadInfo setComment(int comment) {
        Comment = comment;
        return this;
    }

    public int getRead() {
        return Read;
    }

    public ReadInfo setRead(int read) {
        Read = read;
        return this;
    }

    public String getDetailUrl() {
        return DetailUrl;
    }

    public ReadInfo setDetailUrl(String detailUrl) {
        DetailUrl = detailUrl;
        return this;
    }

    @Override
    public String toString() {
        return "ReadInfo{" +
                "UserName='" + UserName + '\'' +
                ", Title='" + Title + '\'' +
                ", Summary='" + Summary + '\'' +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", Praise=" + Praise +
                ", Comment=" + Comment +
                ", Read=" + Read +
                ", DetailUrl='" + DetailUrl + '\'' +
                '}';
    }
}
