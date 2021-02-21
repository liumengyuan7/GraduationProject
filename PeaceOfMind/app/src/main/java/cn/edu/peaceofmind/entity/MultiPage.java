package cn.edu.peaceofmind.entity;


public enum MultiPage {

    新闻(0),
    教育(1),
    体育(2),
    娱乐(3),
    八卦(4),
    母婴(5),
    音乐(6),
    健康(7),
    旅游(8),
    文化(9);

    private final int position;

    MultiPage(int pos) {
        position = pos;
    }

    public static MultiPage getPage(int position) {
        return MultiPage.values()[position];
    }

    public static int size() {
        return MultiPage.values().length;
    }

    public static String[] getPageNames() {
        MultiPage[] pages = MultiPage.values();
        String[] pageNames = new String[pages.length];
        for (int i = 0; i < pages.length; i++) {
            pageNames[i] = pages[i].name();
        }
        return pageNames;
    }

    public int getPosition() {
        return position;
    }

}
