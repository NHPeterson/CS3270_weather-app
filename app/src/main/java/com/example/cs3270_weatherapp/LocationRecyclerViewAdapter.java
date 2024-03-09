package com.example.cs3270_weatherapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs3270_weatherapp.db.Location;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LocationRecyclerViewAdapter extends RecyclerView.Adapter<LocationRecyclerViewAdapter.ViewHolder>{

    public final List<Location> locations;

    public LocationRecyclerViewAdapter(List<Location> locations) {
        this.locations = locations;
    }

    public void addItems(List<Location> locations) {
        this.locations.clear();
        this.locations.addAll(locations);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtLocationName, txtForecastToday;
        private ImageView imgForecastImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            txtLocationName = view.findViewById(R.id.rcyLocationName);
            txtForecastToday = view.findViewById(R.id.rcyForecastToday);
            imgForecastImg = view.findViewById(R.id.rcyForecastImg);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Location location = locations.get(position);
        if(location != null) {
            holder.txtLocationName.setText(location.getName());
            holder.txtForecastToday.setText(location.getForecast_today());
            Picasso.get()
                    .load(location.getPicture_today())
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .into(holder.imgForecastImg);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("location_pk", location.getId());
                    WeeklyForecastFragment weeklyForecastFragment = new WeeklyForecastFragment();
                    weeklyForecastFragment.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .add(android.R.id.content, weeklyForecastFragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }
}
