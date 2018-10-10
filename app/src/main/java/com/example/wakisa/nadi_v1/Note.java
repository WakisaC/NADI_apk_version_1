package com.example.wakisa.nadi_v1;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


// serialization means saving the current state of the object
public class Note implements Serializable {

    private long mDateTime;
    private String mTitle;
    private String mContent;

    public Note(long mDateTime, String mTitle, String mContent) {
        this.mDateTime = mDateTime;
        this.mTitle = mTitle;
        this.mContent = mContent;
    }

    public long getmDateTime() {
        return mDateTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmDateTime(long mDateTime) {
        this.mDateTime = mDateTime;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }


    // get the date and time of the user
    public String getDateTimeFormated(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",
                context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(mDateTime));
    }
}
