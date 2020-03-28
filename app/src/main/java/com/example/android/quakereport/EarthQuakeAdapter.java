package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {

    public EarthQuakeAdapter(Activity context, ArrayList<EarthQuake> earthQuakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context,0,earthQuakes);
    }
    private String formattedDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formattedTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h, mm a");
        return timeFormat.format(dateObject);
    }
    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        EarthQuake currentEarthQuake = getItem(position);

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        String formattedMagnitude = formatMagnitude(currentEarthQuake.getmMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);

        Date dateObject = new Date(currentEarthQuake.getmTime());
        String originalLocation = currentEarthQuake.getmLocation();

        if (originalLocation.contains("of")) {
            primaryLocationView.setText(originalLocation.substring(originalLocation.indexOf("f")+2));
            locationOffsetView.setText(originalLocation.substring(originalLocation.indexOf("f")+1));

        } else {
            locationOffsetView.setText("near the");
            primaryLocationView.setText(originalLocation);
        }

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formattedDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate(dateObject));

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formattedTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime(dateObject));


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthQuake.getmMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        return listItemView;
    }

    public int getMagnitudeColor(double magnitude) {
        int magnitude1ColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch(magnitudeFloor) {
            case 0:
            case 1:
                magnitude1ColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitude1ColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitude1ColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitude1ColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitude1ColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitude1ColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitude1ColorResourceId  = R.color.magnitude7;
                break;
            case 8:
                magnitude1ColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitude1ColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitude1ColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitude1ColorResourceId);
    }


}
