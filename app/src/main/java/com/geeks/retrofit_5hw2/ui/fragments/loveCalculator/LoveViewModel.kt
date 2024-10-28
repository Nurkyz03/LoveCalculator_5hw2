package com.geeks.retrofit_5hw2.ui.fragments.loveCalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geeks.retrofit_5hw2.LoveModel
import com.geeks.retrofit_5hw2.LoveRepository
import com.geeks.retrofit_5hw2.room.LoveEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class LoveViewModel @Inject constructor(private val repository: LoveRepository): ViewModel() {

    val error: LiveData<String> = repository.error
    val flag: LiveData<Boolean> = repository.flag
    val historyList: LiveData<List<LoveEntity>> = repository.getHistoryList()
    fun getLovePercentage(firstName: String, secondName: String): LiveData<LoveModel> {
        return repository.getLovePercentage(firstName, secondName)
    }

    fun deleteHistory(historyEntity: LoveEntity) {
        repository.deleteHistory(historyEntity)
    }
}