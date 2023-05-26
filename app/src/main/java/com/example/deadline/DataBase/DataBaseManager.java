package com.example.deadline.DataBase;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {

    private UserDataDao dataDao;
    private UserDataset userDataset;

    public DataBaseManager(Context context){
        UserDataBase dataBase = Room.databaseBuilder(context, UserDataBase.class, "data")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        dataDao = dataBase.userDataDao();

        userDataset = new UserDataset();
    }

    public void setInsert(int Type, String Title, String Memo, int Date_Y, int Date_M, int Date_D, int Time_h, int Time_m){

        userDataset.setType(Type);
        userDataset.setTitle(Title);
        userDataset.setMemo(Memo);
        userDataset.setY(Date_Y);
        userDataset.setM(Date_M);
        userDataset.setD(Date_D);
        userDataset.setTime_h(Time_h);
        userDataset.setTime_m(Time_m);

        dataDao.setInsert(userDataset);
    }


    public void setUpData(int id, int Type, String Title, String Memo, int Date_Y, int Date_M, int Date_D, int Time_h, int Time_m){

        userDataset.setId(id);
        userDataset.setType(Type);
        userDataset.setTitle(Title);
        userDataset.setMemo(Memo);
        userDataset.setY(Date_Y);
        userDataset.setM(Date_M);
        userDataset.setD(Date_D);
        userDataset.setTime_h(Time_h);
        userDataset.setTime_m(Time_m);

        dataDao.setUpdate(userDataset);
    }

    public void setDelete(int id){
        userDataset.setId(id);
        dataDao.setDelete(userDataset);
    }

    public List<UserDataset> getDate(){

        List<UserDataset> userDataset;

        userDataset = dataDao.getDataAll();
        return userDataset;
    }
}
