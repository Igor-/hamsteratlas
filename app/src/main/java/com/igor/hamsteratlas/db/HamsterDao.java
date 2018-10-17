package com.igor.hamsteratlas.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.igor.hamsteratlas.model.Hamster;

import java.util.List;

@Dao
public interface HamsterDao {

    @Query("SELECT * FROM hamster")
    List<Hamster> all();

    @Query("SELECT * FROM hamster WHERE pinned = 1")
    List<Hamster>pinned();

    @Query("SELECT * FROM hamster WHERE pinned = 0")
    List<Hamster>notPinned();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertHamsters(List<Hamster> hamsters);

    @Query("DELETE FROM hamster")
    public void deleteAll();

    @Query("SELECT * FROM hamster WHERE name LIKE :search")
    List<Hamster> findByString(String search);

}
