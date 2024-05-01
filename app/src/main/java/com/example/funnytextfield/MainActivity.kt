package com.example.funnytextfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.funnytextfield.ui.theme.FunnyTextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunnyTextFieldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FunnyTextField()
                }
            }
        }
    }
}

@Composable
fun FunnyTextField() {
    var text by remember {
        mutableStateOf("")
    }
    var passwordText by remember {
        mutableStateOf("")
    }
    var isReadable by remember {
        mutableStateOf(true)
    }
    var isWriting by remember {
        mutableStateOf(false)
    }


    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Color.Black, shape = CircleShape
                )
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 20.dp)
            ) {
                val rotateDegree by animateFloatAsState(
                    targetValue = if (!isWriting) 0f else if (text.isEmpty()) 40f else if (!isReadable) (40f + -180) else (40f + (-text.length * 1.8f)),
                    label = ""
                )
                Eye(rotateDegree)
                Spacer(modifier = Modifier.width(5.dp))
                Eye(rotateDegree)

            }

        }

        OutlinedTextField(
            label = { Text(text = "User name") },
            value = text,
            onValueChange = {
                text = it
                run { isReadable = true }
                run { isWriting = true }

            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            /*
            trailingIcon = {
                IconButton(onClick = { isReadable = !isReadable }) {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                }
            },

            visualTransformation = if (isReadable) VisualTransformation.None else PasswordVisualTransformation()
            */
        )
        OutlinedTextField(
            label = { Text(text = "Password") },
            value = passwordText,
            onValueChange = {
                passwordText = it
                run { isReadable = false }
                run { isWriting = true }

            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

    }
}


@Composable
fun Eye(rotateDegree: Float) {
    Box(

        contentAlignment =
            if (rotateDegree == 0f)Alignment.Center else Alignment.BottomCenter,
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = Color.Black, shape = CircleShape
            )
            .rotate(rotateDegree)
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(Color.Black)
                .border(
                    width = 1.dp,
                    color = Color.Black, shape = CircleShape
                )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunnyTextFieldTheme {
        FunnyTextField()
    }
}