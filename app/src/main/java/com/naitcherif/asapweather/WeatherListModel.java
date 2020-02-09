package com.naitcherif.asapweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WeatherListModel extends ArrayAdapter<WeatherItem> {
    public static Map<String, Integer> images = new HashMap<>();

    static {
        images.put("Clear", R.drawable.clear);
        images.put("Clouds", R.drawable.clouds);
        images.put("Rain", R.drawable.rain);
        images.put("Thunderstorm", R.drawable.thunderstorm);
    }

    private List<WeatherItem> listItems;
    private int resource;

    WeatherListModel(@NonNull Context context, int resource, @NonNull List<WeatherItem> data) {
        super(context, resource, data);
        this.resource = resource;
        this.listItems = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            System.out.println("null");
            listItem = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }

        ImageView imageView = listItem.findViewById(R.id.weatherImage);
        TextView textViewTempMax = listItem.findViewById(R.id.max);
        TextView textViewTempMin = listItem.findViewById(R.id.min);
        TextView textViewPresser = listItem.findViewById(R.id.presser);
        TextView textViewHumidity = listItem.findViewById(R.id.humidty);
        TextView textViewDate = listItem.findViewById(R.id.timendate);
        String key = listItems.get(position).getImage();
        if (key != null)
            imageView.setImageResource(images.get(key));
        String holder = listItems.get(position).getTempMax() + " °C";
        textViewTempMax.setText(holder);
        holder = listItems.get(position).getTempMin() + " °C";
        textViewTempMin.setText(holder);
        holder = listItems.get(position).getPresser() + " hPa";
        textViewPresser.setText(holder);
        holder = listItems.get(position).getHumidity() + " %";
        textViewHumidity.setText(holder);
        textViewDate.setText(String.valueOf(listItems.get(position).getDate()));
        return listItem;
    }
}
