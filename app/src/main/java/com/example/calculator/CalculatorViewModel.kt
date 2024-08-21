package com.example.calculator

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private val calculatorLogic = CalculatorLogic()

    var displayText by mutableStateOf("")
        private set

    private var firstValue: String = "0"
    private var operator: String? = null
    private var secondValue: String = ""

    var shouldClearDisplay by mutableStateOf(false)
        private set

    fun onDigitPress(digit: String) {
        if (operator == null) {
            firstValue = if (firstValue == "0") digit else firstValue + digit
            displayText = firstValue
        } else {
            secondValue += digit
            displayText = "$firstValue $operator $secondValue"
        }
    }

    fun onOperationPress(op: String) {
        if (firstValue.isNotEmpty() && secondValue.isEmpty()) {
            operator = op
            updateDisplayText()
            shouldClearDisplay = true
        }
    }

    fun onClearPress() {
        displayText = ""
        firstValue = "0"
        secondValue = ""
        operator = null
        shouldClearDisplay = false
        updateDisplayText()
    }

    fun onEqualPress() {
        if (operator == null || secondValue.isEmpty()) return

        val result = calculatorLogic.calculate(firstValue, operator, secondValue)

        displayText = result
        firstValue = result
        operator = null
        secondValue = ""
        updateDisplayText()
    }

    fun onErasePress() {
        when {
            secondValue.isNotEmpty() -> {
                secondValue = secondValue.dropLast(1)
                updateDisplayText()
            }
            operator != null -> {
                operator = null
                displayText = firstValue
            }
            firstValue.isNotEmpty() -> {
                firstValue = if (firstValue.length == 1) "0" else firstValue.dropLast(1)
                updateDisplayText()
            }
        }
    }

    private fun updateDisplayText() {
        displayText = when {
            operator == null -> firstValue
            secondValue.isEmpty() -> "$firstValue $operator"
            else -> "$firstValue $operator $secondValue"
        }
    }
}














//    private var shouldClearDisplay: Boolean = false
//
//
//    fun onDigitPress(digit: String) {
//        if (shouldClearDisplay) {
//            displayText = digit
//            shouldClearDisplay = false
//        } else {
//            if (digit == "." && displayText.contains(".")) return
//            displayText = if (displayText == "0" && digit != ".") digit else displayText + digit
//        }
//    }
//
//
//
//    fun onOperationPress(op: String) {
//        if (operator != null) {
//            onEqualPress()
//        }
//        lastValue = displayText
//        operator = op
//        shouldClearDisplay = true
//    }
//
//    fun onEqualPress() {
//        if (operator == null) return
//
//        val currentValue = displayText.toDoubleOrNull() ?: return
//        val lastValue = lastValue.toDoubleOrNull() ?: return
//        val result = when (operator) {
//            "+" -> lastValue + currentValue
//            "-" -> lastValue - currentValue
//            "*" -> lastValue * currentValue
//            "/" -> if (currentValue != 0.0) lastValue / currentValue else return
//            else -> return
//        }
//        displayText = result.toString()
//        operator = null
//        shouldClearDisplay = true
//    }
//
//
//    fun onClearPress() {
//        displayText = "0"
//        lastValue = ""
//        operator = null
//        shouldClearDisplay = false
//    }
//