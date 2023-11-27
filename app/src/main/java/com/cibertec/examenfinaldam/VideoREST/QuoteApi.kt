package com.cibertec.examenfinaldam.VideoREST

import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {
    @GET("/api/videos")
    suspend fun getQuotes():Response<QuotesList>
}