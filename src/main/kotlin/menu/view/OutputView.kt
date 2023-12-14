package menu.view

class OutputView {

    fun printWelcome() {
        println("점심 메뉴 추천을 시작합니다.\n")
    }

    fun printIllegalCoachNameError() {
        printError("코치 이름이 잘못되었습니다.")
    }

    fun printIllegalUneatableMenuError() {
        printError("먹지 못하는 메뉴를 잘못 입력했습니다.")
    }

    private fun printError(message: String) {
        println("[ERROR] $message")
    }
}