package com.geeks.retrofit_5hw2.interfaces

import com.geeks.retrofit_5hw2.LoveModel

interface LoveCalcContract {
    interface View {
        fun showResult(loveModel: LoveModel)
        fun showError(message: String)
        fun navigateToResult()
    }

    interface Presenter {
        fun calculateLovePercentage(firstName: String, secondName: String)
    }
}