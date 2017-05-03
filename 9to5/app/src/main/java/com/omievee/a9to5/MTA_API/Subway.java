package com.omievee.a9to5.MTA_API;

import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import static android.R.attr.name;

/**
 * Created by omievee on 5/1/17.
 */

@Root(name = "subway")
public class Subway {

    @ElementList(name = "line")
    private Line[] line;

    public Line[] getLine() {
        return line;
    }

    public void setLine(Line[] line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "ClassPojo [line = " + line + "]";
    }
}

