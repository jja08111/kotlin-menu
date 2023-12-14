package menu.model

class WeekdayRecommend {
    private val recommendByWeekday = mutableMapOf<Weekday, DayRecommend>()

    val weekdays: List<Weekday>
        get() = recommendByWeekday.keys.toList()

    val dayRecommends: List<DayRecommend>
        get() = recommendByWeekday.values.toList()

    fun canAdd(category: Category): Boolean {
        val count = recommendByWeekday.count { it.value.category == category }
        return count < MAX_CATEGORY_COUNT
    }

    fun isRecommended(coachName: String, menuName: String): Boolean {
        return recommendByWeekday.any {
            val weekdayRecommend = it.value
            weekdayRecommend.isRecommended(coachName = coachName, menuName = menuName)
        }
    }

    fun getMenusBy(coachName: String): List<String> {
        return recommendByWeekday.map {
            val weekdayRecommend = it.value
            weekdayRecommend.getMenuBy(coachName)
        }
    }

    operator fun set(weekday: Weekday, dayRecommend: DayRecommend) {
        recommendByWeekday[weekday] = dayRecommend
    }

    companion object {
        private const val MAX_CATEGORY_COUNT = 2
    }
}