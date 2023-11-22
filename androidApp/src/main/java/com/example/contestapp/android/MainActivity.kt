package com.example.contestapp.android

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toFile
import androidx.core.net.toUri
import com.example.contestapp.Greeting
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player.REPEAT_MODE_OFF
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

class MainActivity : ComponentActivity() {

    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current

            val url = "http://192.168.1.161:8080/user/get_audio/video.mp4"

            val audioUrl = "http://192.168.1.161:8080/user/get_audio"

//            LaunchedEffect(Unit){
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
//
//                mediaPlayer.setDataSource(audioUrl)
//
//                mediaPlayer.prepare()
//
//                mediaPlayer.start()
//            }


            val exoPlayer = remember {
                ExoPlayer.Builder(context).build().apply {
                    setMediaItem(MediaItem.fromUri(Uri.parse(url)))
                    prepare()
                    play()
                }
            }

            MyApplicationTheme {
                Text("dfgfdfg")
                AndroidView(
                    factory = { context ->
                        StyledPlayerView(context).apply {
                            player = exoPlayer
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            DisposableEffect(Unit) {
                onDispose { exoPlayer.release() }
            }
        }
    }
}


