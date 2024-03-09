package com.example.cs3270_weatherapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.DeleteTable;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocationDAO {

    @Query("select * from Location")
    LiveData<List<Location>> getAll();

    @Query("select * from Location")
    List<Location> getLocations();

    @Query("select * from Location where id =:id")
    Location getByID(int id);

    @Update
    void update(Location location);

    @Delete
    void delete(Location location);

    @Insert
    void insert(Location... locations);

    @Query("delete from Location")
    void deleteAll();
}
