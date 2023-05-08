package com.example.deadline;

public class ItemList_View {

    String Title;
    String Memo;
    int Time_Y, Time_M, Time_D;//년 월 일

    public ItemList_View(String Title, String Memo, int Time_Y,int Time_M,int Time_D){
        this.Title = Title;
        this.Memo = Memo;

        this.Time_Y = Time_Y;
        this.Time_M = Time_M;
        this.Time_D = Time_D;
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

    public int getTime_Y() {
        return Time_Y;
    }

    public void setTime_Y(int time_Y) {
        Time_Y = time_Y;
    }

    public int getTime_M() {
        return Time_M;
    }

    public void setTime_M(int time_M) {
        Time_M = time_M;
    }

    public int getTime_D() {
        return Time_D;
    }

    public void setTime_D(int time_D) {
        Time_D = time_D;
    }
}
