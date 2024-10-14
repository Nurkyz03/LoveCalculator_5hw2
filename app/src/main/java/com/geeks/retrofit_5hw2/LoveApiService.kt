package com.geeks.retrofit_5hw2

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import com.geeks.retrofit_5hw2.LoveModel

interface LoveApiService {

    @GET("getPercentage")
    fun fetchPercentage(
        @Header("X-Rapidapi-Key") key: String,
        @Header("X-Rapidapi-Host") host: String,
        @Query("fname") firstName: String,
        @Query("sname") secondName: String
    ): Call<LoveModel>
}