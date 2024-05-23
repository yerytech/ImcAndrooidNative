package com.yerytechj.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.yerytechj.imc.IcmActivity.Companion.IMC_KEY

class ResultImcActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnCalculate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()
        setContentView(R.layout.activity_result_imc)
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponets()
        initUI(result)
        navigateToIMCPage()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        tvDescription.text =getString(R.string.tv_Description)
        when (result) {
//            Bajo Peso
            in 0.00..18.50 -> {
                tvResult.text = getString(R.string.bajopeso)

            }
//            Normal Peso
            in 18.51..24.99 -> {
                tvResult.text =getString(R.string.normal)


            }
//             Alto peso
            in 25.00..29.99 -> {
                tvResult.text =getString(R.string.sobrepeso)


            }
//            Obsesidad
            in 30.00..99.00 -> {
                tvResult.text = getString(R.string.obesidad)

            }

            else -> {
                tvResult.text = getString(R.string.error)
                tvIMC.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponets() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvImc)
        tvDescription = findViewById(R.id.tvDescription)
        btnCalculate=findViewById(R.id.btnCalculate)
    }

    private fun navigateToIMCPage(){
        btnCalculate.setOnClickListener {
            val intent= Intent(this,IcmActivity::class.java)
            startActivity(intent)

        }

    }
}