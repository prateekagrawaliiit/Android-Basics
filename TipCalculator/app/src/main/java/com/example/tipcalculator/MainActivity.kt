package com.example.tipcalculator

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateTip.setOnClickListener {
//            Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show()
            calculateTip()
        }
        binding.textField.setOnKeyListener{ view, keyCode, _ -> handleKeyEvent(view, keyCode) }
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
        val defaultFormat = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        val final_tip = defaultFormat.format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, final_tip)


    }
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }


}
