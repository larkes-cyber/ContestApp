package com.example.contestapp.android

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

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


