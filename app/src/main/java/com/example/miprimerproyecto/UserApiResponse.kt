package com.example.miprimerproyecto


data class UserApiResponse(
    val results: List<UserImage>
)

data class UserImage(
    val name: Name,
    val picture: Picture

)

data class Name(
    val title:String,
    val first:String,
    val last: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String

)