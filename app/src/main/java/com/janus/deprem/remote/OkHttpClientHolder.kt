package com.janus.deprem.remote

import okhttp3.OkHttpClient

// to resolve cyclic dependency issue
class OkHttpClientHolder {
    lateinit var client: OkHttpClient
}