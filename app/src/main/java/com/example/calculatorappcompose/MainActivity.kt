package com.example.calculatorappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorappcompose.ui.theme.CalculatorAppComposeTheme
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppComposeTheme {
                CalculatorScreen()
            }
        }
    }
}

@Preview
@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {

    var expression by remember {
        mutableStateOf("")
    }
    var result by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Spacer(modifier = modifier
                .fillMaxHeight()
                .weight(1f))

            Text(
                modifier = modifier.fillMaxWidth(),
                text = expression,
                style = TextStyle(
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.End
                ),
                maxLines = 3
            )

            Text(
                modifier = modifier.fillMaxWidth(),
                text = result,
                style = TextStyle(
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.End
                )
            )

        }

        Divider(modifier = modifier.padding(vertical = 10.dp))

        Column(modifier = modifier.fillMaxWidth()) {

            Row(modifier = modifier.fillMaxWidth()) {
                CalculatorButton(
                    text = "AC",
                    isFunction = true,
                    modifier = modifier.weight(2f),
                    onClick = {
                        expression = ""
                        result = ""
                    })
                CalculatorButton(
                    text = "Clr",
                    isFunction = true,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression = delCharachter(expression)

                    })
                CalculatorButton(
                    text = "/",
                    isFunction = true,
                    modifier = modifier.weight(1f),
                    onClick = {

                        expression += it
                    })

            }

            Row(modifier = modifier.fillMaxWidth()) {
                CalculatorButton(
                    text = "7",
                    isFunction = false,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })
                CalculatorButton(
                    text = "8",
                    isFunction = false,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })
                CalculatorButton(
                    text = "9",
                    isFunction = false,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })
                CalculatorButton(
                    text = "x",
                    isFunction = true,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })

            }
            Row(modifier = modifier.fillMaxWidth()) {
                CalculatorButton(
                    text = "4",
                    isFunction = false,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })
                CalculatorButton(
                    text = "5",
                    isFunction = false,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })
                CalculatorButton(
                    text = "6",
                    isFunction = false,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })
                CalculatorButton(
                    text = "+",
                    isFunction = true,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })

            }
            Row(modifier = modifier.fillMaxWidth()) {
                CalculatorButton(
                    text = "1",
                    isFunction = false,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })
                CalculatorButton(
                    text = "2",
                    isFunction = false,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })
                CalculatorButton(
                    text = "3",
                    isFunction = false,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })
                CalculatorButton(
                    text = "-",
                    isFunction = true,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })

            }

            Row(modifier = modifier.fillMaxWidth()) {
                CalculatorButton(
                    text = "0",
                    isFunction = false,
                    modifier = modifier.weight(2f),
                    onClick = {
                        expression += it
                    })
                CalculatorButton(
                    text = ".",
                    isFunction = false,
                    modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    })
                CalculatorButton(
                    text = "=",
                    isFunction = true,
                    modifier = modifier.weight(1f),
                    onClick = {
                        if (expression.isEmpty()) return@CalculatorButton
                        result = solveExpression(expression)

                    })

            }

        }
    }

}

fun solveExpression(expression: String): String {
    var answer = ""
    try {
        answer = Expression(
            expression
                .replace(
                    "x", "*"
                )
        ).calculate().toString()
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
    return answer
}

fun delCharachter(expression: String): String {
    if (expression.isNotEmpty()) {
        return expression.substring(0, expression.length - 1)
    }
    return expression

}

@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    text: String,
    isFunction: Boolean = false,
    onClick: (String) -> Unit = {}
) {

    ElevatedButton(
        modifier = modifier
            .size(71.dp)
            .clip(CircleShape)
            .padding(4.dp),
        onClick = { onClick(text) }, colors = ButtonDefaults.buttonColors(
            containerColor = if (isFunction && text == "=" || text == "AC") {
                MaterialTheme.colorScheme.secondary
            } else if (isFunction) {
                MaterialTheme.colorScheme.error
            } else {
                MaterialTheme.colorScheme.tertiary
            }
        )
    ) {

        Text(
            text = text, style = TextStyle(
                fontSize = 24.sp,
                color = if (isFunction && text != "=" && text != "AC") {
                    MaterialTheme.colorScheme.background
                } else {
                    MaterialTheme.colorScheme.onTertiary
                }
            )
        )
    }

}

