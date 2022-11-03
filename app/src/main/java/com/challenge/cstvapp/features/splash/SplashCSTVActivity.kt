package com.challenge.cstvapp.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.challenge.cstvapp.R
import com.challenge.cstvapp.features.matches.MatchListActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashCSTVActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        routeToAppropriatePage()
    }

    private fun routeToAppropriatePage() {
        startActivity(Intent(this, MatchListActivity::class.java))
        finish()
    }
}