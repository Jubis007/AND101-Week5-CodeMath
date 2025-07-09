package com.example.codemath

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.codemath.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.calculateButton.setOnClickListener {
            val billAmountString = binding.billAmountEditText.text.toString()

            if (billAmountString.isEmpty()) {
                return@setOnClickListener // Stop here if the input is empty
            }
            val billAmount = billAmountString.toDouble()


            val splitBy = when (binding.splitRadioGroup.checkedRadioButtonId) {
                R.id.splitBy2RadioButton -> 2
                R.id.splitBy3RadioButton -> 3
                else -> 1
            }

            val tipPercentage = 0.18 // Using 18% as an example, can change this later
            val tip = billAmount * tipPercentage
            val total = billAmount + tip
            val totalPerPerson = total / splitBy

            val formattedTip = "%.2f".format(tip)
            val formattedTotalPerPerson = "%.2f".format(totalPerPerson)

            binding.tipResultTextView.text = "$$formattedTip"
            binding.totalPerPersonResultTextView.text = "$$formattedTotalPerPerson"


        }



        binding.resetFab.setOnClickListener {
            binding.billAmountEditText.text?.clear() //If the object on the left is not null, then call the function on the right. If the object is null, do nothing at all
            binding.tipResultTextView.text = ""
            binding.totalPerPersonResultTextView.text = ""
            binding.splitRadioGroup.check(R.id.splitBy1RadioButton) // Resets to the default option
        }

    }
}