package com.example.cs3270_weatherapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs3270_weatherapp.db.AppDatabase;
import com.example.cs3270_weatherapp.db.Authorization;
import com.example.cs3270_weatherapp.db.Location;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class AddLocationFragment extends DialogFragment {

    private View view;
    private Toolbar toolbar;
    private EditText txtSearch;
    private Button btnSearch, btnAdd;
    private TextView txtAddLocationName, txtAddForecastToday;
    private ImageView imgAddForecastImg;
    private WeatherDataService weatherDataService;
    private BingDataService bingDataService;
    private String name, coords;
    private Location location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_location, container, false);
        toolbar = view.findViewById(R.id.toolbarAddLocation);
        txtSearch = view.findViewById(R.id.txtSearch);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnAdd = view.findViewById(R.id.btnAdd);
        txtAddLocationName = view.findViewById(R.id.txtAddLocationName);
        txtAddForecastToday = view.findViewById(R.id.txtAddForecastToday);
        imgAddForecastImg = view.findViewById(R.id.imgAddForecastImg);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherDataService = new WeatherDataService(getContext());
        bingDataService = new BingDataService(getContext());
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        toolbar.setTitle("Add Location");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = txtSearch.getText().toString();
                bingDataService.getGeocode(name, new BingDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Log.d("API", message);
                    }
                    @Override
                    public void onResponseString(String bingName, String bingCoords) {
                        name = bingName;
                        coords = bingCoords;
                        weatherDataService.getForecastURL(coords, new WeatherDataService.VolleyResponseListener() {
                            @Override
                            public void onError(String message) {
                                Log.d("API", message);
                            }
                            @Override
                            public void onResponseString(String response) {
                                weatherDataService.getForecast(response, name, coords, new WeatherDataService.VolleyResponseListener() {
                                    @Override
                                    public void onError(String message) {
                                        Log.d("API", message);
                                    }
                                    @Override
                                    public void onResponseString(String response) {

                                    }
                                    @Override
                                    public void onResponseClass(Location response) {
                                        location = response;
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                getActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        txtAddLocationName.setText(name);
                                                        txtAddForecastToday.setText(location.getForecast_today());
                                                        Picasso.get()
                                                                .load(location.getPicture_today())
                                                                .memoryPolicy(MemoryPolicy.NO_CACHE)
                                                                .networkPolicy(NetworkPolicy.NO_CACHE)
                                                                .into(imgAddForecastImg);
                                                    }
                                                });
                                            }
                                        }).start();
                                        btnAdd.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                            @Override
                            public void onResponseClass(Location response) {

                            }
                        });
                    }
                });
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase.getInstance(getContext())
                                .locationDAO()
                                .insert(location);
                    }
                }).start();
                Toast.makeText(getContext(), "Location added", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }
}