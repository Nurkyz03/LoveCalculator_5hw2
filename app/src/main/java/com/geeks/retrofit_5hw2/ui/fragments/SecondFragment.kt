package com.geeks.retrofit_5hw2.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.geeks.retrofit_5hw2.LoveModel
import com.geeks.retrofit_5hw2.R
import com.geeks.retrofit_5hw2.databinding.FragmentSecondBinding
import com.geeks.retrofit_5hw2.interfaces.ResultContract
import com.geeks.retrofit_5hw2.ui.presenters.ResultPresenter

class SecondFragment : Fragment(), ResultContract.View {

    private lateinit var presenter: ResultContract.Presenter
    private val binding by lazy { FragmentSecondBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ResultPresenter(this)
        fillInTheData()
        initListener()
    }

    private fun fillInTheData() = with(binding) {
        setFragmentResultListener("KEY_ARG") { _, bundle ->
            val result = bundle.getSerializable("data") as? LoveModel
            if (result != null) {
                showData(result)
            } else {
                Log.e("ResultFragment", "No data received or data is null")
            }
        }
    }

    private fun initListener() {
        binding.btnTryAgain.setOnClickListener {
            presenter.onTryAgainClicked()
        }
    }

    override fun showData(loveModel: LoveModel) = with(binding) {
        tvMeResult.text = loveModel.firstName
        tvYouResult.text = loveModel.secondName
        tvProcent.text = loveModel.percentage
        tvResult.text = loveModel.result
    }

    override fun navigateToCalculation() {
        findNavController().navigate(R.id.firstFragment)
    }
}