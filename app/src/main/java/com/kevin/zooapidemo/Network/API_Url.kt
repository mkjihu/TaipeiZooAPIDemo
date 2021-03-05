package com.kevin.zooapidemo.Network

import com.kevin.zooapidemo.Model.resourceAquire
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.*


interface API_Url {


    companion object {
        var BaseUrl = "https://data.taipei/api/v1/dataset/"
        var Api1 = "https://data.taipei/api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire"
        var Api2 = "https://data.taipei/api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire&q="
    }


    @GET
    fun GetData(@Url url: String): Flowable<resourceAquire>

    /*
    @GET
    fun GetAPI(@Url url: String): Flowable<String>

    @GET("/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    fun GetAPI1(@Query("q") q: String,
                @Query("limit") sort: String,
                @Query("offset") order: String): Flowable<String>

    @GET("/f18de02f-b6c9-47c0-8cda-50efad621c14")
    fun GetAPI2(@Query("q") q: String,
                @Query("limit") sort: String,
                @Query("offset") order: String): Flowable<String>
*/


}