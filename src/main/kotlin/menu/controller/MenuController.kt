package menu.controller

import menu.model.Coach
import menu.model.MenuRecommender
import menu.model.Team
import menu.view.InputView
import menu.view.OutputView

class MenuController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {
    private inline fun <T> inputUntilValid(
        onInvalid: (message: String?) -> Unit,
        block: InputView.() -> T
    ): T {
        while (true) {
            try {
                return inputView.block()
            } catch (e: IllegalArgumentException) {
                onInvalid(e.message)
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

    private fun inputUneatableMenus(team: Team): Unit = inputUntilValid(
        onInvalid = outputView::printIllegalUneatableMenuError
    ) {
        val coachNames = team.coachNames
        coachNames.forEach { name ->
            val uneatableMenus = readUneatableMenus(coachName = name)
            team.addUneatableMenuNamesBy(coachName = name, menus = uneatableMenus)
        }
        return
    }

    fun run() {
        outputView.printWelcome()

        val team = inputTeam()
        inputUneatableMenus(team)
        val menuRecommender = MenuRecommender(team = team)
        val weekdayRecommend = menuRecommender.recommend()

        outputView.printResult(weekdayRecommend, coachNames = team.coachNames)
        outputView.printComplete()
    }
}