package com.example.android.sunshine.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sunshine.R;


public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>{

    private String[] mWeatherData;

    public ForecastAdapter() {}

    public void setWeatherData(String[] weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }

    @Override
    public ForecastAdapter.ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context parentContext = parent.getContext();
        View listItemView = LayoutInflater
                                .from(parentContext)
                                .inflate(R.layout.forecast_list_item, parent, false);

        ForecastAdapterViewHolder forecastViewHolder = new ForecastAdapterViewHolder(listItemView);

        return forecastViewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastAdapter.ForecastAdapterViewHolder holder, int position) {
        holder.bind(mWeatherData[position]);
    }

    @Override
    public int getItemCount() {
        if (mWeatherData == null) {
            return 0;
        } else {
            return mWeatherData.length;
        }
    }



    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView mWeatherTextView;

        public ForecastAdapterViewHolder(View view) {
            super(view);

            mWeatherTextView = (TextView) view.findViewById(R.id.tv_weather_data);

        }

        public void bind(String text) {
            mWeatherTextView.setText(text);
        }
    }
}
