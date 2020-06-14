package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

fun Double.roundToNDecimalPlaces(n: Int) = BigDecimal(this).setScale(n, BigDecimal.ROUND_HALF_UP).toDouble()

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val output : Array<TextView> = arrayOf(output0, output1, output2, output3, output4)
        val currency : Array<String> = arrayOf("RUB", "EUR", "USD", "GBR", "UAH", "JPY")
        val coefficient : Array<Double> = arrayOf(1.0000, 78.5225, 69.1219, 87.6673, 2.5990, 0.6460)
        button.setOnClickListener {
            if(input.text.toString().isNotEmpty()) {
                var outputIndex = 0
                val input : Double = input.text.toString().toDouble()
                val pos = spinner.selectedItemPosition
                for(i in currency.indices) {
                    if (i == pos) continue
                    val result = input / coefficient[i] * coefficient[pos]
                    output[outputIndex++].text = "${result.roundToNDecimalPlaces(2)} ${currency[i]}"
                }
            }
            else {
                for(i in output) i.text = ""
                output0.text = "Still not entered currency amount"
            }
        }
    }
}