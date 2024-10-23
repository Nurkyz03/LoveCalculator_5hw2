package com.geeks.retrofit_5hw2.di

import android.content.Context
import android.content.SharedPreferences
import com.geeks.retrofit_5hw2.LoveApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule  {

    @Provides
    fun provideOnBoardPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("shared",Context.MODE_PRIVATE)
    }

    @Provides
    fun provideRetrofitService(): LoveApiService {
        return Retrofit.Builder().baseUrl("https://love-calculator.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(LoveApiService::class.java)
    }
}