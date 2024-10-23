package com.geeks.retrofit_5hw2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.geeks.retrofit_5hw2.LoveApiService
import com.geeks.retrofit_5hw2.R
import com.geeks.retrofit_5hw2.databinding.ActivityMainBinding
import com.geeks.retrofit_5hw2.utils.SharedPreferences
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navController by lazy {
        val navHostController = supportFragmentManager.
        findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navHostController.navController
    }
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var loveApiService: LoveApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (!sharedPreferences.isOnboardingComplete()) {
            sharedPreferences.setOnboardingComplete(true)
        } else {
            navController.navigate(R.id.firstFragment)
        }
    }
}