package com.yerytechj.imc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider


class IcmActivity : AppCompatActivity() {
    val viewMan: CardView = findViewById(R.id.viewMan)
    val viewWoman: CardView = findViewById(R.id.viewWoman)
    val tvHeight: TextView = findViewById(R.id.tvHeight)
    val tvWeightNumber: TextView = findViewById(R.id.tvWeightNumber)
    val tvAgeNumber: TextView = findViewById(R.id.tvAgeNumber)
    val rsHeightSlider: RangeSlider = findViewById(R.id.rsHeightSlider)
    val btnMinusWei:FloatingActionButton=findViewById(R.id.btnMinusWei)
    val bntPlusWei:FloatingActionButton=findViewById(R.id.btnPlusWei)
    val bntMinusAge:FloatingActionButton=findViewById(R.id.btnMinusAge)
    val btnPlusAge:FloatingActionButton=findViewById(R.id.btnPlusAge)
    val btnCalculate:Button=findViewById(R.id.btnCalculate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()

        setContentView(R.layout.activity_icm)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }
}