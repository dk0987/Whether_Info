package com.devdk.whetherinfo.data.remote.dto

data class Location(
    val country: String,
    val lat: Double,
    val localtime: String ,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String ,
    val region: String
)
