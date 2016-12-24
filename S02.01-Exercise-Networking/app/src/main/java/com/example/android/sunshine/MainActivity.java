/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.sunshine.utilities.NetworkUtils;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mWeatherTextView;
    private EditText mWeatherLocationEditText;
    private TextView mWeatherLocationURL;
    private TextView mWeatherLocationError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);
        mWeatherLocationEditText = (EditText) findViewById(R.id.weather_location_search);
        mWeatherLocationURL = (TextView) findViewById(R.id.weather_forcast_url);
        mWeatherLocationError = (TextView) findViewById(R.id.weather_error);

        String location = "Ann Arbor, MI";
        loadWeatherData(location);

    }

    // TODO (8) Create a method that will get the user's preferred location and execute your new AsyncTask and call it loadWeatherData

    private void loadWeatherData(String location) {
        URL weatherUrl = NetworkUtils.buildUrl(location);

        mWeatherLocationURL.setText(weatherUrl.toString());

        FetchWeatherTask weatherTask = new FetchWeatherTask();
        weatherTask.execute(weatherUrl);

    }

    private void showWeatherData() {
        mWeatherTextView.setVisibility(View.VISIBLE);
        mWeatherLocationError.setVisibility(View.INVISIBLE);
    }

    private void showError() {
        mWeatherTextView.setVisibility(View.INVISIBLE);
        mWeatherLocationError.setVisibility(View.VISIBLE);
    }

    public class FetchWeatherTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];

            String weatherData = null;
            try {
                weatherData = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return weatherData;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null && !s.isEmpty()) {
                showWeatherData();
                mWeatherTextView.setText(s);
            } else {
                showError();
            }
        }
    }

    // TODO (5) Create a class that extends AsyncTask to perform network requests
    // TODO (6) Override the doInBackground method to perform your network requests
    // TODO (7) Override the onPostExecute method to display the results of the network request
}