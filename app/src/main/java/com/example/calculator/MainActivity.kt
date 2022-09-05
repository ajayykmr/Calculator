package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {

    var lastnumeric: Boolean=false
    var lastdecimal: Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun ondigit(view: View) {

        textview.append((view as Button).text)
        lastnumeric=true
    }

    fun onoperator(view: View) {
        if (textview.text.isNotEmpty()) {
            if (lastnumeric && !isOperatorAdded(textview.text.toString())) {
                textview.append((view as Button).text)
                lastnumeric = false
                lastdecimal = false

            }
        }
    }
    fun AC(view: View) {
        textview.text=""
        lastdecimal=false
        lastnumeric=false
    }

    fun equal(view: View) {
        if (lastnumeric) {
            var tvvalue = textview.text.toString()
            var prefix = ""
            try {
                if (tvvalue.startsWith("-")) {
                    prefix = "-"
                    tvvalue = tvvalue.substring(1)
                }

                if (tvvalue.contains("-")) {
                    val splitvalue = tvvalue.split("-")

                    var one = splitvalue[0];
                    var two = splitvalue[1];

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    textview.text = removeZeroAfterDecimal((one.toDouble() - two.toDouble()).toString())
                } else if(tvvalue.contains("+")){
                    val splitvalue = tvvalue.split("+")

                    var one = splitvalue[0];
                    var two = splitvalue[1];

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    textview.text =removeZeroAfterDecimal ((one.toDouble() + two.toDouble()).toString())
                } else if(tvvalue.contains("/")){
                    val splitvalue = tvvalue.split("/")

                    var one = splitvalue[0];
                    var two = splitvalue[1];

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    textview.text =removeZeroAfterDecimal ((one.toDouble() / two.toDouble()).toString())
                } else if(tvvalue.contains("*")){
                    val splitvalue = tvvalue.split("*")

                    var one = splitvalue[0];
                    var two = splitvalue[1];

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    textview.text = removeZeroAfterDecimal((one.toDouble() * two.toDouble()).toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }
    fun decimal(view: View) {
        if (lastnumeric && !lastdecimal)
        {
            textview.append(".")
            lastnumeric=false
            lastdecimal=true
        }

    }

    fun erase(view: View){
        if (textview.text.isNotEmpty())
        {
            if (textview.text.length>1)
                textview.text = textview.text.substring(0, textview.text.length - 1)
            else
                textview.text = ""
        }
    }
    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }
        else
        {
            (       value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")      )

        }
    }
    private fun removeZeroAfterDecimal(result: String): String{
        var value = result
            if (result.contains(".0") )
                    value = result.substring(0, result.length - 2)
        return value
    }


}