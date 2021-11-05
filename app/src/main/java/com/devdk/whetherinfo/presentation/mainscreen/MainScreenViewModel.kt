package com.devdk.whetherinfo.presentation.mainscreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdk.whetherinfo.common.Resource
import com.devdk.whetherinfo.data.remote.dto.toForecastModal
import com.devdk.whetherinfo.data.remote.dto.toWhetherDetailModal
import com.devdk.whetherinfo.domain.useCases.GetDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
  private val getDetail : GetDetail
) : ViewModel() {

    private val _state = mutableStateOf(States())
    val states : State<States> = _state

    init {
        getDetail(states.value.selectedText)
    }

    fun onEvent(event: Event) {
       when(event){
            is Event.onItemClick -> {
                _state.value = states.value.copy(
                    selectedText = event.text,
                    expanded = false
                )
                getDetail(states.value.selectedText)
            }
            is Event.onIconClick -> {
                _state.value = states.value.copy(
                    expanded = !states.value.expanded,
                    icon = if (states.value.expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                )
            }
            is Event.onDismissRequest -> {
                _state.value = states.value.copy(
                    expanded = false
                )
            }
        }
    }

    private fun getDetail(country : String){
        getDetail.invoke(country).onEach {result ->
            when(result){
                is Resource.Success ->{
                    _state.value = result.data?.let { it ->
                        states.value.copy(
                            whetherDetail = it.toWhetherDetailModal(),
                            isLoading = false,
                            hour = it.forecast.forecastday[0].hour.let {
                                it.map {
                                    it.toForecastModal()
                                }
                            }
                        )
                    }!!
                }
                is Resource.Loading -> {
                    _state.value = states.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _state.value = states.value.copy(
                        isLoading = false,
                        error = result.message.toString()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}