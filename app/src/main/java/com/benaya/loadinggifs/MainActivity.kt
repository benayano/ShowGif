package com.benaya.loadinggifs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.benaya.loadinggifs.model.network.GiphApiServiceImpl
import com.benaya.loadinggifs.model.network.GiphyApiService
import com.benaya.loadinggifs.model.network.GiphyResponse
import com.benaya.loadinggifs.vieu.GifsAdapter
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var gifList: RecyclerView
    private lateinit var gifsAdapter: GifsAdapter
    private lateinit var progressBar: ProgressBar

    @ExperimentalSerializationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        gifList = findViewById(R.id.gifList)

        gifsAdapter = GifsAdapter()

        gifList.adapter = gifsAdapter

        loadGifs()


    }

    @ExperimentalSerializationApi
    private fun loadGifs() {
        val call = GiphApiServiceImpl
                .service
                .fetchTrendingGifs("cpI91hYtFNmKJz9y8TcR7Jp8qnSIY753").also {
                    it.enqueue(GifCallback())
                }

        setProgressVisibility(true)
    }

    private fun setProgressVisibility(show: Boolean) = progressBar.isVisible == show

    inner class GifCallback : Callback<GiphyResponse> {
        override fun onResponse(call: Call<GiphyResponse>, response: Response<GiphyResponse>){
            if(response.isSuccessful){
                response.body()?.data?.let { gifsAdapter.submitList(it) }
            }
            setProgressVisibility(false)
        }

        override fun onFailure(call: Call<GiphyResponse>, t: Throwable) {
            setProgressVisibility(false)

            Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
        }
    }
}