package com.example.raweng.Screens.Components.CardComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.core.view.ContentInfoCompat.Flags
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.raweng.Schedule
import com.example.raweng.teams

@Composable
fun AwayTeam(tid: String, schedule: Schedule, isFuture: Boolean,
             flag: Boolean?//for align Away team ACr properly
) {
    val teamMap = teams.data.teams.associateBy { it.tid }


    if(!isFuture){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .scale(Scale.FIT)
                    .crossfade(true)
                    .data(teamMap[tid]?.logo)
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
                loading = {
                    CircularProgressIndicator(0.1f)
                },
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = teamMap[tid]?.ta.toString(),
                modifier = Modifier
                    .padding(vertical = 10.dp),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = Color.White,
            )
        }
    }else{
        Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
            if(flag != true) {
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
            Spacer(modifier = Modifier.padding(horizontal=4.dp))
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .scale(Scale.FILL)
                    .crossfade(true)
                    .data(teamMap[tid]?.logo)
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
                loading = {
                    CircularProgressIndicator(0.1f)
                },
                modifier = Modifier.size(50.dp)
            )
        Spacer(modifier = Modifier.padding(horizontal=4.dp))
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
    }
    }
    if(!isFuture){
        schedule?.v?.s?.let {
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
}