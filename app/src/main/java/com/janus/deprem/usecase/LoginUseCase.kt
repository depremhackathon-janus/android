package com.janus.deprem.usecase

import com.janus.deprem.remote.NetworkHandler
import com.janus.deprem.repo.TokenRepository
import com.janus.deprem.util.Status
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LoginUseCase
@Inject constructor(
    val networkHandler: NetworkHandler,
    val tokenRepository: TokenRepository
) {
    val status = PublishSubject.create<Status>()

    private fun login() {
        networkHandler
            .login("")
            .map(tokenRepository::saveToken)
            .doOnNext(::updateStatus)
            .doOnError { it -> updateStatus(Status.FAILED.withReason(it.message)) }
            .subscribe()
    }

    private fun updateStatus(status: Status) {
        this.status.onNext(status)
    }
}