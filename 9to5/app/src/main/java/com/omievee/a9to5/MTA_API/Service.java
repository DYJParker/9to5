package com.omievee.a9to5.MTA_API;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import static android.R.attr.name;

/**
 * Created by omievee on 5/1/17.
 */

@Root(name = "service", strict = false)
public class Service {
    @Element(name = "timestamp")
    private String timestamp;

    @ElementList(name = "subway")
    private List<Line> subway;

    @Element(name = "responsecode")
    private String responsecode;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<Line> getSubway() {
        return subway;
    }

    public void setSubway(List<Line> subway) {
        this.subway = subway;
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    @Override
    public String toString() {
        return "ClassPojo [timestamp = " + timestamp + " subway = " + subway + " responsecode = " + responsecode + "]";
    }
}


