package com.websarva.wings.developlab

import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import com.websarva.wings.developlab.ui.theme.DevelopLabTheme
import coil.load

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            DevelopLabTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Log.d("hoge", "creating picker")
//                    // Registers a photo picker activity launcher in single-select mode.
//                    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//                        if(uri != null) {
//                            Log.d("hoge", "uri: $uri")
//                        } else {
//                            Log.d("hoge", "uri: null")
//                        }
//                    }
//
//                    Log.d("hoge", "launching picker")
//                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//                    Greeting("Android")
//                }
//            }
//        }
//    }
//}

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            DevelopLabTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Log.d("hoge", "creating picker")
//                    Greeting("Android")
//                }
//            }
//        }
//
//        // Registers a photo picker activity launcher in single-select mode here in onCreate.
//        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//            if(uri != null) {
//                Log.d("hoge", "uri: $uri")
//                DisplayImageFromUri(uri = uri.toString())
//            } else {
//                Log.d("hoge", "uri: null")
//            }
//        }
//
//        Log.d("hoge", "$pickMedia")
//        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//    }
//}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Registers a photo picker activity launcher in single-select mode here in onCreate.
        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if(uri != null) {
                Log.d("hoge", "uri: $uri")
                setContent {
                    // Compose画面に切り替えて画像を表示
                    DisplayImageFromUri(uri = uri.toString())
                }
            } else {
                Log.d("hoge", "uri: null")
            }
        }

        setContent {
            DevelopLabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Log.d("hoge", "creating picker")
                    Greeting("Android")
                }
            }
        }

        Log.d("hoge", "$pickMedia")
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun DisplayImageFromUri(uri: String) {
    val imagePainter = rememberImagePainter(data = uri)
    Image(
        painter = imagePainter,
//        painter = painterResource(uri.toInt()), // URIから画像を読み込む
        contentDescription = null, // 画像の説明文
        contentScale = ContentScale.Crop, // 画像のスケーリング方法を設定
        modifier = Modifier
            .fillMaxWidth() // 画像を横幅いっぱいに表示
            .height(200.dp) // 画像の高さを設定
    )
}
