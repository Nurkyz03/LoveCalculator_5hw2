package com.geeks.retrofit_5hw2.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.geeks.retrofit_5hw2.LoveModel
import com.geeks.retrofit_5hw2.R
import com.geeks.retrofit_5hw2.RetrofitService
import com.geeks.retrofit_5hw2.databinding.FragmentFirstBinding
import com.geeks.retrofit_5hw2.interfaces.LoveCalcContract
import com.geeks.retrofit_5hw2.ui.presenters.LoveCalcPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment(), LoveCalcContract.View {

    private lateinit var presenter: LoveCalcContract.Presenter
    private val binding by lazy { FragmentFirstBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = LoveCalcPresenter(this, RetrofitService)
        initListener()
    }

    private fun initListener() = with(binding) {
        btnCalc.setOnClickListener {
            val firstName = etFname.text.toString()
            val secondName = etSname.text.toString()
            presenter.calculateLovePercentage(firstName, secondName)
        }
    }

    override fun showResult(loveModel: LoveModel) {
        setFragmentResult(
            "KEY_ARG", bundleOf("data" to loveModel)
        )
    }

    override fun navigateToResult() {
        findNavController().navigate(R.id.secondFragment)
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}