package com.example.deadline.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDataDao {

    @Insert
    void setInsert(UserDataset userDataset);

    @Update
    void setUpdate(UserDataset userDataset);

    @Delete
    void setDelete(UserDataset userDataset);

    @Query("SELECT * FROM UserDataset ORDER BY Y, M, D, time_h, time_m, Title")
    List<UserDataset> getDataAll();

}
