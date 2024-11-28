package com.example.hiddencalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddencalculator.databinding.ActivityCalculatorBinding
import com.example.hiddencalculator.databinding.ActivityMainBinding

class CalculatorActivity : AppCompatActivity() {
    private var result: Int? = null
    private lateinit var binding: ActivityCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.apply {
            plusBTN.setOnClickListener { calculate { a, b -> a + b } }
            minusBNT.setOnClickListener { calculate { a, b -> a - b } }
            multBTN.setOnClickListener { calculate { a, b -> a * b} }
            divBTN.setOnClickListener { calculate { a, b -> a / b } }
            sendDataBTN.setOnClickListener {
                if (result == null) {
                    Toast.makeText(this@CalculatorActivity,
                        getString(R.string.we_have_nothing_to_send), Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                result?.let {
                    val intent = Intent().apply {
                        putExtra(MainActivity.KEY_RESULT, result)
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    private fun checkInput(str:String): Boolean = "^\\d+$".toRegex().matches(str)

    private fun makeOperation(first: Int, second:Int, op: (Int, Int) -> Int): Int {
        return op(first, second)
    }

    private fun calculate(op: (Int, Int) -> Int) {
        binding.apply {
            val first = firstET.text.toString()
            val second = secondET.text.toString()
            if (!(checkInput(first) && checkInput(second))) {
                Toast.makeText(this@CalculatorActivity, R.string.wrong_input, Toast.LENGTH_SHORT).show()
                return
            }
            result = makeOperation(first.toInt(), second.toInt(), op)
            resultTV.text = String.format(getString(R.string.result_placeholder), result.toString())
        }
    }
}