package com.example.robcastle.flamingcalendar;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;
import android.app.AlertDialog;

/**
 * This is magic.
 * @author Robbie
 * @since 11/20/18
 */

public class DailyViewAdapter extends RecyclerView.Adapter<DailyViewAdapter.DailyEventHolder>
{
    private static final String TAG = "DailyViewAdapter";

    private ArrayList<fpEvent> dailyEvents;

    /**
     * a constructor to the custom adapter so that it has a handle to the data
     *      that the RecyclerView displays
     * @param dailyEvents
     */
    DailyViewAdapter(ArrayList<fpEvent> dailyEvents){
        this.dailyEvents = dailyEvents;
    }

    @Override
    public int getItemCount() {
        return dailyEvents.size();
    }

    @Override
    /**
     * Creates our recycle view for daily
     */
    public DailyEventHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        DailyEventHolder pvh = new DailyEventHolder(v);
        return pvh;
    }

    @Override
    /**
     * slaps all the info into each card in RecycleView
     */
    public void onBindViewHolder(DailyEventHolder eventLoader, int i) {
        eventLoader.eventDate.setText(dailyEvents.get(i).getDate());
        eventLoader.eventName.setText(dailyEvents.get(i).getName());
        eventLoader.eventDesc.setText(dailyEvents.get(i).getDescription());
        eventLoader.eventStart.setText(dailyEvents.get(i).getStartTime());
        eventLoader.eventEnd.setText(dailyEvents.get(i).getEndTime());
        //eventLoader.reminderSwitch.setChecked(dailyEvents.get(i).getBoolReminder());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Inside the constructor of our custom ViewHolder,
     *      initialize the views that belong to the items of our RecyclerView.
     */
    public static class DailyEventHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView eventDate;
        TextView eventName;
        TextView eventDesc;
        TextView eventStart;
        TextView eventEnd;
        ImageButton goToDeleteButton;
        ImageButton goToEdit;

        DailyEventHolder(final View itemView) {
            super(itemView);
            generateInfo();

            //just delete from SQL records and refresh
            goToDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Log.d(TAG, "DeleteButton, Going to Delete Event");

                    //Confirmation pop-up

                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Are you sure you want to delete this event?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                            deleteEvent(eventName.getText().toString(),
                                    eventDate.getText().toString(),
                                    eventDesc.getText().toString());
                            itemView.getContext().startActivity(new Intent(itemView.getContext(), DailyView.class));
                             }
                          })
                            .setNegativeButton("Cancel", null);
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });

            //display old records, delete old record from sql db, and save new one
            goToEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Log.d(TAG, "EditButton, Going to AddEvent");
                    editEvent(eventName.getText().toString(),
                              eventDate.getText().toString(),
                              eventDesc.getText().toString());
                }
            });

        }

        private void deleteEvent (String name, String date, String desc)
        {
            DatabaseHelper dbHelp = new DatabaseHelper(itemView.getContext());
            dbHelp.deleteRecord(name, date, desc);
        }

        private void editEvent (String name, String date, String desc)
        {
            //deleteEvent(name, date, desc); //we then delete the event
            //send intent data
            Intent toEdit = new Intent(itemView.getContext(), AddEventActivity.class);
            toEdit.putExtra("gettingInfo", true);
            toEdit.putExtra("name", name);
            toEdit.putExtra("date", date);
            toEdit.putExtra("desc", desc);
            itemView.getContext().startActivity(toEdit);

        }

        private void generateInfo()
        {
            cv = (CardView)itemView.findViewById(R.id.cv);

            eventDate   = (TextView)itemView.findViewById(R.id.daily_date);
            eventName   = (TextView)itemView.findViewById(R.id.daily_name);
            eventDesc   = (TextView) itemView.findViewById(R.id.daily_description);
            eventStart  = (TextView)itemView.findViewById(R.id.daily_start);
            eventEnd    = (TextView) itemView.findViewById(R.id.daily_end);

            goToDeleteButton = (ImageButton) itemView.findViewById(R.id.btnDaily_Delete);
            goToEdit = (ImageButton) itemView.findViewById(R.id.btnDaily_EditEvent);
        }
    }
}
