package com.geeks.retrofit_5hw2.ui.presenters

import com.geeks.retrofit_5hw2.LoveModel
import com.geeks.retrofit_5hw2.RetrofitService
import com.geeks.retrofit_5hw2.interfaces.LoveCalcContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoveCalcPresenter(
    private val view: LoveCalcContract.View,
    private val apiService: RetrofitService
) : LoveCalcContract.Presenter {

    override fun calculateLovePercentage(firstName: String, secondName: String) {

        apiService.api.fetchPercentage(
            firstName = firstName,
            secondName = secondName,
            key = "bba0e0acaemsh38f1a7b99023527p1a7a8fjsn26046b35aa96",
            host = "love-calculator.p.rapidapi.com"
        ).enqueue(object : Callback<LoveModel> {
            override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()
                    result?.let { view.showResult(it) }
                    view.navigateToResult()
                } else {
                    view.showError("Error fetching data")
                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                view.showError("Request failed: ${t.message}")
            }
        })
    }
}