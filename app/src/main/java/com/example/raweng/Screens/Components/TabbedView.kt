package com.example.raweng.Screens.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.raweng.ui.theme.RawEngTheme
import com.example.raweng.TabData


@Composable
fun TabbedView(tabItems: List<TabData>) {
    var selectedTab by
    remember { mutableStateOf(tabItems.firstOrNull() ?: tabItems[0]) }

    RawEngTheme{


        Column() {
            // TabRow with tabs
            Text(text = "TEAM",
                Modifier.fillMaxWidth()
                    .padding(vertical = 10.dp),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
                color = Color.White,
                )
            TabRow(
                selectedTabIndex = tabItems.indexOf(selectedTab),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20)),
                containerColor = Color.Transparent,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(
                            tabPositions[tabItems.indexOf(selectedTab)]
                        ),
                        color = Color(0xFFFFDB58)
                    )

                }
            ) {

                tabItems.forEachIndexed { index, tab ->
                    Tab(
                        text = { Text(text = tab.title,
                            color = MaterialTheme.colorScheme.primary) },
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        modifier = Modifier
                            .clip(RoundedCornerShape(20))
                            .background(
                                Color.Transparent
                            )
                    )
                }
            }
            // Content for each tab
            selectedTab.content()
        }
    }
}
