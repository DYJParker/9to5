package com.omievee.a9to5.MTA_API;

/**
 * Created by omievee on 5/1/17.
 */

public class MTA_POJO {

    private Service service;

    public Service getService ()
    {
        return service;
    }

    public void setService (Service service)
    {
        this.service = service;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [service = "+service+"]";
    }
}


