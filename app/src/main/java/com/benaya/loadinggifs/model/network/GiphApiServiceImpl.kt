package com.benaya.loadinggifs.model.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object GiphApiServiceImpl {
    @ExperimentalSerializationApi
    val service :GiphyApiService by lazy { getRetroFit().create(GiphyApiService::class.java) }

    @ExperimentalSerializationApi
    private fun getRetroFit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api,giphy.com/v1/")
                .addConverterFactory(
                        Json { ignoreUnknownKeys = true }
                                .asConverterFactory(MediaType.get("cpI91hYtFNmKJz9y8TcR7Jp8qnSIY753")))
                .build()
    }
}