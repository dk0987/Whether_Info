package com.devdk.whetherinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devdk.whetherinfo.presentation.mainscreen.*
import com.devdk.whetherinfo.presentation.mainscreen.MainScreen
import com.devdk.whetherinfo.presentation.ui.theme.WhetherInfoTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhetherInfoTheme {
               MainScreen()
            }

        }
    }
}
