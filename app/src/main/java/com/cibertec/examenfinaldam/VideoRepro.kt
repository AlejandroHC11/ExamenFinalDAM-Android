package com.cibertec.examenfinaldam

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView

class VideoRepro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_repro)

        val buttonAtras : Button = findViewById(R.id.buttonAtras)
        buttonAtras.setOnClickListener{
            val MenuVideos = Intent(this, MenuVideos::class.java)
            startActivity(MenuVideos)
            finish()
        }

        val videoSource = intent.getStringExtra("source")

        val videoView: VideoView = findViewById(R.id.videoView)
        val uri: Uri = Uri.parse(videoSource)
        videoView.setVideoURI(uri)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)

        videoView.start()
    }
}