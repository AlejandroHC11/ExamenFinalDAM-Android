package com.cibertec.examenfinaldam.VideoREST

data class Category(
    val name: String,
    val videos: List<Video>
){
}