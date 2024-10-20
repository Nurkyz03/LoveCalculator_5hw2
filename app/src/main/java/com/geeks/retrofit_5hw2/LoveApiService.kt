package com.geeks.retrofit_5hw2

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import com.geeks.retrofit_5hw2.LoveModel

interface LoveApiService {

    // CRUD запрос
    // C - create - post
    // R - read - get
    // U - update - put, post
    // D - delete - delete
    // Https://online.geeks.kg - base url, уник адрес сайта
    // Https://online.geeks.kg/lesson - endpoint/ method

    @GET("getPercentage")
    fun fetchPercentage(
        @Query("fname") firstName: String,
        @Query("sname") secondName: String,
        @Header("x-Rapidapi-Key") key: String,
        @Header("x-Rapidapi-Host") host: String
    ) : retrofit2.Call<LoveModel>
}