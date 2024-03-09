package com.example.cs3270_weatherapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs3270_weatherapp.db.Authorization;
import com.example.cs3270_weatherapp.db.Location;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class BingDataService {

    private Context context;
    private String encodedQuery, bingName, bingCoords;

    public BingDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponseString(String name, String coords);
    }

    public void getGeocode(String search, VolleyResponseListener vrl) {
        try {
            encodedQuery = URLEncoder.encode(search, "UTF-8");
        } catch (Exception err) {
            Log.d("API", "getGeocode error encoding URL");
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://dev.virtualearth.net/REST/v1/Locations?q=" + encodedQuery + "&maxRes=1&key=" + Authorization.BING_TOKEN,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    bingName = response
                            .getJSONArray("resourceSets")
                            .getJSONObject(0)
                            .getJSONArray("resources")
                            .getJSONObject(0)
                            .getString("name");
                    JSONArray coordinates = response
                            .getJSONArray("resourceSets")
                            .getJSONObject(0)
                            .getJSONArray("resources")
                            .getJSONObject(0)
                            .getJSONArray("geocodePoints")
                            .getJSONObject(0)
                            .getJSONArray("coordinates");
                    bingCoords = coordinates.getString(0) + "," + coordinates.getString(1);
                } catch (Exception e) {
                    Log.d("API", e.toString());
                }
                vrl.onResponseString(bingName, bingCoords);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("API", error.toString());
                vrl.onError(error.toString());
            }
        });
        WeatherAPISingleton.getInstance(context).addToRequestQueue(request);
    }
}
