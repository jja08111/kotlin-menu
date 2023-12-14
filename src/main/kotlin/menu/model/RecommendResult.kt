package menu.model

data class RecommendResult(val recommendByWeekday: Map<Weekday, DayRecommend>) {
    fun getMenusBy(coachName: String): List<String> {
        return recommendByWeekday.map {
            val weekdayRecommend = it.value
            weekdayRecommend.getMenuBy(coachName)
        }
    }
}

data class DayRecommend(
    val category: Category,
    val menuRecommends: List<MenuRecommend>
) {
    fun getMenuBy(coachName: String): String {
        val menuRecommend = menuRecommends.firstOrNull { it.coachName == coachName }
            ?: error("코치가 존재하지 않습니다")
        return menuRecommend.menuName
    }

    fun isRecommended(coachName: String, menuName: String): Boolean {
        return menuRecommends.any { it.coachName == coachName && it.menuName == menuName }
    }
}

data class MenuRecommend(
    val coachName: String,
    val menuName: String
)