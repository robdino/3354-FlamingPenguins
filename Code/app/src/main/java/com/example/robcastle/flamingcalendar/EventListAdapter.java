package com.example.robcastle.flamingcalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

/**
 * @author Robbie Castillo
 * @since 11/2/18
 * So this class is here to power our scrolling "Weekly View" feature.
 * In the function getView( . . .), it gets every item in our ArrayList in WeeklyView.java, and displays it
*/
public class EventListAdapter extends ArrayAdapter<fpEvent>
{
    private final String TAG = "EventListAdapter";

    private Context mContext;
    private int mResource;

    public EventListAdapter(Context context, int resource, List<fpEvent> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    /**
     * @author Robbie Castillo
     * @since 11/02/18
     * So this is what powers the Weekly view. We get each item, then slap it into our text
     * in our layout files (textView1 - 4). Then the screen is updated. L I T.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the info
        String date = getItem(position).getDate();
        String description = getItem(position).getDescription();
        String startTime = getItem(position).getStartTime();
        String endTime = getItem(position).getEndTime();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvDate = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.textView4);
        TextView tvStartTime = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvEndTime = (TextView) convertView.findViewById(R.id.textView3);

        tvDate.setText(date);
        tvDescription.setText(description);
        tvStartTime.setText(startTime);
        tvEndTime.setText(endTime);

        return convertView;
    }
}
