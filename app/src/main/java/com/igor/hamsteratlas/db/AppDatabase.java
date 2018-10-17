package com.igor.hamsteratlas.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.igor.hamsteratlas.model.Hamster;

@Database(entities = {Hamster.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HamsterDao hamsterDao();
}
