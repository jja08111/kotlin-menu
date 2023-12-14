package menu.model

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