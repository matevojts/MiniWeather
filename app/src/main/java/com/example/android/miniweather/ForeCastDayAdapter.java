package com.example.android.miniweather;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by matev on 2017. 10. 13..
 */

public class ForeCastDayAdapter extends RecyclerView.Adapter<ForeCastDayAdapter.ViewHolder> {

    private Forecast forecasts;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        //TODO: egybe a textview deklaralas

        public ImageView weatherConditionImageView;
        public TextView dateTextView;
        public TextView sunRiseTextView;
        public TextView maxTempTextView;
        public TextView sunSetTextView;
        public TextView minTempTextView;

        public ViewHolder(View view) {
            super(view);
            dateTextView = view.findViewById(R.id.date_text_view);
            sunRiseTextView = view.findViewById(R.id.sunrise_text_view);
        }

    }

    public ForeCastDayAdapter(Forecast forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItemView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

            ViewHolder viewHolder = new ViewHolder(listItemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Forecastday actualForecastday = forecasts.getForecastday().get(position);
        holder.dateTextView.setText(actualForecastday.getDate());
        holder.sunRiseTextView.setText(actualForecastday.getAstro().getSunrise());
    }

    @Override
    public int getItemCount() {

        return 10;
        //return forecasts.getForecastday().size();
    }

    /*

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ...
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
*/

}
