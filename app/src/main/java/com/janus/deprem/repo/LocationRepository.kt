package com.janus.deprem.repo

import android.text.TextUtils
import com.janus.deprem.data.Vehicle
import com.janus.deprem.remote.DataHolder
import com.janus.deprem.util.Status
import javax.inject.Inject

class LocationRepository @Inject constructor() {
    // use mock data for demonstration purposes
    private var locations: List<Vehicle> = listOf(
        Vehicle("41.01","28.97","34ZRR34","Su an buradasiniz","41.01","28.97","Ahmet Zorer"),
        Vehicle("41.11","28.87","1","İbb yardım","41.11","28.87","Erzak Deposu"),
        Vehicle("41.21","28.57","1","İbb yardım","41.21","28.57","Bez Deposu"),
        Vehicle("41.31","28.27","1","İbb yardım","41.31","28.27","Toplanma Yeri")
    )
    private lateinit var currentVehicle: Vehicle

    fun save(dataHolder: DataHolder<List<Vehicle>>): Status {
        when (dataHolder) {
            is DataHolder.Success -> {
                dataHolder.data.let {
                    locations = it
                    if (!it.isNullOrEmpty()) {
                        it.forEach {
                            if (it.isMe()) {
                                currentVehicle = it
                            }
                        }
                    }
                    return Status.FINISHED
                }
            }
            is DataHolder.Error -> {
                return Status.FAILED.withReason(dataHolder.error.message)
            }
        }
    }

    fun getCurrentVehicle(): Vehicle {
        return currentVehicle
    }

    fun getVehicles(): List<Vehicle> {
        return locations
    }
}

fun Vehicle.isMe(): Boolean {
    return TextUtils.equals(vehicleId, "34ZRR34")
}