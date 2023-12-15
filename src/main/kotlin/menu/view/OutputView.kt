package menu.view

import menu.model.Category
import menu.model.Weekday
import menu.model.WeekdayRecommend

class OutputView {

    fun printWelcome() {
        println("점심 메뉴 추천을 시작합니다.\n")
    }

    fun printIllegalCoachNameError(message: String?) {
        printError(message ?: "코치 이름이 잘못되었습니다.")
    }

    fun printIllegalUneatableMenuError(message: String?) {
        printError(message ?: "먹지 못하는 메뉴를 잘못 입력했습니다.")
    }

    private fun Collection<String>.reduceWithDivider(): String {
        return this.reduce { a, b -> "$a | $b" }
    }

    private fun printWeekdayLineOfResult(weekdays: Collection<Weekday>) {
        val weekdayKoreanNames = weekdays.map { it.koreanName }
        val weekdayLine = weekdayKoreanNames.reduceWithDivider()
        println("[ 구분 | $weekdayLine ]")
    }

    private fun printCategoriesOfResult(category: Collection<Category>) {
        val categoryKoreanNames = category.map { it.koreanName }
        val categoryLine = categoryKoreanNames.reduceWithDivider()
        println("[ 카테고리 | $categoryLine ]")
    }

    private fun printRecommendedMenusOfResult(coachName: String, menus: List<String>) {
        val menuLine = menus.reduceWithDivider()
        println("[ $coachName | $menuLine ]")
    }

    fun printResult(weekdayRecommend: WeekdayRecommend, coachNames: List<String>) {
        println("메뉴 추천 결과입니다.")
        val weekdays = weekdayRecommend.weekdays
        val dayRecommends = weekdayRecommend.dayRecommends
        val categories = dayRecommends.map { it.category }
        printWeekdayLineOfResult(weekdays)
        printCategoriesOfResult(categories)
        coachNames.forEach { coachName ->
            val menus = weekdayRecommend.getMenusBy(coachName = coachName)
            printRecommendedMenusOfResult(coachName, menus)
        }
        println()
    }

    fun printComplete() {
        println("추천을 완료했습니다.")
    }

    private val Weekday.koreanName: String
        get() = when (this) {
            Weekday.Monday -> "월요일"
            Weekday.Tuesday -> "화요일"
            Weekday.Wednesday -> "수요일"
            Weekday.Thursday -> "목요일"
            Weekday.Friday -> "금요일"
            Weekday.Saturday -> "토요일"
            Weekday.Sunday -> "일요일"
        }

    private val Category.koreanName: String
        get() = when (this) {
            Category.Japanese -> "일식"
            Category.Korean -> "한식"
            Category.Chinese -> "중식"
            Category.Asian -> "아시안"
            Category.Western -> "양식"
        }

    private fun printError(message: String) {
        println("[ERROR] $message")
    }
}