package com.omievee.a9to5.MTA_API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by omievee on 5/1/17.
 */

public interface MTA_Interface  {

    @GET ("/status.txt")
    Call<MTA_POJO> getService();
}
