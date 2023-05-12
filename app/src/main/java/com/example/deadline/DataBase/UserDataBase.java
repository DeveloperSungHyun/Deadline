package com.example.deadline.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserDataset.class}, version = 1)
public abstract class UserDataBase extends RoomDatabase {
    public abstract  UserDataDao userDataDao();
}
