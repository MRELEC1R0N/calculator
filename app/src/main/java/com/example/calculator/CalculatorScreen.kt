package com.example.calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalculatorScreen() {
    val viewModel: CalculatorViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DisplaySection(
            displayText = viewModel.displayText,
            onErasePress = {viewModel.onErasePress()},
            modifier = Modifier.weight(0.4f))

        ButtonSection(
            onDigitPress = {viewModel.onDigitPress(it)},
            onOperationPress = {viewModel.onOperationPress(it)},
            onEqualPress = {viewModel.onEqualPress()},
            onClearPress = {viewModel.onClearPress()},
            modifier = Modifier.weight(0.6f)
        )
    }
}


@Composable
fun DisplaySection(
    displayText: String ,
    onErasePress: () -> Unit,
    modifier: Modifier = Modifier

) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = displayText,
            modifier = modifier
                .fillMaxWidth()
                .padding(end = 16.dp,top = 32.dp)
            ,
            textAlign = TextAlign.End,
            fontSize = 80.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.delete),
            contentDescription = "eraser",
            modifier = Modifier
                .padding(end = 16.dp, bottom = 16.dp)
                .size(50.dp)
                .clickable { onErasePress() }
        )
    }
}



@Composable
fun ButtonSection(
    onDigitPress: (String) -> Unit,
    onOperationPress: (String) -> Unit,
    onEqualPress: () -> Unit,
    onClearPress: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth()
    ) {
        val buttonSize = (maxWidth - 3 * 16.dp) / 4

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(text = "C", modifier = Modifier.size(buttonSize), onClick = { onClearPress() })
                CalculatorButton(text = "()", modifier = Modifier.size(buttonSize), onClick = { /* Handle () */ })
                CalculatorButton(text = "%", modifier = Modifier.size(buttonSize), onClick = { /* Handle % */ })
                CalculatorButton(text = "÷", modifier = Modifier.size(buttonSize), onClick = { onOperationPress("÷") })
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(text = "7", modifier = Modifier.size(buttonSize), onClick = { onDigitPress("7") })
                CalculatorButton(text = "8", modifier = Modifier.size(buttonSize), onClick = { onDigitPress("8") })
                CalculatorButton(text = "9", modifier = Modifier.size(buttonSize), onClick = { onDigitPress("9") })
                CalculatorButton(text = "x", modifier = Modifier.size(buttonSize), onClick = { onOperationPress("x") })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(text = "4", modifier = Modifier.size(buttonSize), onClick = { onDigitPress("4") })
                CalculatorButton(text = "5", modifier = Modifier.size(buttonSize), onClick = { onDigitPress("5") })
                CalculatorButton(text = "6", modifier = Modifier.size(buttonSize), onClick = { onDigitPress("6") })
                CalculatorButton(text = "-", modifier = Modifier.size(buttonSize), onClick = { onOperationPress("-") })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(text = "1", modifier = Modifier.size(buttonSize), onClick = { onDigitPress("1") })
                CalculatorButton(text = "2", modifier = Modifier.size(buttonSize), onClick = { onDigitPress("2") })
                CalculatorButton(text = "3", modifier = Modifier.size(buttonSize), onClick = { onDigitPress("3") })
                CalculatorButton(text = "+", modifier = Modifier.size(buttonSize), onClick = { onOperationPress("+") })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(text = "+/-", modifier = Modifier.size(buttonSize), onClick = { /* Handle +/- */ })
                CalculatorButton(text = "0", modifier = Modifier.size(buttonSize), onClick = { onDigitPress("0") })
                CalculatorButton(text = ".", modifier = Modifier.size(buttonSize), onClick = { onDigitPress(".") })
                CalculatorButton(text = "=", modifier = Modifier.size(buttonSize), onClick = { onEqualPress() })
            }
        }
    }
}


@Composable
fun CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    buttonColor: Color = Color.DarkGray,
    textColor: Color = Color.White,
    onClick: () -> Unit
) {
    val fontSize = if (text.length > 1) 20.sp else 24.sp

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = buttonColor
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen()
}
