package menu.controller

import menu.model.Coach
import menu.model.Team
import menu.view.InputView
import menu.view.OutputView

class MenuController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {
    private inline fun <T> inputUntilValid(onInvalid: () -> Unit, block: InputView.() -> T): T {
        while (true) {
            try {
                return inputView.block()
            } catch (e: IllegalArgumentException) {
                onInvalid()
            }
        }
    }

    private fun inputTeam(): Team = inputUntilValid(
        onInvalid = outputView::printIllegalCoachNameError
    ) {
        val names = readCoachNames()
        val coaches = names.map { Coach(name = it) }
        return Team(coaches = coaches)
    }

    fun run() {
        val team = inputTeam()

    }

    }
}