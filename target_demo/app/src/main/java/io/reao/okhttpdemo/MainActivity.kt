package io.reao.okhttpdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.reao.okhttpdemo.ui.theme.OkhttpdemoTheme
import io.reao.okhttpdemo.util.HttpUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Response

private var httpUtils = HttpUtils();

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OkhttpdemoTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    color = Color.White
                ) {
                    StatusBar()
                    Column() {
                        MyAppBar()
                        Greeting()
                    }
                }
            }
        }
    }

}


@Composable
fun MyAppBar() {
    TopAppBar(
        title = { Text("AA") },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    )
}

@Composable
fun StatusBar() {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme

        systemUiController.setStatusBarColor(
            color = Color.Black
        )

        //and setNavigationBarsColor() also exist
    }
}


@Composable
fun Greeting() {
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            onClick = {
                httpUtils.okhttpGet()
            }) {
            Text(text = "Get")
        }
        Button(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth(),
            onClick = {
                httpUtils.okhttpPost()
            }
        ) {
            Text(text = "Post")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Post With Interceptor")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Post With SSL Pining")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Post With Two-way Certification")
        }
    }
}

@Composable
fun DefaultPreview() {
    OkhttpdemoTheme() {
        Greeting()
    }
}
