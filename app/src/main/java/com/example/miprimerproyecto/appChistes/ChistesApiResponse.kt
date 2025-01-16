package com.example.miprimerproyecto.appChistes

data class ChistesApiResponse(
    val results: List<Chistes>
)
data class Chistes(
    val icon_url:String,
    val id:String,
    val url: String,
    val value: String
)