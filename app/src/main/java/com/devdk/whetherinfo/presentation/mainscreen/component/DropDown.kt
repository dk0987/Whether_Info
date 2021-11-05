package com.devdk.whetherinfo.presentation.mainscreen.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devdk.whetherinfo.presentation.mainscreen.Event
import com.devdk.whetherinfo.presentation.mainscreen.MainScreenViewModel
import com.devdk.whetherinfo.presentation.ui.theme.PrimarySky
import com.devdk.whetherinfo.presentation.ui.theme.PrimaryText

@ExperimentalFoundationApi
@Composable
fun StandardDropDown(
    viewModel: MainScreenViewModel,
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            BasicTextField(
                value = viewModel.states.value.selectedText,
                onValueChange = { },
                modifier = Modifier
                    .onSizeChanged {
                        viewModel.states.value.dropDownWidth = it.width
                    }
                    .width(100.dp),
                readOnly = true,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            IconButton(onClick = {viewModel.onEvent(Event.onIconClick)}) {
                Icon(imageVector = viewModel.states.value.icon, contentDescription = "DropDownMenu" )
            }
        }

        DropdownMenu(
            expanded = viewModel.states.value.expanded,
            onDismissRequest = { viewModel.onEvent(Event.onDismissRequest) },
            modifier = Modifier
                .background(PrimarySky)
                .width(with(LocalDensity.current){viewModel.states.value.dropDownWidth.toDp()}),
        ) {
            viewModel.states.value.list.forEach { label ->
                DropdownMenuItem(
                    modifier = Modifier.background(PrimarySky),
                    onClick = {
                    viewModel.onEvent(Event.onItemClick(label))
                },
            ) {
                    Text(
                        text = label,
                        color = PrimaryText,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
}
