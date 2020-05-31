package com.janus.deprem.remote

import com.janus.deprem.repo.TokenRepository
import okhttp3.HttpUrl
import okhttp3.Request

object ApiConstants {
    const val API_URL = "http://18.202.176.0:8000/"

    //region non authorized
    const val AUTHENTICATION_URL = "api/auth/login"
    const val REFRESH_TOKEN_URL = "api/auth/token"
    const val VEHICLES = "vehicles"
    //endregion
}

fun HttpUrl.isRefresh(): Boolean {
    val s = pathSegments().joinToString(separator = "/")
    return s.startsWith(ApiConstants.REFRESH_TOKEN_URL)
}

fun HttpUrl.isAuthorized(): Boolean {
    val s = pathSegments().joinToString(separator = "/")
    return !(s.startsWith(ApiConstants.AUTHENTICATION_URL)
            || s.startsWith(ApiConstants.REFRESH_TOKEN_URL))
}

fun Request.Builder.addTokenIfAvailable(
    isRefreshToken: Boolean = false,
    tokenRepository: TokenRepository
): Request.Builder {
    val token = if (isRefreshToken) tokenRepository.getRefreshToken() else tokenRepository.getAccessToken()
    if (!token.isEmpty()) {
        addHeader("Authorization", "Bearer $token")
    }
    return this
}