package com.devdk.whetherinfo.domain.modal

data class WhetherDetailModal(
    val pngURL : String = "" ,
    val condition : String = "" ,
    val temp : String = "" ,
    val place : String = "" ,
    val time : String = ""
)
