package cn.edu.peaceofmind.entity;


public enum MultiPage {

    头条(0),
    社会(1),
    国内(2),
    国际(3),
    娱乐(4),
    体育(5),
    军事(6),
    科技(7),
    财经(8),
    时尚(9);

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
