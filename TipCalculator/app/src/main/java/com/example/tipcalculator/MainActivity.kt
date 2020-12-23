package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateTip.setOnClickListener({
//            Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show()
            calculateTip()
        })
    }

    private fun calculateTip() {
        val amount: Double? = ((binding.textField.text).toString()).toDoubleOrNull()

        if (amount == null) {
            binding.tipResult.text = ""
            return
        }
        val tip_perc: Double = when(binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.2
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip: Double = amount * tip_perc

        val roundUp = binding.roundTip.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        val final_tip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, final_tip)


    }


}