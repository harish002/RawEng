package com.example.raweng.Screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.raweng.Schedule
import com.example.raweng.Screens.Components.FutureGame
import com.example.raweng.Screens.Components.LiveGame
import com.example.raweng.Screens.Components.PastGame
import com.example.raweng.viewmodelRaw
import java.text.SimpleDateFormat

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MonthlyLazyColumn(
    scheduleslist:List<Schedule>,
    modifier: Modifier = Modifier,
    viewmodel: viewmodelRaw
) {

    val sortedSchedules = scheduleslist.sortedByDescending {viewmodel.parseDateTime(it.gametime)}

    val uniqueMonths = sortedSchedules.map {
        viewmodel.getMonthFromDateString(it.gametime) }.distinct()
    Log.d("uniqueMonths",uniqueMonths.toString())

    val groupedItems = uniqueMonths.associateWith { month ->
        sortedSchedules.filter {
            viewmodel.getMonthFromDateString(it.gametime) == month
        }
    }

    Log.d("listval",groupedItems.values.toString())
    val currentMonth = remember { mutableStateOf("") }
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = rememberLazyListState()
    ) {
        groupedItems.forEach { (month, itemsForMonth) ->
            stickyHeader {
                MonthHeader((month.uppercase()))
                currentMonth.value = month

            }
            items(itemsForMonth) { item ->
                ItemRow(item,
                    context=context,
                    viewmodel = viewmodel)
            }
            Log.d("scheduleListSIZE",itemsForMonth.size.toString())
        }
    }
}


@Composable
fun MonthHeader(
    month: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp)
    ) {
        Text(
            text = month,
            modifier= Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ItemRow(
    schedule: Schedule,
    modifier: Modifier = Modifier,
    viewmodel: viewmodelRaw,
    context: Context
) {
//    val result = viewmodel.compareDates(schedule.gametime,viewmodel.getLocalDateTimeString() )
    when (schedule.st) {
        1-> FutureGame(schedule,context = context,
                         viewmodelRaw = viewmodel)
        2 -> LiveGame(schedule,context = context, viewmodelRaw = viewmodel)
        3 -> PastGame(schedule = schedule,
                        context = context,
                        viewmodelRaw = viewmodel)
    }

}

