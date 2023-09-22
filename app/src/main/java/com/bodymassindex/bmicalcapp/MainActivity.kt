package com.bodymassindex.bmicalcapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText = findViewById<EditText>(R.id.etWeight)
        val hightText = findViewById<EditText>(R.id.etHeight)

        val calcButton = findViewById<AppCompatButton>(R.id.btnCalculate)
        calcButton.setOnClickListener { v ->
            val weight = weightText.text.toString()
            val height = hightText.text.toString()
            if (validdataInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                val bmi2digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2digits)
            }

        }

    }


    @SuppressLint("ResourceAsColor")
    private fun validdataInput(weight: String?, height: String?): Boolean {
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)

        return when {
            weight.isNullOrEmpty() -> {
                var snackbar: Snackbar =
                    Snackbar.make(constraintLayout, "Weight is empty", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(R.color.layout_background)
                snackbar.setTextColor(R.color.white)
                snackbar.show()
                return false
            }

            height.isNullOrEmpty() -> {
                var snackbar: Snackbar =
                    Snackbar.make(constraintLayout, "Height is empty", Snackbar.LENGTH_SHORT)
                snackbar.show()
                return false
            }

            else -> {
                return true
            }
        }
    }

    @SuppressLint("CutPasteId")
    private fun displayResult(bmi: Float) {
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "(Normal range is 18.5-24.9)"

        var resultText = ""
        var color = 0

        when {
            bmi < 18.50 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }

            bmi in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.normal
            }

            bmi in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.over_weight
            }

            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this@MainActivity, color))
        resultDescription.text = resultText

    }
}