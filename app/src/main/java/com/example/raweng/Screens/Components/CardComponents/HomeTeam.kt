package com.example.raweng.Screens.Components.CardComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.raweng.Schedule
import com.example.raweng.teams

@Composable
fun HomeTeam(tid: String,schedule: Schedule,isFuture: Boolean,flag:Boolean?) {
    val teamMap = teams.data.teams.associateBy { it.tid }

    if(!isFuture) {
        schedule?.h?.s?.let {
            Text(
                text = it,
                modifier = Modifier
                    .padding(5.dp, vertical = 10.dp),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
    if(!isFuture){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.size(50.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .scale(Scale.FIT)
                    .crossfade(true)
                    .data(teamMap[tid]?.logo)
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
                loading = {
                    CircularProgressIndicator(0.1f)
                }
            )
            teamMap[tid]?.ta?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(vertical = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }
        }
    }else{
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if(flag == true) {
                teamMap[tid]?.ta?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(vertical = 10.dp),
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                    )
                }
            }
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))

            SubcomposeAsyncImage(
                modifier = Modifier.size(50.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .scale(Scale.FIT)
                    .crossfade(true)
                    .data(teamMap[tid]?.logo)
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
                loading = {
                    CircularProgressIndicator(0.1f)
                }
            )
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            if(flag == false){
                teamMap[tid]?.ta?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(vertical = 10.dp),
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                    )
                }
            }
//            }
//            else{
//
//                SubcomposeAsyncImage(
//                    modifier = Modifier.size(50.dp),
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .scale(Scale.FIT)
//                        .crossfade(true)
//                        .data(teamMap[tid]?.logo)
//                        .build(),
//                    contentDescription = "Image",
//                    contentScale = ContentScale.FillBounds,
//                    loading = {
//                        CircularProgressIndicator(0.1f)
//                    }
//                )
//
//                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
//
//
//                teamMap[tid]?.ta?.let {
//                    Text(
//                        text = it,
//                        modifier = Modifier
//                            .padding(vertical = 10.dp),
//                        fontWeight = FontWeight.Bold,
//                        fontStyle = FontStyle.Italic,
//                        style = MaterialTheme.typography.headlineSmall,
//                        textAlign = TextAlign.Center,
//                        color = Color.White,
//                    )
//                }
//
//
//            }

        }
    }


}

@Preview
@Composable
fun preHomeTeam(){
//    HomeTeam(schedule.v.tid)
}