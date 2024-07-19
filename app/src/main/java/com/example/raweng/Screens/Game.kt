package com.example.raweng.Screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.raweng.Screens.Components.AutoSlidingCarousel
import com.example.raweng.Screens.Components.FutureCard
import com.example.raweng.Screens.Components.PastCard
import com.example.raweng.Screens.Components.PromotionCard
import com.example.raweng.Screens.Components.UpcomingCard
import com.example.raweng.games
import com.example.raweng.schedules
import com.example.raweng.viewmodelRaw
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Games(viewmodel: viewmodelRaw
){
    val schedulelist = schedules.data.schedules
    val pastGames= schedulelist.filter { it.st == 3 }.map { it.uid }

    val promotionCards = games.entry.promotion_cards
    val focus_Card = games.entry.game_card_config.focus_card
    val items: List<@Composable () -> Unit> = remember {
        mutableListOf<@Composable () -> Unit>().apply {
            add { PastCard() }
            add { FutureCard(viewmodel) }
            add { UpcomingCard() }
            // promotion cards dynamically
            promotionCards.forEach { card ->
                add { PromotionCard(card) }
            }
        }
    }

    AutoSlidingCarousel(

        itemsCount = items.size,
        focusCard = focus_Card
    ) { index ->
        items[index]()
    }
}