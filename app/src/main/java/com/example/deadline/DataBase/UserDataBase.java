package com.example.deadline.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserDataset.class}, version = 2)
public abstract class UserDataBase extends RoomDatabase {
    public abstract  UserDataDao userDataDao();
}
