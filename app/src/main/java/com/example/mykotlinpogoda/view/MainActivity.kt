package com.example.mykotlinpogoda.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mykotlinpogoda.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportFragmentManager.beginTransaction()
//            .add(R.id.main_container, MainFragment.newInstance())
//            .commit()
    }
}