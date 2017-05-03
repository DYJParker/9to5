package com.omievee.a9to5.MTA_API;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import static android.R.attr.name;

/**
 * Created by omievee on 5/1/17.
 */

@Root(name = "MTA_POJO", strict = false)
public class MTA_POJO {

    @Element(name = "service")
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "ClassPojo [service = " + service + "]";
    }
}


