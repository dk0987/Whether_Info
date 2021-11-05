package com.devdk.whetherinfo.data.remote

import com.devdk.whetherinfo.data.remote.dto.WhetherDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WhetherAPI {

    @GET("forecast.json")
    suspend fun getDetail(
        @Query("key")key : String ,
        @Query("q")country : String,
        @Query("days")days : Int = 1 ,
        @Query("aqi")aqi : String = "no",
        @Query("alerts")alerts : String = "no"
    ) : WhetherDto

}