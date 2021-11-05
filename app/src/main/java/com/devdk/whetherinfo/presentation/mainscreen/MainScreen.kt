package com.devdk.whetherinfo.presentation.mainscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.ImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import com.devdk.whetherinfo.R
import com.devdk.whetherinfo.data.remote.dto.ConditionXX
import com.devdk.whetherinfo.data.remote.dto.Hour
import com.devdk.whetherinfo.domain.modal.ForecastModal
import com.devdk.whetherinfo.presentation.mainscreen.component.HourWhether
import com.devdk.whetherinfo.presentation.mainscreen.component.StandardDropDown
import com.devdk.whetherinfo.presentation.ui.theme.Background
import com.devdk.whetherinfo.presentation.ui.theme.PrimarySky
import com.devdk.whetherinfo.presentation.ui.theme.PrimaryText
import com.devdk.whetherinfo.presentation.ui.theme.SecondarySky

@ExperimentalFoundationApi
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val png = "https:${viewModel.states.value.whetherDetail.pngURL}"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        PrimarySky, SecondarySky
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
            .padding(top = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(top = 10.dp, start = 18.dp)
                .align(Alignment.TopStart)
                .border(width = 0.5.dp, color = PrimaryText, shape = RoundedCornerShape(15.dp)),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = stringResource(id = R.string.loaction)
            )
            StandardDropDown(
                viewModel = viewModel
            )
        }
        Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 80.dp)
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(170.dp),
                painter = rememberImagePainter(data = png),
                contentDescription = viewModel.states.value.whetherDetail.condition
            )
            Text(
                text = viewModel.states.value.whetherDetail.condition,
                color = PrimaryText,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = viewModel.states.value.whetherDetail.temp + "c",
                fontWeight = FontWeight.Bold,
                fontSize = 55.sp,
                color = PrimaryText
            )
            Text(
                text = viewModel.states.value.whetherDetail.place,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = PrimaryText
            )
            Text(
                text = viewModel.states.value.whetherDetail.time,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = PrimaryText
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 140.dp)
            ) {
                items(viewModel.states.value.hour) { hour ->
                    HourWhether(hour)
                }
            }
        }
    }

    if (viewModel.states.value.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            PrimarySky, SecondarySky
                        ),
                        start = Offset.Zero,
                        end = Offset.Infinite
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.Green)
        }
    }
    if (viewModel.states.value.error.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            PrimarySky, SecondarySky
                        ),
                        start = Offset.Infinite,
                        end = Offset.Infinite
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = viewModel.states.value.error, color = Color.Red)
        }
    }
}

