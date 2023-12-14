package menu.model

class MenuRecommender(
    private val team: Team,
    private val categoryGenerator: CategoryGenerator = RandomCategoryGenerator(),
    private val menuGenerator: MenuGenerator = RandomMenuGenerator()
) {
    private val coachNames: List<String>
        get() = team.coachNames

    private fun WeekdayRecommend.selectCategory(): Category {
        while (true) {
            val category = categoryGenerator.generate()
            if (!canAdd(category)) {
                continue
            }
            return category
        }
    }

    private fun WeekdayRecommend.recommendMenuToCoach(
        category: Category,
        coachName: String
    ): MenuRecommend {
        while (true) {
            val menuName = menuGenerator.generate(category)
            val canEat = team.canEatMenu(coachName = coachName, menuName = menuName)
            if (!canEat) {
                continue
            }
            val alreadyRecommended = isRecommended(coachName = coachName, menuName = menuName)
            if (alreadyRecommended) {
                continue
            }
            return MenuRecommend(coachName = coachName, menuName = menuName)
        }
    }

    fun recommend(): WeekdayRecommend {
        val weekdayRecommend = WeekdayRecommend()
        RECOMMENDED_WEEKDAYS.forEach { weekday ->
            val category: Category = weekdayRecommend.selectCategory()
            val menuRecommends: List<MenuRecommend> = coachNames.map coachMap@{ coachName ->
                weekdayRecommend.recommendMenuToCoach(category = category, coachName = coachName)
            }
            val dayRecommend = DayRecommend(
                category = category,
                menuRecommends = menuRecommends
            )
            weekdayRecommend[weekday] = dayRecommend
        }
        return weekdayRecommend
    }

    companion object {
        private val RECOMMENDED_WEEKDAYS = listOf(
            Weekday.Monday,
            Weekday.Tuesday,
            Weekday.Wednesday,
            Weekday.Thursday,
            Weekday.Friday
        )
    }
}