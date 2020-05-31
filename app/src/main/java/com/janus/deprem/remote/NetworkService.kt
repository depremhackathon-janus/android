package com.janus.deprem.remote

import com.janus.deprem.data.OAuthResponse
import com.janus.deprem.data.Vehicle
import com.janus.deprem.remote.ApiConstants.AUTHENTICATION_URL
import com.janus.deprem.remote.ApiConstants.REFRESH_TOKEN_URL
import com.janus.deprem.remote.ApiConstants.VEHICLES
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path


interface NetworkService {
    //region non authorized
    @GET("$AUTHENTICATION_URL{accessToken}")
    fun facebookLogin(@Path("accessToken") accessToken: String): Flowable<OAuthResponse>

    @GET(REFRESH_TOKEN_URL)
    fun refreshToken(): Flowable<OAuthResponse>
    //endregion

    //region authorized
    @GET(VEHICLES)
    fun getVehicles(): Flowable<List<Vehicle>>
    //endregion
}