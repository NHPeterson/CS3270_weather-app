package com.example.cs3270_weatherapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs3270_weatherapp.db.AppDatabase;
import com.example.cs3270_weatherapp.db.Location;

import java.util.List;

public class WeeklyForecastFragment extends DialogFragment {

    private View view;
    private Toolbar toolbar;
    private TextView txtHigh1, txtLow1, txtPrecip1,
            txtDay2, txtHigh2, txtLow2, txtPrecip2, txtDay3, txtHigh3, txtLow3, txtPrecip3,
            txtDay4, txtHigh4, txtLow4, txtPrecip4, txtDay5, txtHigh5, txtLow5, txtPrecip5,
            txtDay6, txtHigh6, txtLow6, txtPrecip6, txtDay7, txtHigh7, txtLow7, txtPrecip7;
    Location location;
    Bundle bundle;
    WeatherDataService weatherDataService;
    int location_pk;

    public WeeklyForecastFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_weekly_forecast, container, false);
        toolbar = view.findViewById(R.id.toolbarWeeklyForecast);
        txtHigh1 = view.findViewById(R.id.txtHigh1);
        txtLow1 = view.findViewById(R.id.txtLow1);
        txtPrecip1 = view.findViewById(R.id.txtPrecip1);
        txtDay2 = view.findViewById(R.id.txtDay2);
        txtHigh2 = view.findViewById(R.id.txtHigh2);
        txtLow2 = view.findViewById(R.id.txtLow2);
        txtPrecip2 = view.findViewById(R.id.txtPrecip2);
        txtDay3 = view.findViewById(R.id.txtDay3);
        txtHigh3 = view.findViewById(R.id.txtHigh3);
        txtLow3 = view.findViewById(R.id.txtLow3);
        txtPrecip3 = view.findViewById(R.id.txtPrecip3);
        txtDay4 = view.findViewById(R.id.txtDay4);
        txtHigh4 = view.findViewById(R.id.txtHigh4);
        txtLow4 = view.findViewById(R.id.txtLow4);
        txtPrecip4 = view.findViewById(R.id.txtPrecip4);
        txtDay5 = view.findViewById(R.id.txtDay5);
        txtHigh5 = view.findViewById(R.id.txtHigh5);
        txtLow5 = view.findViewById(R.id.txtLow5);
        txtPrecip5 = view.findViewById(R.id.txtPrecip5);
        txtDay6 = view.findViewById(R.id.txtDay6);
        txtHigh6 = view.findViewById(R.id.txtHigh6);
        txtLow6 = view.findViewById(R.id.txtLow6);
        txtPrecip6 = view.findViewById(R.id.txtPrecip6);
        txtDay7 = view.findViewById(R.id.txtDay7);
        txtHigh7 = view.findViewById(R.id.txtHigh7);
        txtLow7 = view.findViewById(R.id.txtLow7);
        txtPrecip7 = view.findViewById(R.id.txtPrecip7);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherDataService = new WeatherDataService(getContext());

        bundle = this.getArguments();
        if (bundle != null) {
            location_pk = bundle.getInt("location_pk");
            new ViewModelProvider(this)
                    .get(AllLocationsViewModel.class)
                    .getLocationList(getContext())
                    .observe(this, new Observer<List<Location>>() {
                        @Override
                        public void onChanged(List<Location> locations) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    location = AppDatabase.getInstance(getContext())
                                            .locationDAO()
                                            .getByID(location_pk);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            toolbar.setTitle(location.getName());
                                            txtHigh1.setText(location.getHigh1());
                                            txtLow1.setText(location.getLow1());
                                            txtPrecip1.setText(location.getPrecip1());
                                            txtDay2.setText(location.getDay2());
                                            txtHigh2.setText(location.getHigh2());
                                            txtLow2.setText(location.getLow2());
                                            txtPrecip2.setText(location.getPrecip2());
                                            txtDay3.setText(location.getDay3());
                                            txtHigh3.setText(location.getHigh3());
                                            txtLow3.setText(location.getLow3());
                                            txtPrecip3.setText(location.getPrecip3());
                                            txtDay4.setText(location.getDay4());
                                            txtHigh4.setText(location.getHigh4());
                                            txtLow4.setText(location.getLow4());
                                            txtPrecip4.setText(location.getPrecip4());
                                            txtDay5.setText(location.getDay5());
                                            txtHigh5.setText(location.getHigh5());
                                            txtLow5.setText(location.getLow5());
                                            txtPrecip5.setText(location.getPrecip5());
                                            txtDay6.setText(location.getDay6());
                                            txtHigh6.setText(location.getHigh6());
                                            txtLow6.setText(location.getLow6());
                                            txtPrecip6.setText(location.getPrecip6());
                                            txtDay7.setText(location.getDay7());
                                            txtHigh7.setText(location.getHigh7());
                                            txtLow7.setText(location.getLow7());
                                            txtPrecip7.setText(location.getPrecip7());
                                        }
                                    });
                                }
                            }).start();
                        }
                    });
        }

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel);
        toolbar.inflateMenu(R.menu.menu_weekly);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_edit) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    LayoutInflater inflater = getLayoutInflater();
                    View v1 = inflater.inflate(R.layout.fragment_rename_dialog, null);
                    EditText txtRename = v1.findViewById(R.id.txtRename);
                    builder.setView(v1)
                            .setTitle("Rename Location")
                            .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    location.setName(txtRename.getText().toString());
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                    AppDatabase.getInstance(getContext())
                                            .locationDAO()
                                            .update(location);
                                        }
                                    }).start();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                    return true;
                } else if (item.getItemId() == R.id.menu_refresh) {
                    weatherDataService.getForecastURL(location.getCoords(), new WeatherDataService.VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            Log.d("API", message);
                        }
                        @Override
                        public void onResponseString(String response) {
                            weatherDataService.getForecast(response, location.getName(), location.getCoords(), new WeatherDataService.VolleyResponseListener() {
                                @Override
                                public void onError(String message) {
                                    Log.d("API", message);
                                }
                                @Override
                                public void onResponseString(String response) {

                                }
                                @Override
                                public void onResponseClass(Location response) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            location.setForecast_today(response.getForecast_today());
                                            location.setPicture_today(response.getPicture_today());
                                            location.setHigh1(response.getHigh1());
                                            location.setLow1(response.getLow1());
                                            location.setPrecip1(response.getPrecip1());
                                            location.setDay2(response.getDay2());
                                            location.setHigh2(response.getHigh2());
                                            location.setLow2(response.getLow2());
                                            location.setPrecip2(response.getPrecip2());
                                            location.setDay3(response.getDay3());
                                            location.setHigh3(response.getHigh3());
                                            location.setLow3(response.getLow3());
                                            location.setPrecip3(response.getPrecip3());
                                            location.setDay4(response.getDay4());
                                            location.setHigh4(response.getHigh4());
                                            location.setLow4(response.getLow4());
                                            location.setPrecip4(response.getPrecip4());
                                            location.setDay5(response.getDay5());
                                            location.setHigh5(response.getHigh5());
                                            location.setLow5(response.getLow5());
                                            location.setPrecip5(response.getPrecip5());
                                            location.setDay6(response.getDay6());
                                            location.setHigh6(response.getHigh6());
                                            location.setLow6(response.getLow6());
                                            location.setPrecip6(response.getPrecip6());
                                            location.setDay7(response.getDay7());
                                            location.setHigh7(response.getHigh7());
                                            location.setLow7(response.getLow7());
                                            location.setPrecip7(response.getPrecip7());
                                            AppDatabase.getInstance(getContext())
                                                    .locationDAO()
                                                    .update(location);
                                        }
                                    }).start();
                                    Toast.makeText(getContext(), "Forecast refreshed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        @Override
                        public void onResponseClass(Location response) {

                        }
                    });
                    return true;
                } else if (item.getItemId() == R.id.menu_delete) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Delete Confirmation")
                            .setMessage("Are you sure you want to delete this location?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            AppDatabase.getInstance(getContext())
                                                    .locationDAO()
                                                    .delete(location);
                                        }
                                    }).start();
                                    Toast.makeText(getContext(), "Location deleted", Toast.LENGTH_SHORT).show();
                                    dismiss();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                    return true;
                }
                return false;
            }
        });
    }
}