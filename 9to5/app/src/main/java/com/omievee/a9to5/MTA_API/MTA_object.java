package com.omievee.a9to5.MTA_API;

import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;

/**
 * Created by omievee on 5/1/17.
 */

public class MTA_object extends AbstractBaseInformationObject {


    private String m123;
    private String m456;
    private String mNQR;
    private String mBDFM;
    private String mL;
    private String m7;
    private String mACE;
    private String mG;
    private String mJZ;
    private String mShuttle;
    private boolean mFailed;


    public String getM123() {
        return m123;
    }


    public String getM456() {
        return m456;
    }

    public String getmNQR() {
        return mNQR;
    }

    public String getmBDFM() {
        return mBDFM;
    }

    public String getmL() {
        return mL;
    }

    public String getM7() {
        return m7;
    }

    public String getmACE() {
        return mACE;
    }

    public String getmG() {
        return mG;
    }

    public String getmJZ() {
        return mJZ;
    }

    public String getmShuttle() {
        return mShuttle;
    }


    public MTA_object(String m123, String m456, String mNQR, String mBDFM, String mL, String m7, String mACE, String mG, String mJZ, String mShuttle) {
        this.m123 = m123;
        this.m456 = m456;
        this.mNQR = mNQR;
        this.mBDFM = mBDFM;
        this.mL = mL;
        this.m7 = m7;
        this.mACE = mACE;
        this.mG = mG;
        this.mJZ = mJZ;
        this.mShuttle = mShuttle;
    }

    public MTA_object(boolean failed) {
        mFailed = failed;
    }

    public boolean getFailed() {
        return mFailed;
    }


}