package com.geeks.retrofit_5hw2.interfaces

import com.geeks.retrofit_5hw2.LoveModel

interface ResultContract {
    interface View {
        fun showData(loveModel: LoveModel)
        fun navigateToCalculation()
    }

    interface Presenter {
        fun onDataReceived(model: LoveModel)
        fun onTryAgainClicked()
    }
}