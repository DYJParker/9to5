package com.omievee.a9to5.MTA_API;

/**
 * Created by omievee on 5/1/17.
 */

public class Service {

        private String timestamp;


        private Subway subway;




        private String responsecode;

        public String getTimestamp ()
        {
            return timestamp;
        }

        public void setTimestamp (String timestamp)
        {
            this.timestamp = timestamp;
        }



        public Subway getSubway ()
        {
            return subway;
        }

        public void setSubway (Subway subway)
        {
            this.subway = subway;
        }
//
//        public Bus getBus ()
//        {
//            return bus;
//        }
//
//        public void setBus (Bus bus)
//        {
//            this.bus = bus;
//        }


        public String getResponsecode ()
        {
            return responsecode;
        }

        public void setResponsecode (String responsecode)
        {
            this.responsecode = responsecode;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [timestamp = "+timestamp+ " subway = " +subway+ " responsecode = " + responsecode + "]";
        }
    }


