package com.janus.deprem.usecase

import com.janus.deprem.data.Location
import com.janus.deprem.data.LocationType
import com.janus.deprem.remote.NetworkHandler
import com.janus.deprem.repo.LocationRepository
import com.janus.deprem.repo.isMe
import com.janus.deprem.util.Status
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LocationUseCase
@Inject constructor(
    val networkHandler: NetworkHandler,
    val locationRepository: LocationRepository
) {
    val status = PublishSubject.create<Status>()

    fun getIbbPoints(): Flowable<Status> {
        return networkHandler
            .getIbbPoints()
            .map(locationRepository::save)
            .doOnNext(::updateStatus)
            .doOnError { updateStatus(Status.FAILED.withReason(it.message)) }
    }

    fun getLocations(): ArrayList<Location> {
        val locations = ArrayList<Location>()
        locationRepository.getVehicles().forEach {
            locations.add(
                Location(
                    it.vehicleLocationLatitude.toDouble(),
                    it.vehicleLocationLongitude.toDouble(),
                    it.vehicleName,
                    it.vehicleDescription,
                    if (it.isMe()) LocationType.CURRENT else LocationType.HELP
                )
            )
        }
        return locations
    }

    private fun updateStatus(status: Status) {
        this.status.onNext(status)
    }
}