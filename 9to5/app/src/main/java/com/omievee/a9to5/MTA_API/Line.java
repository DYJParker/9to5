package com.omievee.a9to5.MTA_API;

/**
 * Created by omievee on 5/1/17.
 */

public class Line {

    private String text;

    private String Time;

    private String status;

    private String Date;

    private String name;

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getTime ()
    {
        return Time;
    }

    public void setTime (String Time)
    {
        this.Time = Time;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [text = "+text+", Time = "+Time+", status = "+status+", Date = "+Date+", name = "+name+"]";
    }
}
