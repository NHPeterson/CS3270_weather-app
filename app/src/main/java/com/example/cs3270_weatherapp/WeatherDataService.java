package com.example.cs3270_weatherapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs3270_weatherapp.db.Location;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WeatherDataService {

    public static final String url = "https://api.weather.gov/points/";

    private Context context;
    private String forecastURL;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponseString(String response);
        void onResponseClass(Location response);
    }

    public void getForecastURL(String coords, VolleyResponseListener vrl) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url + coords, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject properties = response.getJSONObject("properties");
                    forecastURL = properties.getString("forecast");
                } catch (Exception e) {
                    Log.d("API", e.toString());
                }
                vrl.onResponseString(forecastURL);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("API", error.toString());
                vrl.onError(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("User-Agent", "Nathan Peterson, petersonnh@gmail.com");
                return params;
            }
        };
        WeatherAPISingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getForecast(String url2, String name, String coords, VolleyResponseListener vrl) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject properties = response.getJSONObject("properties");
                    JSONArray periods = properties.getJSONArray("periods");
                    if (periods.getJSONObject(0).getString("name").equals("Tonight")) {
                        Location location = new Location(name, coords,
                                periods.getJSONObject(0).getString("detailedForecast"),
                                periods.getJSONObject(0).getString("icon"),
                                periods.getJSONObject(0).getString("temperature") + "°" + periods.getJSONObject(0).getString("temperatureUnit"),
                                periods.getJSONObject(0).getString("temperature") + "°" + periods.getJSONObject(0).getString("temperatureUnit"),
                                periods.getJSONObject(0).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(0).getJSONObject("probabilityOfPrecipitation").getString("value") + "%",
                                periods.getJSONObject(1).getString("name"),
                                periods.getJSONObject(1).getString("temperature") + "°" + periods.getJSONObject(1).getString("temperatureUnit"),
                                periods.getJSONObject(2).getString("temperature") + "°" + periods.getJSONObject(2).getString("temperatureUnit"),
                                periods.getJSONObject(1).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(1).getJSONObject("probabilityOfPrecipitation").getString("value") + "%",
                                periods.getJSONObject(3).getString("name"),
                                periods.getJSONObject(3).getString("temperature") + "°" + periods.getJSONObject(3).getString("temperatureUnit"),
                                periods.getJSONObject(4).getString("temperature") + "°" + periods.getJSONObject(4).getString("temperatureUnit"),
                                periods.getJSONObject(3).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(3).getJSONObject("probabilityOfPrecipitation").getString("value") + "%",
                                periods.getJSONObject(5).getString("name"),
                                periods.getJSONObject(5).getString("temperature") + "°" + periods.getJSONObject(5).getString("temperatureUnit"),
                                periods.getJSONObject(6).getString("temperature") + "°" + periods.getJSONObject(6).getString("temperatureUnit"),
                                periods.getJSONObject(5).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(5).getJSONObject("probabilityOfPrecipitation").getString("value") + "%",
                                periods.getJSONObject(7).getString("name"),
                                periods.getJSONObject(7).getString("temperature") + "°" + periods.getJSONObject(7).getString("temperatureUnit"),
                                periods.getJSONObject(8).getString("temperature") + "°" + periods.getJSONObject(8).getString("temperatureUnit"),
                                periods.getJSONObject(7).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(7).getJSONObject("probabilityOfPrecipitation").getString("value") + "%",
                                periods.getJSONObject(9).getString("name"),
                                periods.getJSONObject(9).getString("temperature") + "°" + periods.getJSONObject(9).getString("temperatureUnit"),
                                periods.getJSONObject(10).getString("temperature") + "°" + periods.getJSONObject(10).getString("temperatureUnit"),
                                periods.getJSONObject(9).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(9).getJSONObject("probabilityOfPrecipitation").getString("value") + "%",
                                periods.getJSONObject(11).getString("name"),
                                periods.getJSONObject(11).getString("temperature") + "°" + periods.getJSONObject(11).getString("temperatureUnit"),
                                periods.getJSONObject(12).getString("temperature") + "°" + periods.getJSONObject(12).getString("temperatureUnit"),
                                periods.getJSONObject(11).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(11).getJSONObject("probabilityOfPrecipitation").getString("value") + "%");
                        vrl.onResponseClass(location);
                    } else {
                        Location location = new Location(name, coords,
                                periods.getJSONObject(0).getString("detailedForecast"),
                                periods.getJSONObject(0).getString("icon"),
                                periods.getJSONObject(0).getString("temperature") + "°" + periods.getJSONObject(0).getString("temperatureUnit"),
                                periods.getJSONObject(1).getString("temperature") + "°" + periods.getJSONObject(1).getString("temperatureUnit"),
                                periods.getJSONObject(0).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(0).getJSONObject("probabilityOfPrecipitation").getString("value") + "%",
                                periods.getJSONObject(2).getString("name"),
                                periods.getJSONObject(2).getString("temperature") + "°" + periods.getJSONObject(2).getString("temperatureUnit"),
                                periods.getJSONObject(3).getString("temperature") + "°" + periods.getJSONObject(3).getString("temperatureUnit"),
                                periods.getJSONObject(2).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(2).getJSONObject("probabilityOfPrecipitation").getString("value") + "%",
                                periods.getJSONObject(4).getString("name"),
                                periods.getJSONObject(4).getString("temperature") + "°" + periods.getJSONObject(4).getString("temperatureUnit"),
                                periods.getJSONObject(5).getString("temperature") + "°" + periods.getJSONObject(5).getString("temperatureUnit"),
                                periods.getJSONObject(4).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(4).getJSONObject("probabilityOfPrecipitation").getString("value") + "%",
                                periods.getJSONObject(6).getString("name"),
                                periods.getJSONObject(6).getString("temperature") + "°" + periods.getJSONObject(6).getString("temperatureUnit"),
                                periods.getJSONObject(7).getString("temperature") + "°" + periods.getJSONObject(7).getString("temperatureUnit"),
                                periods.getJSONObject(6).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(6).getJSONObject("probabilityOfPrecipitation").getString("value") + "%",
                                periods.getJSONObject(8).getString("name"),
                                periods.getJSONObject(8).getString("temperature") + "°" + periods.getJSONObject(8).getString("temperatureUnit"),
                                periods.getJSONObject(9).getString("temperature") + "°" + periods.getJSONObject(9).getString("temperatureUnit"),
                                periods.getJSONObject(8).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(8).getJSONObject("probabilityOfPrecipitation").getString("value") + "%",
                                periods.getJSONObject(10).getString("name"),
                                periods.getJSONObject(10).getString("temperature") + "°" + periods.getJSONObject(10).getString("temperatureUnit"),
                                periods.getJSONObject(11).getString("temperature") + "°" + periods.getJSONObject(11).getString("temperatureUnit"),
                                periods.getJSONObject(10).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(10).getJSONObject("probabilityOfPrecipitation").getString("value") + "%",
                                periods.getJSONObject(12).getString("name"),
                                periods.getJSONObject(12).getString("temperature") + "°" + periods.getJSONObject(12).getString("temperatureUnit"),
                                periods.getJSONObject(13).getString("temperature") + "°" + periods.getJSONObject(13).getString("temperatureUnit"),
                                periods.getJSONObject(12).getJSONObject("probabilityOfPrecipitation").isNull("value") ? "0%" : periods.getJSONObject(12).getJSONObject("probabilityOfPrecipitation").getString("value") + "%");
                        vrl.onResponseClass(location);
                    }
                } catch (Exception e) {
                    Log.d("API", "getForecast error building location");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("API", "getForecast error on request");
                vrl.onError(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("User-Agent", "Nathan Peterson, petersonnh@gmail.com");
                return params;
            }
        };
        WeatherAPISingleton.getInstance(context).addToRequestQueue(request);
    }
}
