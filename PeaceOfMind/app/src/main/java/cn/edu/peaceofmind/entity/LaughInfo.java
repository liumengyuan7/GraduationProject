package cn.edu.peaceofmind.entity;

public class LaughInfo {
    private int LaughId;
    //摘要
    private String Summary;
    /**
     * 点赞数
     */
    private int Praise;
    /**
     * 笑话的详情地址
     */
    private String DetailUrl;

    public LaughInfo(){

    }

    public LaughInfo(String summary,  int praise,  String detailUrl) {
        Summary = summary;
        Praise = praise;
        DetailUrl = detailUrl;
    }
    public LaughInfo(int laughId, String summary, int praise) {
        LaughId = laughId;
        Summary = summary;
        Praise = praise;
    }
    public LaughInfo(String title) {
        Praise = (int) (Math.random() * 100 + 5);
    }


    public String getSummary() {
        return Summary;
    }

    public LaughInfo setSummary(String summary) {
        Summary = summary;
        return this;
    }

    public int getPraise() {
        return Praise;
    }

    public LaughInfo setPraise(int praise) {
        Praise = praise;
        return this;
    }

    public String getDetailUrl() {
        return DetailUrl;
    }

    public LaughInfo setDetailUrl(String detailUrl) {
        DetailUrl = detailUrl;
        return this;
    }

    public int getLaughId() {
        return LaughId;
    }

    public void setLaughId(int laughId) {
        LaughId = laughId;
    }

    @Override
    public String toString() {
        return "LaughInfo{" +
                "LaughId=" + LaughId +
                ", Summary='" + Summary + '\'' +
                ", Praise=" + Praise +
                ", DetailUrl='" + DetailUrl + '\'' +
                '}';
    }
}
