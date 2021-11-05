package com.devdk.whetherinfo.domain.modal

data class ForecastModal(
    val time: String = "",
    val pngURL: String = "",
    val temp: String = "",
    val condition: String =""
)
