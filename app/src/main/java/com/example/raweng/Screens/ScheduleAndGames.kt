package com.example.raweng.Screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.raweng.Screens.Components.TabbedView
import com.example.raweng.TabData
import com.example.raweng.schedules
import com.example.raweng.viewmodelRaw
import com.google.accompanist.pager.ExperimentalPagerApi

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScheduleAndGames(name: String,navController: NavHostController,viewmodelRaw: viewmodelRaw) {

    val scheduleslist = schedules.data.schedules

    val viewTabs= listOf(
        TabData("Schedule",navController) {

            MonthlyLazyColumn(scheduleslist, viewmodel = viewmodelRaw)

        },
        TabData("Games",navController){
           Games(viewmodel = viewmodelRaw)
        }
    )

    TabbedView(viewTabs)

}


