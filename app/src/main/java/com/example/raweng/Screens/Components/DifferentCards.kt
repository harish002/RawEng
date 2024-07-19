package com.example.raweng.Screens.Components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.raweng.Schedule
import com.example.raweng.Screens.Components.CardComponents.AwayTeam
import com.example.raweng.Screens.Components.CardComponents.HomeTeam
import com.example.raweng.id
import com.example.raweng.viewmodelRaw


@Composable
fun LiveGame(schedule: Schedule,
                 context:Context,viewmodelRaw: viewmodelRaw){
    Card(
        Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        ),
        shape = RoundedCornerShape(15)
    ) {
        Column(
            Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = "${schedule.stt.uppercase()} | " +
                    "${viewmodelRaw.formatMonthDayYear(schedule.gametime)}|"+
                    "${viewmodelRaw.getTimeFromDateString(schedule.gametime)}",
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center)

            Row(modifier= Modifier
                .fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly) {

                    AwayTeam(schedule.v.tid,schedule,false,schedule.h.tid == id)
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Surface(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .wrapContentWidth()
                            .align(Alignment.CenterHorizontally),
                        shape = CircleShape,
                        color = Color.Black.copy(alpha = 0.5f), //For indicator background
                    ) {
                        Text(
                            text = "LIVE",
                            modifier =Modifier.padding(horizontal = 4.dp),
                            color = Color.White,
                            )
                    }
                    Text(
                        text = "VS",
                        modifier = Modifier
                            .padding(vertical = 10.dp),
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
                    HomeTeam(schedule.h.tid,schedule,false,schedule.h.tid == id)

            }
        }
    }
}
@Composable
fun PastGame(schedule: Schedule,context:Context,
             viewmodelRaw: viewmodelRaw){
    Card(
        Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        ),
        shape = RoundedCornerShape(15)
    ) {
        Column(
            Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = "${schedule.stt.uppercase()} | " +
                    "${viewmodelRaw.formatMonthDayYear(schedule.gametime)} | " +
                    "${viewmodelRaw.getTimeFromDateString(schedule.gametime)}",
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center)

            Row(modifier= Modifier
                .clickable {
                    if(schedule.buy_ticket_url.isNullOrBlank()){
                        Toast.makeText(context, "URL IS NULL", Toast.LENGTH_SHORT).show()

                    }
                    else{
                        val urlIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www." + schedule.buy_ticket_url)
                        )
                        context.startActivity(urlIntent)
                    }
                }
                .fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly) {

                AwayTeam(schedule.v.tid, schedule, false,schedule.h.tid == id)

                Text(text = if(id==schedule.h.tid) "VS" else "@",
                    modifier= Modifier
                        .padding(vertical = 10.dp),
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

                HomeTeam(schedule.h.tid,schedule,false,schedule.h.tid == id)
            }
        }
    }
}

@Composable
fun FutureGame(schedule: Schedule,
               viewmodelRaw: viewmodelRaw,
               context: Context){
    Card(
        Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        ),
        shape = RoundedCornerShape(15)
    ) {
        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = if(schedule.h.tid == id) "HOME | "+
                            schedule.stt.uppercase() +
                            " ${viewmodelRaw.formatMonthDayYear(schedule.gametime)} |" +
                            " ${viewmodelRaw.getTimeFromDateString(schedule.gametime)} "
                         else "AWAY |"+
                         schedule.stt.uppercase()+
                        "| ${viewmodelRaw.formatMonthDayYear(schedule.gametime)}",
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center)

            Row(modifier= Modifier
                .fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly) {
                if(schedule.h.tid == id)
                    AwayTeam(schedule.v.tid,schedule,true,schedule.h.tid == id)
                else
                    HomeTeam(schedule.v.tid,schedule,true,schedule.h.tid == id)

                Text(text = if(schedule.h.tid == id) "VS" else "@",
                    modifier= Modifier
                        .padding(vertical = 10.dp),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                if(schedule.h.tid == id)
                    HomeTeam(schedule.h.tid,schedule,true,schedule.h.tid == id)
                else
                    AwayTeam(schedule.h.tid,schedule,true,schedule.h.tid == id)

            }
            TextButton(
                onClick = {
                    if(schedule.buy_ticket_url.isNullOrBlank()){
                        Toast.makeText(context, "URL IS NULL", Toast.LENGTH_SHORT).show()

                    }
                    else{
                        val urlIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www." + schedule.buy_ticket_url)
                        )
                        context.startActivity(urlIntent)
                    }
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(45))
                    .background(Color.White)
                    .padding(horizontal = 50.dp),
            ) {
                Text(text = "BUY TICKET ON ",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
                Text(text = "ticketmaster",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,)
            }
        }
    }
}

@Preview
@Composable
fun precard(){
//    FutureGame()
}