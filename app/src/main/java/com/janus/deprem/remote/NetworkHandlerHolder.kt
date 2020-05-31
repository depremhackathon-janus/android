package com.janus.deprem.remote

// to resolve cyclic dependency issue authenticator-> networkHandler -> authenticator
// instead networkhandlerholder -> authenticator -> networkHandler (then networkHandler instance is created inside networkhandlerholder)
class NetworkHandlerHolder {
    lateinit var networkHandler: NetworkHandler
}