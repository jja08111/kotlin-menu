package menu.model

class MenuRecommender(
    private val team: Team,
    private val categoryGenerator: CategoryGenerator = RandomCategoryGenerator(),
    private val menuGenerator: MenuGenerator = RandomMenuGenerator()
) {
    private val recommendByWeekday = mutableMapOf<Weekday, DayRecommend>()

    private val coachNames: List<String>
        get() = team.coachNames

    private fun selectCategory(): Category {
        while (true) {
            val category = categoryGenerator.generate()
            val count = recommendByWeekday.count { it.value.category == category }
            if (count >= MAX_CATEGORY_COUNT) {
                continue
            }
            return category
        }
    }

    private fun recommendMenuToCoach(category: Category, coachName: String): MenuRecommend {
        while (true) {
            val menuName = menuGenerator.generate(category)
            val canEat = team.canEatMenu(coachName = coachName, menuName = menuName)
            if (!canEat) {
                continue
            }
            val alreadyRecommended = recommendByWeekday.any {
                val weekdayRecommend = it.value
                weekdayRecommend.isRecommended(coachName = coachName, menuName = menuName)
            }
            if (alreadyRecommended) {
                continue
            }
            return MenuRecommend(coachName = coachName, menuName = menuName)
        }
    }

    fun recommend(): RecommendResult {
        recommendByWeekday.clear()
        RECOMMENDED_WEEKDAYS.forEach { weekday ->
            val category: Category = selectCategory()
            val menuRecommends: List<MenuRecommend> = coachNames.map coachMap@{ coachName ->
                recommendMenuToCoach(category = category, coachName = coachName)
            }
            val dayRecommend = DayRecommend(
                category = category,
                menuRecommends = menuRecommends
            )
            recommendByWeekday[weekday] = dayRecommend
        }
        return RecommendResult(recommendByWeekday)
    }

    companion object {
        private const val MAX_CATEGORY_COUNT = 2
        private val RECOMMENDED_WEEKDAYS = listOf(
            Weekday.Monday,
            Weekday.Tuesday,
            Weekday.Wednesday,
            Weekday.Thursday,
            Weekday.Friday
        )
    }
}