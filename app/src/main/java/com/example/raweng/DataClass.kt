package com.example.raweng

import androidx.compose.runtime.Composable
import androidx.navigation.NavController




//
//
//
//
// FOR UI
data  class TabData(
    val title: String,
    val navController: NavController? = null,
    val content: @Composable () -> Unit
)

data class Item(
    val schedules: Schedule,
    val month: String
)

//
//
//
// Data class for Schedule

data class Schedules(
    val `data`: Data
)

data class Data(
    val schedules: List<Schedule>
)

data class Schedule(
    val arena_city: String,
    val arena_name: String,
    val arena_state: String,
    val buy_ticket: String,
    val buy_ticket_url: String,
    val cl: String,
    val game_state: String,
    val game_subtype: String,
    val gametime: String,
    val gcode: String,
    val gid: String,
    val h: H,
    val hide: Boolean,
    val is_game_necessary: String,
    val league_id: String,
    val logo_url: Any,
    val ppdst: String,
    val season_id: String,
    val seri: String,
    val st: Int,
    val stt: String,
    val uid: String,
    val v: V,
    val year: Int
)

data class H(
    val ist_group: String,
    val re: String,
    val s: String,
    val ta: String,
    val tc: String,
    val tid: String,
    val tn: String
)

data class V(
    val ist_group: String,
    val re: String,
    val s: String,
    val ta: String,
    val tc: String,
    val tid: String,
    val tn: String
)


//
//
//
// Data class for Teams JSON
data class Teams(
    val `data`: Teams_Data
)

data class Teams_Data(
    val teams: List<Team>
)

data class Team(
    val co: String,
    val color: String,
    val di: String,
    val ist_group: String,
    val league_id: String,
    val logo: String,
    val season_id: String,
    val sta: String,
    val ta: String,
    val tc: String,
    val tid: String,
    val tn: String,
    val uid: String,
    val year: Int
)

//
//
// Data class for Games CARD


data class GameCard(
    val entry: Entry
)

data class Entry(
    val ACL: ACL,
    val _in_progress: Boolean,
    val _version: Int,
    val created_at: String,
    val created_by: String,
    val future_game: FutureGame,
    val game_card_config: GameCardConfig,
    val locale: String,
    val past_game_card: PastGameCard,
    val promotion_cards: List<PromotionCard>,
    val publish_details: List<PublishDetail>,
    val tags: List<Any>,
    val title: String,
    val uid: String,
    val upcoming_game: UpcomingGame,
    val updated_at: String,
    val updated_by: String
)

class ACL

data class FutureGame(
    val background_image: BackgroundImage,
    val button: Button
)

data class GameCardConfig(
    val focus_card: Int,
    val future_game_count: Int,
    val past_game_count: Int
)

data class PastGameCard(
    val background_image: BackgroundImageX,
    val button: ButtonX
)

data class PromotionCard(
    val _metadata: Metadata,
    val card: List<Card>,
    val position: Int
)

data class PublishDetail(
    val environment: String,
    val locale: String,
    val time: String,
    val user: String,
    val version: Int
)

data class UpcomingGame(
    val background_image: BackgroundImageXXX,
    val button: ButtonXXX
)

data class BackgroundImage(
    val ACL: List<Any>,
    val _version: Int,
    val content_type: String,
    val created_at: String,
    val created_by: String,
    val dimension: Dimension,
    val file_size: String,
    val filename: String,
    val is_dir: Boolean,
    val parent_uid: String,
    val publish_details: List<PublishDetail>,
    val tags: List<Any>,
    val title: String,
    val uid: String,
    val updated_at: String,
    val updated_by: String,
    val url: String
)

data class Button(
    val cta_link: String,
    val cta_text: String,
    val icons: Icons
)

data class Dimension(
    val height: Int,
    val width: Int
)

data class Icons(
    val leading_icon: Any,
    val trailing_icon: Any
)

