package com.example.composetask.home.presentation.home

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composetask.compose.HandleError
import com.example.composetask.compose.HandleLoading
import com.example.composetask.compose.ShowToast
import com.example.composetask.compose.SpacerHeight
import com.example.composetask.core.presentation.base.BaseState
import com.example.composetask.core.presentation.common.SharedPrefs
import com.example.composetask.home.data.responseremote.ClassNamer
import com.example.composetask.home.data.responseremote.ModelGetMedicinesResponseRemote
import com.example.composetask.home.data.responseremote.Problem
import com.example.composetask.home.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    sharedPrefs: SharedPrefs,
    homeViewModel: HomeViewModel = hiltViewModel(),
    isConnectedForMenu:Boolean
) {
    val homeState by homeViewModel.homeState.collectAsState()

    val context = LocalContext.current

    if(!isConnectedForMenu){
        homeViewModel.getMedicinesFromRoom()
    }

    handleStateChange(homeState, context, sharedPrefs) {
        homeViewModel.insertProblems(it)
    }
}

//region handleStateChange()
@Composable
private fun handleStateChange(
    homeState: BaseState<ModelGetMedicinesResponseRemote>,
    context: Context,
    sharedPrefs: SharedPrefs,
    invokeMedicines: (List<Problem>) -> Unit
) {
    when (homeState) {
        is BaseState.Init -> Unit
        is BaseState.IsLoading -> HandleLoading(homeState.isLoading)
        is BaseState.ShowToast -> ShowToast(
            context,
            homeState.message,
            homeState.isConnectionError
        )

        is BaseState.Success -> handleSuccess(homeState.items, sharedPrefs) {
            invokeMedicines(it)
        }

        is BaseState.Error -> HandleError(
            code = homeState.code,
            message = homeState.message,
            context
        )
    }

}

@Composable
private fun handleSuccess(
    data: ModelGetMedicinesResponseRemote?,
    sharedPrefs: SharedPrefs,
    invokeMedicines: (List<Problem>) -> Unit
) {
    LoginContent(
        data?.problems?.get(0)?.Diabetes?.get(0)?.medications?.get(0)?.medicationsClasses?.get(
            0
        )?.className,
        data?.problems?.get(0)?.Diabetes?.get(0)?.medications?.get(0)?.medicationsClasses?.get(
            0
        )?.className2, sharedPrefs
    )
    data?.problems?.let { invokeMedicines(it) }
}

//endregion

@Composable
private fun LoginContent(
    medicines: List<ClassNamer>?,
    medicines2: List<ClassNamer>?,
    sharedPrefs: SharedPrefs
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(vertical = 25.dp, horizontal = 30.dp)
    ) {

        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 10.dp, horizontal = 7.dp)
                .border(
                    BorderStroke(2.dp, SolidColor(Color.Gray)),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.dp),
                text =
                "Welcome for your time Mr. ${sharedPrefs.getUser()?.emailOrUserName}",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        LazyColumn() {
            if (medicines != null) {
                items(medicines) {
                    MedicineCard(it, null)
                }
            }
        }

        LazyColumn() {
            if (medicines2 != null) {
                items(medicines2) {
                    MedicineCard(null, it)
                }
            }
        }

    }
}

@Composable
private fun MedicineCard(className: ClassNamer?, className2: ClassNamer?) {

    Column(modifier = Modifier.fillMaxWidth()) {

        SpacerHeight(height = 10.dp)

        Text(
            modifier = Modifier.fillMaxSize(),
            text = "doze name : ${
                if (className == null) {
                    className2?.associatedDrug?.get(0)?.name
                } else {
                    className.associatedDrug?.get(0)?.name
                }
            }",
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.fillMaxSize(),
            text = "doze strength = ${
                if (className == null) {
                    className2?.associatedDrug?.get(0)?.strength
                } else {
                    className.associatedDrug?.get(0)?.strength
                }
            }",
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )

        SpacerHeight(height = 3.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.Gray)
        )

    }
}