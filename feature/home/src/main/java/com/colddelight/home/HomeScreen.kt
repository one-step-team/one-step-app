package com.colddelight.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.colddelight.designsystem.component.StepButton

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onStartButtonClick: (Int) -> Unit
){

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ){ padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text(
                text = "Home",
            )
            StepButton(onClick = {
                onStartButtonClick(1)
            } ) {
                Text(
                    text = stringResource(R.string.start),
                )
            }
        }
    }
}