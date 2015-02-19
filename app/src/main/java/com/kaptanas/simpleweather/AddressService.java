package com.kaptanas.simpleweather;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by cihankaptan on 18/02/15.
 */
public interface AddressService {

    @GET("/json")
    void getAdress(@Query("address") String address,@Query("sensor") boolean sensor);

}
