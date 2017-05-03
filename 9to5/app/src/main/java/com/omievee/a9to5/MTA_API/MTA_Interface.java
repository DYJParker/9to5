package com.omievee.a9to5.MTA_API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by omievee on 5/1/17.
 */

public interface MTA_Interface  {


    @Headers("User-Agent: Mozilla/5.0 (Linux; Android 7.1.2; Nexus 6P Build/N2G47H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.132 Mobile Safari/537.36")


    @GET ("serviceStatus.txt")
    Call<MTA_POJO> getService();
}
