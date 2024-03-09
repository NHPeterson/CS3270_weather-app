package com.example.cs3270_weatherapp;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cs3270_weatherapp.db.AppDatabase;
import com.example.cs3270_weatherapp.db.Location;

import java.util.List;

public class AllLocationsViewModel extends ViewModel {

    private LiveData<List<Location>> locationList;

    public LiveData<List<Location>> getLocationList(Context c) {
        if (locationList == null) {
            locationList = AppDatabase.getInstance(c).locationDAO().getAll();
        }
        return locationList;
    }
}
