package com.example.cs3270_weatherapp;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cs3270_weatherapp.db.AppDatabase;
import com.example.cs3270_weatherapp.db.Location;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cs3270_weatherapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FragmentManager fm;
    WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .add(android.R.id.content, new AddLocationFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh_all) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Location> locationList = AppDatabase.getInstance(MainActivity.this).locationDAO().getLocations();
                    for (Location location : locationList) {
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
                                                AppDatabase.getInstance(MainActivity.this)
                                                        .locationDAO()
                                                        .update(location);
                                            }
                                        }).start();
                                        Toast.makeText(MainActivity.this, "All forecasts refreshed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            @Override
                            public void onResponseClass(Location response) {

                            }
                        });
                    }
                }
            }).start();
            return true;
        } else if (item.getItemId() == R.id.menu_delete_all) {
            DialogFragment dialog = new DeleteConfirmDialog();
            dialog.show(fm, "delete all");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}