package com.geeks.retrofit_5hw2.ui.presenters

import com.geeks.retrofit_5hw2.LoveModel
import com.geeks.retrofit_5hw2.interfaces.ResultContract

class ResultPresenter(private val view: ResultContract.View) : ResultContract.Presenter {

    override fun onDataReceived(model: LoveModel) {
        view.showData(model)
    }

    override fun onTryAgainClicked() {
        view.navigateToCalculation()
    }
}