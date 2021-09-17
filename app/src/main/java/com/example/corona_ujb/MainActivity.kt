package com.example.corona_ujb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vpAdapter: VPAdapter =
            VPAdapter(supportFragmentManager)
        viewpager.adapter = vpAdapter
    }
}