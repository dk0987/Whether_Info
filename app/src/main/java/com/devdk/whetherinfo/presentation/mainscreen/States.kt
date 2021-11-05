package com.devdk.whetherinfo.presentation.mainscreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.ui.graphics.vector.ImageVector
import com.devdk.whetherinfo.data.remote.dto.Hour
import com.devdk.whetherinfo.domain.modal.ForecastModal
import com.devdk.whetherinfo.domain.modal.WhetherDetailModal

data class States(
    var expanded : Boolean = false,
    val error : String = "",
    val isLoading : Boolean = false ,
    val list: List<String> = listOf(
        "India",
        "USA",
        "London",
        "China",
        "Russia",
        "Germany"
    ),
    val selectedText : String = list[0],
    var dropDownWidth : Int = 0,
    val icon: ImageVector = Icons.Default.KeyboardArrowDown ,
    val whetherDetail : WhetherDetailModal = WhetherDetailModal(),
    val hour: List<ForecastModal> = emptyList()
)