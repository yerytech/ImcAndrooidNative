package com.yerytechj.imc

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider


class IcmActivity : AppCompatActivity() {
    private var weight: Int = 60
    private var age: Int = 25
    private var height: Int = 120
    private var isSelected: Boolean = true
    private lateinit var viewWoman: CardView
    private lateinit var viewMan: CardView
    private lateinit var tvHeight: TextView
    private lateinit var tvWeightNumber: TextView
    private lateinit var tvAgeNumber: TextView
    private lateinit var rsHeightSlider: RangeSlider
    private lateinit var btnMinusWei: FloatingActionButton
    private lateinit var btnPlusWei: FloatingActionButton
    private lateinit var btnMinusAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnCalculate: Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()

        setContentView(R.layout.activity_icm)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets


//        }
//        initUI()
        initComponents()
        initListener()
    }


    private fun initComponents() {
        tvHeight = findViewById(R.id.tvHeight)
        viewWoman = findViewById(R.id.viewWoman)
        viewMan = findViewById(R.id.viewMan)
        tvWeightNumber = findViewById(R.id.tvWeightNumber)
        tvAgeNumber = findViewById(R.id.tvAgeNumber)
        rsHeightSlider = findViewById(R.id.rsHeightSlider)
        btnMinusWei = findViewById(R.id.btnMinusWei)
        btnPlusWei = findViewById(R.id.btnPlusWei)
        btnMinusAge = findViewById(R.id.btnMinusAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    @SuppressLint("SetTextI18n")
    private fun initListener() {
        viewMan.setOnClickListener { setGenderColor() }
        viewWoman.setOnClickListener { setGenderColor() }
        rsHeightSlider.addOnChangeListener { _, value, _ ->

            val df = DecimalFormat("#.##")
            height = df.format(value).toInt()
            tvHeight.text = "$height cm"
        }
        btnPlusWei.setOnClickListener { plusWeight() }
        btnMinusWei.setOnClickListener { minusWeight() }
        btnPlusAge.setOnClickListener { plusAge() }
        btnMinusAge.setOnClickListener { minusAge() }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)

        }

    }

    private fun navigateToResult(result: Double?) {
        val intent = Intent(this, ResultImcActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun setGenderColor() {
        if (isSelected) {
            viewMan.setCardBackgroundColor(
                ContextCompat.getColor(
                    this, R.color.backgroundItemSelected
                )
            )
            viewWoman.setCardBackgroundColor(ContextCompat.getColor(this, R.color.backgroundItem))
            isSelected = false
        } else {
            viewWoman.setCardBackgroundColor(
                ContextCompat.getColor(
                    this, R.color.backgroundItemSelected
                )
            )
            viewMan.setCardBackgroundColor(ContextCompat.getColor(this, R.color.backgroundItem))
            isSelected = true
        }
    }

    private fun plusWeight() {
        weight++
        tvWeightNumber.text = "$weight"

    }

    private fun minusWeight() {
        if (weight >= 1) {
            weight--
        }
        tvWeightNumber.text = "$weight"

    }

    private fun plusAge() {

        age++
        tvAgeNumber.text = "$age"


    }

    private fun minusAge() {
        if (age >= 1) {

            age--
            tvAgeNumber.text = "$age"
        }

    }

    private fun calculateIMC(): Double? {
        if (weight != 0 && height != 0) {
            val df = DecimalFormat("#.##")
            val imc = weight / (height.toDouble() / 100 * height.toDouble() / 100)
            return df.format(imc).toDouble()
        }
        return null
    }


}