data class BackgroundImageX(
    val ACL: List<Any>,
    val _version: Int,
    val content_type: String,
    val created_at: String,
    val created_by: String,
    val dimension: Dimension,
    val file_size: String,
    val filename: String,
    val is_dir: Boolean,
    val parent_uid: String,
    val publish_details: List<PublishDetail>,
    val tags: List<Any>,
    val title: String,
    val uid: String,
    val updated_at: String,
    val updated_by: String,
    val url: String
)

data class ButtonX(
    val cta_link: String,
    val cta_text: String,
    val icons: Icons
)

data class Metadata(
    val uid: String
)

data class Card(
    val ACL: ACL,
    val _content_type_uid: String,
    val _in_progress: Boolean,
    val _version: Int,
    val background_image: BackgroundImageXX,
    val button: ButtonXX,
    val created_at: String,
    val created_by: String,
    val cta_link: String,
    val description: String,
    val locale: String,
    val publish_details: PublishDetails,
    val sponsor: Sponsor,
    val tags: List<Any>,
    val title: String,
    val uid: String,
    val updated_at: String,
    val updated_by: String
)

data class BackgroundImageXX(
    val ACL: ACL,
    val _version: Int,
    val content_type: String,
    val created_at: String,
    val created_by: String,
    val file_size: String,
    val filename: String,
    val is_dir: Boolean,
    val parent_uid: String,
    val publish_details: PublishDetails,
    val tags: List<Any>,
    val title: String,
    val uid: String,
    val updated_at: String,
    val updated_by: String,
    val url: String
)

data class ButtonXX(
    val cta_link: String,
    val cta_text: String,
    val icons: IconsXX
)

data class PublishDetails(
    val environment: String,
    val locale: String,
    val time: String,
    val user: String
)

data class Sponsor(
    val cta_link: String,
    val sponsor_icon: Any,
    val sponsor_text: String,
    val sponsor_theme_icons: List<Any>
)

data class IconsXX(
    val leading_icon: LeadingIcon,
    val leading_theme_icons: List<Any>,
    val trailing_icon: TrailingIcon,
    val trailing_theme_icons: List<Any>
)

data class LeadingIcon(
    val ACL: ACL,
    val _version: Int,
    val content_type: String,
    val created_at: String,
    val created_by: String,
    val file_size: String,
    val filename: String,
    val is_dir: Boolean,
    val parent_uid: String,
    val publish_details: PublishDetails,
    val tags: List<Any>,
    val title: String,
    val uid: String,
    val updated_at: String,
    val updated_by: String,
    val url: String
)

data class TrailingIcon(
    val ACL: ACL,
    val _version: Int,
    val content_type: String,
    val created_at: String,
    val created_by: String,
    val file_size: String,
    val filename: String,
    val is_dir: Boolean,
    val parent_uid: String,
    val publish_details: PublishDetails,
    val tags: List<Any>,
    val title: String,
    val uid: String,
    val updated_at: String,
    val updated_by: String,
    val url: String
)

data class BackgroundImageXXX(
    val ACL: List<Any>,
    val _version: Int,
    val content_type: String,
    val created_at: String,
    val created_by: String,
    val dimension: Dimension,
    val file_size: String,
    val filename: String,
    val is_dir: Boolean,
    val parent_uid: String,
    val publish_details: List<PublishDetail>,
    val tags: List<Any>,
    val title: String,
    val uid: String,
    val updated_at: String,
    val updated_by: String,
    val url: String
)

data class ButtonXXX(
    val cta_link: String,
    val cta_text: String,
    val icons: IconsXXX
)

data class IconsXXX(
    val leading_icon: Any,
    val trailing_icon: TrailingIconX
)

data class TrailingIconX(
    val ACL: List<Any>,
    val _version: Int,
    val content_type: String,
    val created_at: String,
    val created_by: String,
    val dimension: Dimension,
    val file_size: String,
    val filename: String,
    val is_dir: Boolean,
    val parent_uid: String,
    val publish_details: List<PublishDetail>,
    val tags: List<Any>,
    val title: String,
    val uid: String,
    val updated_at: String,
    val updated_by: String,
    val url: String
)