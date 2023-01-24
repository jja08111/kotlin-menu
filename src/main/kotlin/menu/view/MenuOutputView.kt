package menu.view

import menu.model.Category
import menu.model.Weekday

object MenuOutputView {

    fun printHeader() {
        println("점심 메뉴 추천을 시작합니다.\n")
    }

    fun printInputUsers() {
        println("코치의 이름을 입력해 주세요. (, 로 구분)")
    }

    fun printInputCannotEatMenus(name: String) {
        println("\n$name(이)가 못 먹는 메뉴를 입력해 주세요.")
    }

    fun printResult(
        categories: List<Category>,
        coaches: List<String>,
        suggestions: List<List<String>>
    ) {
        println("\n메뉴 추천 결과입니다.")
        println("[ 구분 | ${Weekday.values().map { it.title }.makeTableRow()} ]")
        println("[ 카테고리 | ${categories.map { it.title }.makeTableRow()} ]")
        coaches.forEachIndexed { index, name ->
            println("[ $name | ${suggestions[index].makeTableRow()} ]")
        }
        println("\n추천을 완료했습니다.")
    }

    fun printError(message: String) {
        println("[ERROR] $message")
    }

    private fun List<String>.makeTableRow(): String {
        return reduce { acc, s -> "$acc | $s" }
    }
}