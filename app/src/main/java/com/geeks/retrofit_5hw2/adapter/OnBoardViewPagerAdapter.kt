package com.geeks.retrofit_5hw2.adapter

import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geeks.retrofit_5hw2.ui.fragments.onBoard.OnBoardFragment
import com.geeks.retrofit_5hw2.ui.fragments.onBoard.OnBoardPagingFragment
import com.geeks.retrofit_5hw2.ui.fragments.onBoard.OnBoardPagingFragment.Companion.ARG_ONBOARD_POSITION

class OnBoardViewPagerAdapter(fragment: OnBoardFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): OnBoardPagingFragment {
        val fragment = OnBoardPagingFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_ONBOARD_POSITION, position)
        }
        return fragment
    }
}