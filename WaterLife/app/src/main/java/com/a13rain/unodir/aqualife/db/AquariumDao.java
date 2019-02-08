package com.a13rain.unodir.aqualife.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AquariumDao {
    @Insert
    void insert(Aquarium aquarium);

    @Query("DELETE FROM aquarium")
    void deleteAll();

    @Query("SELECT * FROM aquarium ORDER BY name ASC")
    LiveData<List<Aquarium>> getAllAquarium();
}
