package com.example.robcastle.flamingcalendar;

/**
 * We need to implement the following functions:
 *
 * Anthony implemented: boolean equalsTo(Event copy) return boolean
 *
 * Anthony implemented: int compareTo(Event copy) return 1, or 0, or -1 (like a normal compareTo)
 *
 * set(String date, String descrip, String startTime, String endTime) set event, return bool
 *
 * @author Robbie & Anthony (11/08/18)
 * @since 11/01/18
 */

public class fpEvent implements Comparable<fpEvent>
{
    /***************VARIABLES********************/
    private String date;
    private String description;
    private String startTime;
    private String endTime;
   // private String name;

    /****************CONSTRUCTORS********************/
    public fpEvent(String date, String description, String startTime, String endTime) //String name for last parameter?
    {
        this.date = date;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        //this.name = name;
    }

    /*******************FUNCTIONS********************/
    //write the functions here

    public boolean equalsTo(fpEvent anotherEvent) {
        if (this.date.equals(anotherEvent.getDate()) &&
            this.description.equals(anotherEvent.getDescription()) &&
            //this.name.equals(anotherEvent.getName()) &&
            this.startTime.equals(anotherEvent.getStartTime()) &&
            this.endTime.equals(anotherEvent.getEndTime()) ){
            return true;
        }
        else{
            return false;
        }
    }

    public int compareTo(fpEvent anotherEvent) {
        if (getDate().compareTo(anotherEvent.getDate()) == 0){
            if (getStartTime().compareTo(anotherEvent.getStartTime()) < 0){
                return -1;
            }
            else if (getStartTime().compareTo(anotherEvent.getStartTime()) > 0){
                return 1;
            }
            else {
                return 0;
            }
        }
        else if (getDate().compareTo(anotherEvent.getDate()) < 0){
            return -1;
        }
        else if (getDate().compareTo(anotherEvent.getDate()) > 0){
            return 1;
        }
        else{
            return -10;
        }
    }

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
/**
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
*/

}
