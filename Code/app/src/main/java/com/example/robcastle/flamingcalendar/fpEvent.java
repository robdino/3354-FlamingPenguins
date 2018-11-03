package com.example.robcastle.flamingcalendar;

/**
 * We need to implement the following functions:
 *
 * boolean equalsTo(Event copy) return boolean
 *
 * int compareTo(Event copy) return 1, or 0, or -1 (like a normal compareTo)
 *
 * set(String date, String descrip, String startTime, String endTime) set event, return bool
 *
 * @author Robbie
 * @since 11/01/18
 */

public class fpEvent
{
    /***************VARIABLES********************/
    private String date;
    private String description;
    private String startTime;
    private String endTime;

    /****************CONSTRUCTORS********************/
    public fpEvent(String date, String description, String startTime, String endTime)
    {
        this.date = date;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /*******************FUNCTIONS********************/
    //write the functions here


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
