package com.omievee.a9to5.RecyclerView;

/**
 * Created by Dave - Work on 5/3/2017.
 */

public class InterfaceSingleton {
    private static InterfaceSingleton sInstance;

    private ListUpdateListener mListener;

    private InterfaceSingleton(){}

    public static InterfaceSingleton getInstance(){
        if(sInstance==null) sInstance = new InterfaceSingleton();
        return sInstance;
    }

    interface ListUpdateListener{
        void updateList(AbstractBaseInformationObject obj);
    }

    public void updateList(AbstractBaseInformationObject obj){
        if(mListener!=null) mListener.updateList(obj);
    }

    public void setListener(ListUpdateListener listener) {
        mListener = listener;
    }

    public ListUpdateListener getListener() {
        return mListener;
    }
}
