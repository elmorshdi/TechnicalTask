package com.elmorshdi.technicaltask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce: Boolean = false
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_Activity_nav_host_fragment) as NavHostFragment
        navHostFragment.navController

        doubleBackToExitPressedOnce = false
    }
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {

            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, " Please click BACK again to exit ", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)


    }


}