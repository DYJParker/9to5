package com.omievee.a9to5.MTA_API;

/**
 * Created by omievee on 5/1/17.
 */

public class Subway {
    private Line[] line;

    public Line[] getLine ()
    {
        return line;
    }

    public void setLine (Line[] line)
    {
        this.line = line;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [line = "+line+"]";
    }
}

