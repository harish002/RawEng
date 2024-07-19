package com.example.raweng.Screens.Components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalAccessibilityManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.raweng.PromotionCard
import com.example.raweng.games
import com.example.raweng.schedules
import com.example.raweng.teams
import com.example.raweng.viewmodelRaw

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PastCard() {
    val teamMap = teams.data.teams.associateBy { it.tid }
    val pastgame = schedules.data.schedules.get(20)

    GameCard(
        backgroundImageUrl = "https://images.contentstack.io/v3/assets/blt787bc9d35cf46301/blt11706bd1cad23e04/66853bcfb84bce7df708c9dd/past_game_card_new.png",
        teamLogos = listOf(
            teamMap[pastgame.v.tid]?.logo,
            teamMap[pastgame.h.tid]?.logo
        ),
        teamText = "random",
        dateTimeText = "",
        countdownText = emptyList(),
        isUpcoming = false,
        isPast = true,
        pastScore = listOf(pastgame.v.s,pastgame.h.s )
    )
}

@Composable
fun TeamInfo(
    imageUrl: String,
    teamName: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .scale(Scale.FIT)
                .crossfade(true)
                .data(imageUrl)
                .build(),
            contentDescription = "Image",
            contentScale = ContentScale.FillBounds,
            loading = {
                CircularProgressIndicator(0.1f)
            },
            modifier = Modifier.size(50.dp)
        )
        Text(
            text = teamName,
            modifier = Modifier
                .padding(vertical = 10.dp),
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Composable
fun PromotionCard(pCard: PromotionCard){
    val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f)
                .padding(16.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (card, rowValues, button, years) = createRefs()
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .constrainAs(card) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                    shape = RoundedCornerShape(8)
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .scale(Scale.FILL)
                            .crossfade(true)
                            .data(pCard.card.first().background_image.url)
                            .build(),
                        contentDescription = "Image",
                        contentScale = ContentScale.FillBounds,
                        loading = {
                            CircularProgressIndicator(0.1f)
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(
                    text = pCard.card.first().title,
                    maxLines = 2,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .constrainAs(rowValues) {
                            top.linkTo(card.top, margin = 150.dp)
                            bottom.linkTo(years.top)
                            start.linkTo(card.start, margin = 16.dp)
                        },
                )
                Text(
                    text = "2023/24",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .constrainAs(years) {
                            top.linkTo(rowValues.bottom)
                            bottom.linkTo(button.top, margin = 8.dp)
                            start.linkTo(card.start, margin = 16.dp)
                            end.linkTo(card.end)
                            width = Dimension.fillToConstraints
                        },
                )

                TextButton(
                    onClick = {
                        val urlIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www." + pCard.card.first().button.cta_link)
                        )
                        context.startActivity(urlIntent)

                    },
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(45))
                        .background(Color(0XFFFFA500))
                        .constrainAs(button) {
                            top.linkTo(years.bottom)
                            bottom.linkTo(card.bottom)
                            start.linkTo(card.start, margin = 15.dp)
                            end.linkTo(card.end, margin = 15.dp)
                            width = Dimension.fillToConstraints
                        },
                ) {
                    Text(
                        text = pCard.card.first().button.cta_text,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }

}

@Composable
fun UpcomingCard(){
    GameCard(
        backgroundImageUrl = "https://images.contentstack.io/v3/assets/blt787bc9d35cf46301/blt7cec778ffeda1128/66853bcf62008a1d5910c932/upcoming_game_card_new.png",
        teamLogos = listOf(
            "https://images.contentstack.io/v3/assets/bltf0f8f301b0c60428/blt064349feb49d5574/636ccef3e7ed9210982321a0/download",
            "https://images.contentstack.io/v3/assets/bltf0f8f301b0c60428/blt064349feb49d5574/636ccef3e7ed9210982321a0/download"
        ),
        teamText = "MIA VS SCH",
        dateTimeText = "HOME | SAT OCT 05 | 7:00 PM",
        countdownText = listOf("02\nDAYS", "21\n HRS", "57\n MIN"),
        isUpcoming = true,
        isPast = false
    )
}

@Composable
fun FutureCard(viewmodelRaw: viewmodelRaw){
    val teamMap = teams.data.teams.associateBy { it.tid }
    val futuregame = schedules.data.schedules.get(1)

        GameCard(
            backgroundImageUrl = "https://images.contentstack.io/v3/assets/blt787bc9d35cf46301/bltb55816cc5c3ea465/6655b09289ebe633fc0ab3f2/future_game_card.png",
            teamLogos = listOf(
                teamMap[futuregame.v.tid]?.logo,
                teamMap[futuregame.h.tid]?.logo
            ),
            teamText = futuregame.v.ta+" @ "+futuregame.h.ta,
            dateTimeText =  "AWAY |"+
                    futuregame.stt.uppercase()+
                    "| ${viewmodelRaw.formatMonthDayYear(futuregame.gametime)}",
            countdownText = listOf("02\nDAYS", "21\n HRS", "57\n MIN"),
            isUpcoming = false,
            isPast = false
        )
}

@Composable
fun GameCard(
    backgroundImageUrl: String,
    teamLogos: List<String?>,
    teamText: String,
    dateTimeText: String,
    countdownText: List<String>,
    isUpcoming: Boolean,
    isPast: Boolean,
    isPromotion: Boolean = false,  // New parameter to check if it's a Promotion card
    pastScore: List<String> = emptyList()
) {
    val context = LocalContext.current
    if (isPromotion) {
        val promotionCards = games.entry.promotion_cards
        for (card in promotionCards) {
            PromotionCard(card)
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f)
                .padding(16.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (card, rowValues, countdown, button,
                    logos, teamAcro, dateTime) = createRefs()

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .constrainAs(card) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                    shape = RoundedCornerShape(8)
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .scale(Scale.FILL)
                            .crossfade(true)
                            .data(backgroundImageUrl)
                            .build(),
                        contentDescription = "Background Image",
                        contentScale = ContentScale.FillBounds,
                        loading = {
                            CircularProgressIndicator(0.1f)
                        },
                        modifier = Modifier.fillMaxSize()
                            .background(if (!isUpcoming && !isPast) MaterialTheme.colorScheme.background else Color.Transparent)
                    )
                }

                if (!isPast) {
                    Row(
                        modifier = Modifier
                            .constrainAs(logos) {
                                top.linkTo(card.top, margin = 15.dp)
                                bottom.linkTo(teamAcro.top)
                                start.linkTo(card.start, margin = 15.dp)
                                end.linkTo(card.end)
                                width = Dimension.fillToConstraints
                            }
                    ) {
                        teamLogos.forEach { logoUrl ->
                            SubcomposeAsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .scale(Scale.FILL)
                                    .crossfade(true)
                                    .data(logoUrl)
                                    .build(),
                                contentDescription = "Team Logo",
                                contentScale = ContentScale.FillBounds,
                                loading = {
                                    CircularProgressIndicator(0.1f)
                                },
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }

                    Text(
                        text = teamText,
                        color = if (isUpcoming) Color.Black else Color.White,
                        modifier = Modifier
                            .constrainAs(teamAcro) {
                                top.linkTo(logos.bottom)
                                bottom.linkTo(dateTime.top)
                                start.linkTo(card.start, margin = 15.dp)
                                end.linkTo(card.end)
                                width = Dimension.fillToConstraints
                            },
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    Text(
                        text = dateTimeText,
                        color = if (isUpcoming) Color.Black else Color.White,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .constrainAs(dateTime) {
                                top.linkTo(teamAcro.bottom)
                                bottom.linkTo(countdown.top)
                                start.linkTo(card.start, margin = 15.dp)
                                end.linkTo(card.end)
                                width = Dimension.fillToConstraints
                            }
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                            .clip(RoundedCornerShape(15))
                            .background(Color.Black.copy(alpha = .7f))
                            .constrainAs(countdown) {
                                top.linkTo(dateTime.bottom, margin = if (isUpcoming) 100.dp else 80.dp)
                                bottom.linkTo(if (isUpcoming) button.top else card.bottom)
                                start.linkTo(card.start, margin = 15.dp)
                                width = Dimension.fillToConstraints
                            }
                    ) {
                        countdownText.forEachIndexed { index, text ->
                            Text(
                                text = text,
                                color = Color.White,
                                modifier = Modifier.padding(2.dp),
                                textAlign = TextAlign.Center
                            )
                            if (index < countdownText.size - 1) {
                                Divider(
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .align(Alignment.CenterVertically)
                                        .width(1.dp)
                                )
                            }
                        }
                    }

                    if (isUpcoming) {
                        TextButton(
                            onClick = {},
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .clip(RoundedCornerShape(45))
                                .background(Color(0XFFFFA500))
                                .constrainAs(button) {
                                    bottom.linkTo(card.bottom)
                                    start.linkTo(card.start, margin = 15.dp)
                                    end.linkTo(card.end, margin = 15.dp)
                                    width = Dimension.fillToConstraints
                                }
                        ) {
                            Text(
                                text = "BUY TICKETS ON ticketmaster",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .constrainAs(rowValues) {
                                top.linkTo(card.top, margin = 150.dp)
                                bottom.linkTo(button.top)
                                start.linkTo(card.start)
                                end.linkTo(card.end)
                                width = Dimension.fillToConstraints
                            },
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        teamLogos[0]?.let {
                            TeamInfo(
                                imageUrl = it,
                                teamName = "SCH"
                            )
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        Text(
                            text = pastScore[0],
                            modifier = Modifier
                                .padding(5.dp, vertical = 10.dp),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        Text(
                            text = "VS",
                            modifier = Modifier
                                .padding(vertical = 10.dp),
                            fontStyle = FontStyle.Italic,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        Text(
                            text = pastScore[1],
                            modifier = Modifier
                                .padding(5.dp, vertical = 10.dp),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        teamLogos[1]?.let {
                            TeamInfo(
                                imageUrl = it,
                                teamName = "MIA"
                            )
                        }
                    }

                    TextButton(
                        onClick = {
                            val urlIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.google.com")
                            )
                            context.startActivity(urlIntent)
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(45))
                            .background(Color(0XFFFFA500))
                            .padding(horizontal = 30.dp)
                            .constrainAs(button) {
                                top.linkTo(rowValues.bottom)
                                bottom.linkTo(card.bottom)
                                start.linkTo(card.start, margin = 15.dp)
                                end.linkTo(card.end, margin = 15.dp)
                                width = Dimension.fillToConstraints
                            },
                    ) {
                        Text(
                            text = "GAME RECAP",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}







