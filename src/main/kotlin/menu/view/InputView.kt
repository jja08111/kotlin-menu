package menu.view

import camp.nextstep.edu.missionutils.Console

class InputView {

    fun readCoachNames(): List<String> {
        println("코치의 이름을 입력해 주세요. (, 로 구분)")
        val line = Console.readLine()
        return line.split(",")
    }
}