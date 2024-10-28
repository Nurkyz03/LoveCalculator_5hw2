package com.geeks.retrofit_5hw2

import com.geeks.retrofit_5hw2.room.LoveDao
import com.geeks.retrofit_5hw2.room.LoveEntity
import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class LoveRepository @Inject constructor(
    private val api: LoveApiService,
    private val dao: LoveDao
) {

    private var lovePercentageLv = MutableLiveData<LoveModel>()
    var error = MutableLiveData<String>()
    var flag = MutableLiveData<Boolean>()

    fun getLovePercentage(firstName: String, secondName: String): MutableLiveData<LoveModel> {
        api.fetchPercentage(
            firstName = firstName,
            secondName = secondName
        ).enqueue(object : Callback<LoveModel> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        val historyEntity = it.toEntity()
                        dao.addHistory(historyEntity)
                    }
                    lovePercentageLv.postValue(response.body())
                    flag.postValue(true)
                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                error.postValue(t.message)
                flag.postValue(false)
            }

        })
        return lovePercentageLv
    }
    fun deleteHistory(historyEntity: LoveEntity) {
        dao.deleteHistory(historyEntity)
    }

    fun getHistoryList(): LiveData<List<LoveEntity>> {
        return dao.getHistory()
    }
}