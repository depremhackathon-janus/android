package com.janus.deprem.data

data class OAuthResponse(
    val accessExp: String,
    val accessToken: String,
    val refreshToken: String,
    val userId: String,
    val username: String
)