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
 * :: Geoffrey implemented String getIntToStrReminder(int intRemind), getReminder(), setReminder(int reminder),
 *    getBoolReminder() /// reminder components
 *
 * @author Robbie & Anthony (11/08/18) :: Geoffrey (12/07/18)
 * @since 11/01/18
 */

public class fpEvent implements Comparable<fpEvent>
{
    /***************VARIABLES********************/
    private String date;
    private String description;
    private String startTime;
    private String endTime;
    private String name;
    private int IDnum;  //ID integer assigned by the database
    private String strReminder;
    private int reminder;

    /****************CONSTRUCTORS********************/
    public fpEvent() {

    }
    public fpEvent(String date, String description, String startTime, String endTime, String name, int reminder)
    {
        this.date = date;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.IDnum = -1;    //-1 Means there is no ID num
        this.reminder = reminder;
    }

    /*******************FUNCTIONS********************/
    //write the functions here

    public boolean equalsTo(fpEvent anotherEvent) {
        if (this.date.equals(anotherEvent.getDate()) &&
            this.description.equals(anotherEvent.getDescription()) &&
            this.name.equals(anotherEvent.getName()) &&
            this.startTime.equals(anotherEvent.getStartTime()) &&
            this.endTime.equals(anotherEvent.getEndTime())) /* &&
            getIntToStrReminder(this.reminder).equals(anotherEvent.getIntToStrReminder(anotherEvent.getReminder())) )*/{
            return true;
        }
        else{
            return false;
        }
    }

    public int compareTo(fpEvent anotherEvent) {

        if (anotherEvent == null)
            return 0; //to not do anything with the null

        String yearStr, yearAnotherStr, monthStr, monthAnotherStr, dayStr, dayAnotherStr;
        int year, yearAnother, month, monthAnother, day, dayAnother, reminder, otherReminder;

        yearStr = getDate().substring(getDate().lastIndexOf("/") + 1);
        year = Integer.parseInt(yearStr);
        monthStr = getDate().substring(0, getDate().indexOf('/'));
        month = Integer.parseInt(monthStr);
        dayStr = getDate().substring(getDate().indexOf('/') + 1 , getDate().lastIndexOf("/"));
        day = Integer.parseInt(dayStr);

        yearAnotherStr = anotherEvent.getDate().substring(anotherEvent.getDate().lastIndexOf("/") + 1);
        yearAnother = Integer.parseInt(yearAnotherStr);
        monthAnotherStr = anotherEvent.getDate().substring(0, anotherEvent.getDate().indexOf('/'));
        monthAnother = Integer.parseInt(monthAnotherStr);
        dayAnotherStr = anotherEvent.getDate().substring(anotherEvent.getDate().indexOf('/') + 1, anotherEvent.getDate().lastIndexOf("/"));
        dayAnother = Integer.parseInt(dayAnotherStr);

        if (year < yearAnother){
            return -1;
        }
        else if (year > yearAnother){
            return 1;
        }
        else{ //if year and yearAnother is the same.
            if (month < monthAnother){
                return -1;
            }
            else if (month > monthAnother){
                return 1;
            }
            else {
                if (day < dayAnother){
                    return -1;
                }
                else if (day > dayAnother){
                    return 1;
                }
                else{
                    if (getStartTime().compareTo(anotherEvent.getStartTime()) < 0){
                        return -1;
                    }
                    else if (getStartTime().compareTo(anotherEvent.getStartTime()) > 0){
                        return -1;
                    }
                    else{
                        return 0;
                    }
                }
            }
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIDnum() { return IDnum; }

    /**
     * @author Robbie
     * NEVER EVER use this method. Like, EVER.
     * @param IDnum
     */
    protected void setIDnum (int IDnum) { this.IDnum = IDnum; }

    public String getIntToStrReminder(int intRemind) {
        this.reminder = intRemind;
        strReminder = Integer.toString(intRemind);
        return strReminder;
    }

    protected void setReminder (int reminder) { this.reminder = reminder; }

    public boolean getBoolReminder() {

        if(this.reminder == 1) { return true; }
        else { return false; }
    }
    public int getReminder() { return this.reminder; }
}
