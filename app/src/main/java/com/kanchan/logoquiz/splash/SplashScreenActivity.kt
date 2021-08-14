package com.kanchan.logoquiz.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kanchan.logoquiz.LogoPuzzleActivity
import com.kanchan.logoquiz.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var splashViewModel : SplashScreeViewModel
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        splashViewModel = ViewModelProvider(this).get(SplashScreeViewModel::class.java)

        splashViewModel.loadQuestionsFromAssets()
        splashViewModel.getLoaded().observe(this, Observer{
            isUpdated -> if (isUpdated) {
               binding.btnStart.visibility = View.VISIBLE
            }
        })
        binding.btnStart.setOnClickListener { startActivity(Intent(this, LogoPuzzleActivity::class.java)) }
    }
}
