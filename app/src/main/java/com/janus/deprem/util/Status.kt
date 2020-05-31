package com.janus.deprem.util

enum class Status {
    NOT_STARTED, IN_PROGRESS, FINISHED, FAILED;

    var reason: Any? = null

    fun withReason(r: Any?): Status {
        reason = r
        return this
    }
}