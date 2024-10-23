package com.geeks.retrofit_5hw2.ui.fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.geeks.retrofit_5hw2.R
import com.geeks.retrofit_5hw2.adapter.OnBoardViewPagerAdapter
import com.geeks.retrofit_5hw2.databinding.FragmentOnBoardBinding
import com.geeks.retrofit_5hw2.utils.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardFragment : Fragment() {

    private val binding by lazy {
        FragmentOnBoardBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return (binding.root)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }
    private fun initialize() {
        val viewPager2 = binding.viewPager2
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this)
    }

    private fun setupListener() = with(binding.viewPager2) {
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 3) {
                    binding.startButton.visibility = View.VISIBLE
                } else {
                    binding.startButton.visibility = View.INVISIBLE

                }
            }
        })
        binding.startButton.setOnClickListener {
            if (currentItem < 4) {
                setCurrentItem(currentItem + 3)
            }
        }
        binding.startButton.setOnClickListener{
            sharedPreferences.setOnboardingComplete(true)
            findNavController().navigate(R.id.action_onBoardFragment_to_firstFragment)
        }
    }
}