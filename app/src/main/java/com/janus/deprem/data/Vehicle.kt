package com.janus.deprem.data

import com.google.gson.annotations.SerializedName

data class Vehicle(
    @SerializedName("vehicle_destination_latitude")
    val vehicleDestinationLatitude: String,
    @SerializedName("vehicle_destination_longitude")
    val vehicleDestinationLongitude: String,
    @SerializedName("vehicle_id")
    val vehicleId: String,

    @SerializedName("vehicle_description")
    val vehicleDescription: String,

    @SerializedName("vehicle_location_latitude")
    val vehicleLocationLatitude: String,
    @SerializedName("vehicle_location_longitude")
    val vehicleLocationLongitude: String,
    @SerializedName("vehicle_name")
    val vehicleName: String
)