package com.example.cs3270_weatherapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs3270_weatherapp.db.Location;

import java.util.ArrayList;
import java.util.List;


public class MainActivityFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private LocationRecyclerViewAdapter lrva;
    private int columnCount = 1;

    public MainActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Context context = getContext();
        lrva = new LocationRecyclerViewAdapter(new ArrayList<Location>());

        if (columnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }

        recyclerView.setAdapter(lrva);
        recyclerView.setHasFixedSize(false);

        new ViewModelProvider(this)
                .get(AllLocationsViewModel.class)
                .getLocationList(context)
                .observe(this, new Observer<List<Location>>() {
                    @Override
                    public void onChanged(List<Location> locations) {
                        if (locations != null) {
                            lrva.addItems(locations);
                        }
                    }
                });
    }
}