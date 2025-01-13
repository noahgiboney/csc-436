package com.example.graduationrsvp

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.graduationrsvp.ui.theme.GraduationRSVPTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraduationRSVPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GradScreen()
                }
            }
        }
    }
}

@Composable
fun GradScreen(modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ){
        Text(
            text = stringResource(R.string.graduation_announcement),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 50.sp,
            color = Color.Red,
            textAlign = TextAlign.Center,
            modifier = modifier
        )

        Image(
            painter = painterResource(R.drawable.grad_cap),
            contentDescription = stringResource(R.string.grad_cap),
            alpha = 0.7f,
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Filled.Info,
                contentDescription = "information",
                modifier = Modifier
                    .padding(10.dp)
                    .size(40.dp)
            )

            Text(stringResource(R.string.graduation_date), fontSize = 20.sp)
        }

        RSVP()
    }
}

@Composable
fun RSVP() {
    var isChecked by remember { mutableStateOf(true) }
    val message = if (isChecked) "I'll be there!" else "Sorry I can't be there."

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Switch(checked = isChecked, onCheckedChange = { isChecked = it })

        Text(message)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GraduationRSVPTheme {
        GradScreen()
    }
}