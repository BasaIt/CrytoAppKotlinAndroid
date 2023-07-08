package com.example.calculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.experimental.ExperimentalObjCRefinement


class MainActivity : AppCompatActivity() {
  
    lateinit var outputTextView: TextView
    var lastNumaric: Boolean = false
    var stateError: Boolean = false
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        


        fun onDigit(view: View) {
            if (stateError) {
                outputTextView.text = (view as Button).text
                stateError = false
            } else {
                outputTextView.append((view as Button).text)
            }
            lastNumaric = true
        }

        fun onDecimalPoint(view: View) {
            if (lastNumaric && !stateError && !lastDot) {
                outputTextView.append(".")
                lastNumaric = false
                lastDot = true
            }
        }

        fun onOperator(view: View) {
            if (lastNumaric && !stateError) {
                outputTextView.append((view as Button).text)
                lastNumaric = false
                lastDot = false
            }
        }


        fun onClear(view: View) {
            this.outputTextView.text = ""
            lastNumaric = false
            stateError = false
            lastDot = false
        }

        fun onEqual(view: View) {

            if (lastNumaric && !stateError) {
                val text = outputTextView.text.toString()
                val expression = text.toString()
                try {
                    val result = expression
                    outputTextView.text = result.toString()
                    lastDot = true
                } catch (ex: Exception) {
                    outputTextView.text = "Error"
                    stateError = true
                    lastNumaric = false
                }
            }

        }


    }
}


