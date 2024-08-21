package com.example.calculator

class CalculatorLogic {

    fun calculate(firstValue: String, operator: String?, secondValue: String): String {
        val firstNum = firstValue.toDoubleOrNull() ?: return ""
        val secondNum = secondValue.toDoubleOrNull() ?: return ""
        val result = when (operator) {
            "+" -> firstNum + secondNum
            "-" -> firstNum - secondNum
            "x" -> firstNum * secondNum
            "÷" -> if (secondNum != 0.0) firstNum / secondNum else return ""
            else -> return ""
        }
        return if (result == result.toInt().toDouble()) {
            result.toInt().toString()
        } else {
            String.format("%.2f", result)
        }
    }
}
