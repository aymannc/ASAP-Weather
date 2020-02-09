package com.naitcherif.asapweather;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    List<WeatherItem> data = new ArrayList<>();
    private EditText textInput;
    private ListView listViewWeather;
    private WeatherListModel model;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInput = findViewById(R.id.inputCity);
        listViewWeather = findViewById(R.id.weatherListView);
        searchButton = findViewById(R.id.searchButton);
        model = new WeatherListModel(getApplicationContext(), R.layout.list_view_item, data);
        listViewWeather.setAdapter(model);
    }

    public void searchClicked(View view) {
        String cityName = textInput.getText().toString();
        if (cityName.matches("")) {
            Toast.makeText(this, "Please enter a city", Toast.LENGTH_LONG).show();
        } else {
            reset();
            String url = "https://samples.openweathermap.org/data/2.5/forecast?q=" + cityName +
                    "&appid=b6907d289e10d714a6e88b30761fae22";
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            StringRequest request = new StringRequest(Request.Method.GET, url,
                    this::onResponse, this::onErrorResponse);
            queue.add(request);
        }
    }

    private void onResponse(String response) {
        try {
            JSONArray jsonArray = new JSONObject(response).getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject d = jsonArray.getJSONObject(i);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH);
                JSONObject main = d.getJSONObject("main");
                JSONArray weather = d.getJSONArray("weather");
                WeatherItem weatherItem = new WeatherItem(
                        (int) (main.getDouble("temp_max") - 273.15),
                        (int) (main.getDouble("temp_min") - 273.15),
                        main.getInt("pressure"),
                        main.getInt("humidity"),
                        weather.getJSONObject(0).getString("main"),
                        sdf.format(new Date(d.getLong("dt") * 1000))
                );
                data.add(0,weatherItem);
            }
            model.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(data);
    }

    private void onErrorResponse(VolleyError error) {
        System.out.println(error.toString());
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }

    private void reset() {
        data.clear();
        model.notifyDataSetChanged();
        textInput.setText("");
        hideSoftKeyBoard();
    }

    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if (imm.isAcceptingText()) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
