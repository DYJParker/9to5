package com.omievee.a9to5.MTA_API;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by omievee on 5/1/17.
 */

@Root(name = "line")
public class Line {

    @Element(name = "text", required = false)
    private String text;

    @Element(name = "Time", required = false)
    private String Time;

    @Element(name = "status")
    private String status;

    @Element(name = "Date", required = false)
    private String Date;

    @Element(name = "name", required = false)
    private String name;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassPojo [text = " + text + ", Time = " + Time + ", status = " + status + ", Date = " + Date + ", name = " + name + "]";
    }
}
