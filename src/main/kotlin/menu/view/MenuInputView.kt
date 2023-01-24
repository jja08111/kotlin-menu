package menu.view

import camp.nextstep.edu.missionutils.Console

object MenuInputView {

    fun inputCoachNames() : List<String> {
        val input = Console.readLine()
        return input.split(",")
    }

    fun inputCannotEatMenus() : List<String> {
        val input = Console.readLine()
        if (input.isBlank()) {
            return emptyList()
        }
        return input.split(",")
    }
}