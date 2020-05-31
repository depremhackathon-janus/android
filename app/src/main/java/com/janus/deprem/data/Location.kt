package com.janus.deprem.data

data class Location(
    var lat:Double,
    var lon:Double,
    var name:String,
    var description:String,
    var type:LocationType
)

enum class LocationType{
    CURRENT,HELP,DESTINATION
}