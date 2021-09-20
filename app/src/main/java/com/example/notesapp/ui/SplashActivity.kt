package com.example.notesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.window.SplashScreen
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.view.postDelayed
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

      binding.pgSplash.postDelayed(
          2000) {
          startActivity(Intent(this@SplashActivity, MainActivity::class.java))
          finish()
      }


    }


}