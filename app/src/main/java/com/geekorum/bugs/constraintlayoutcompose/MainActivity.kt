package com.geekorum.bugs.constraintlayoutcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.geekorum.bugs.constraintlayoutcompose.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ConstraintBug()
                }
            }
        }
    }
}

@Composable
fun ConstraintBug() {
    var buttonFirst by remember { mutableStateOf(true) }
    val constraints = if (buttonFirst) {
        ConstraintSet {
            val button = createRefFor("button")
            val text = createRefFor("text")
            constrain(button) {
                top.linkTo(parent.top, 32.dp)
                centerHorizontallyTo(parent)
            }
            constrain(text) {
                top.linkTo(button.bottom, 56.dp)
                centerHorizontallyTo(parent)
            }
        }
    } else {
        ConstraintSet {
            val button = createRefFor("button")
            val text = createRefFor("text")
            constrain(button) {
                top.linkTo(text.bottom, 56.dp)
                centerHorizontallyTo(parent)
            }
            constrain(text) {
                top.linkTo(parent.top, 32.dp)
                centerHorizontallyTo(parent)
            }
        }
    }

    // workaround is to wrap ConstraintLayout in key
//    key(constraints) {

    ConstraintLayout(constraints, Modifier.fillMaxSize()) {
        Button(modifier = Modifier.layoutId("button"),
            onClick = { buttonFirst = !buttonFirst}) {
            Text("Click me")
        }
        Text("This should move on click", Modifier.layoutId("text"))
    }

//    }
}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}
