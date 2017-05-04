package com.omievee.a9to5.RecyclerView;

/**
 * Created by omievee on 5/1/17.
 */

public class Cardinfo extends AbstractBaseInformationObject{

    protected String test1, test2, test3;

    public Cardinfo(String test1, String test2, String test3) {
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;

    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    public String getTest3() {
        return test3;
    }

    public void setTest3(String test3) {
        this.test3 = test3;
    }
}
