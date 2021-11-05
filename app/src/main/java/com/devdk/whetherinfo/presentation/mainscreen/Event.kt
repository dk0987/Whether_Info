package com.devdk.whetherinfo.presentation.mainscreen

sealed class Event{
    data class onItemClick(val text : String) : Event()
    data class onDropDownWidthChange(val size : Int) : Event()
    object onIconClick : Event()
    object onDismissRequest : Event()
}
