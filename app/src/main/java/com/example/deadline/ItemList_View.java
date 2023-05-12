package com.example.deadline;

public class ItemList_View {

    int Type;
    String Title;
    String Memo;
    int Y, M, D;//년 월 일
    int time_h, time_m;//시 분

    public ItemList_View(int Type, String Title, String Memo, int Y,int M, int D, int time_h, int time_m){
        this.Type = Type;

        this.Title = Title;
        this.Memo = Memo;

        this.Y = Y;
        this.M = M;
        this.D = D;

        this.time_h = time_h;
        this.time_m = time_m;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }

    public int getTime_h() {
        return time_h;
    }

    public void setTime_h(int time_h) {
        this.time_h = time_h;
    }

    public int getTime_m() {
        return time_m;
    }

    public void setTime_m(int time_m) {
        this.time_m = time_m;
    }
}
