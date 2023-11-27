package com.cibertec.examenfinaldam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.bibliotecaapp.RetroFitHelper
import com.cibertec.examenfinaldam.VideoREST.QuoteApi
import com.cibertec.examenfinaldam.VideoREST.QuotesList
import com.cibertec.examenfinaldam.VideoREST.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MenuVideos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_videos)

        val recyclerViewVideos: RecyclerView = findViewById(R.id.recyclerViewVideos)
        recyclerViewVideos.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<Video>();

        val adapter = VideoAdapter(data)
        recyclerViewVideos.adapter = adapter

        val quoteApi = RetroFitHelper.getRetrofitInstance(this).create(QuoteApi::class.java)

        adapter.setOnItemClickListener(object : VideoAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val selectedVideo = data[position]

                val intent = Intent(this@MenuVideos, VideoRepro::class.java)
                intent.putExtra("source", selectedVideo.sources[0])
                startActivity(intent)
            }
        })

        lifecycleScope.launch {
            try {
                // Llamada
                val result: Response<QuotesList> = withContext(Dispatchers.IO) {
                    quoteApi.getQuotes()
                }
                // Verificar
                if (result.isSuccessful) {
                    // Extraer de la respuesta
                    val quotesList = result.body()?.categories
                    // Actualizar recycle view
                    quotesList?.let {
                        data.clear()
                        for (category in it) {
                            for (video in category.videos) {
                                data.add(
                                    Video(
                                        video.description,
                                        video.sources,
                                        video.subtitle,
                                        video.thumb,
                                        video.title
                                    )
                                )
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    // Excepcion
                    Log.e("REST RESPONSE", "Error: ${result.code()}")
                }
            } catch (e: Exception) {
                // Excepcion
                Log.e("REST RESPONSE", "Exception: ${e.message}")
            }
        }
    }





}