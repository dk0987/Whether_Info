package com.devdk.whetherinfo.data.remote.dto

import com.devdk.whetherinfo.domain.modal.WhetherDetailModal

data class WhetherDto(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)

fun WhetherDto.toWhetherDetailModal() : WhetherDetailModal {
    return WhetherDetailModal(
        pngURL = current.condition.icon,
        condition = current.condition.text ,
        temp = current.temp_c.toString() ,
        place = location.region +" , " + location.country,
        time = location.localtime ,
    )
}
