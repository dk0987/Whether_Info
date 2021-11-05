package com.devdk.whetherinfo.presentation.mainscreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.devdk.whetherinfo.R
import com.devdk.whetherinfo.data.remote.dto.Hour
import com.devdk.whetherinfo.domain.modal.ForecastModal
import com.devdk.whetherinfo.presentation.ui.theme.PrimarySky
import com.devdk.whetherinfo.presentation.ui.theme.PrimaryText

@Composable
fun HourWhether(
   forecastModal: ForecastModal
) {
   val png = "https:${forecastModal.pngURL}"
   Card(modifier = Modifier
      .width(120.dp)
      .height(180.dp)
      .padding(10.dp),
      elevation = 10.dp,
      shape = RoundedCornerShape(10.dp)
   ) {
      Column(
         modifier = Modifier
            .fillMaxSize()
            .background(PrimarySky)
            .padding(start = 5.dp , end = 5.dp),
         verticalArrangement = Arrangement.SpaceEvenly,
         horizontalAlignment = Alignment.CenterHorizontally
      ){
         Text(
            text = forecastModal.time,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Normal,
            color = PrimaryText
         )
         Icon(
            modifier= Modifier.size(50.dp),
            painter = rememberImagePainter(data = png),
            contentDescription = forecastModal.condition,
         )
         Text(
            text = forecastModal.temp + "c",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText
         )
         Text(
            text = forecastModal.condition,
            fontSize = 18.sp,
            maxLines = 1 ,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryText
         )
      }
   }
}