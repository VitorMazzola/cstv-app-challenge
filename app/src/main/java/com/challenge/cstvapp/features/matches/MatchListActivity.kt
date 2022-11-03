package com.challenge.cstvapp.features.matches

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.challenge.cstvapp.R
import com.challenge.cstvapp.databinding.ActivityMatchListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchListActivity: AppCompatActivity() {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_matches) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMatchListBinding>(
            this,
            R.layout.activity_match_list
        ).apply {
            lifecycleOwner = this@MatchListActivity
        }

        navController.setGraph(R.navigation.matches_nav_graph)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}