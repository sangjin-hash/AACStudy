package com.example.toyapp.screen.crud

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.toyapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NaviActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navi)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)

        /*
        //상단의 App bar에서, menu에서 선택한 해당 Fragment의 이름이 뜨게 설정하기.
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.insertFragment, R.id.deleteFragment, R.id.updateFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        */


        bottomNavigationView.setupWithNavController(navController)
    }
}