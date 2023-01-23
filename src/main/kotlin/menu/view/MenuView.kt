package menu.view

import camp.nextstep.edu.missionutils.Console

class MenuView(
    private val output: MenuOutput = MenuOutput,
    private val viewModel: MenuViewModel = MenuViewModel()
) {
    init {
        viewModel.uiState.observe(::updateUi)
        output.printHeader()
        inputUserNames()
    }

    private fun inputUserNames() {
        output.printInputUsers()
        val input = Console.readLine()
        viewModel.handleUserNames(input)
    }

    private fun inputCannotEatFoods(stage: CannotEatInputStage) {
        output.printInputCannotEatMenus(stage.currentCoachName)
        val input = Console.readLine().trim()
        viewModel.handleCannotEatMenus(input = input, stage = stage)
    }

    private fun printResult(stage: ResultStage) {
        output.printResult(
            categories = stage.categories,
            coaches = stage.coaches,
            suggestions = stage.suggestions
        )
    }

    private fun updateUi(uiState: MenuUiState) {
        if (uiState.errorMessage != null) {
            output.printError(uiState.errorMessage)
            viewModel.errorMessageShown()
            return
        }

        when (uiState.stage) {
            CoachNameInputStage -> inputUserNames()
            is CannotEatInputStage -> inputCannotEatFoods(uiState.stage)
            is ResultStage -> printResult(uiState.stage)
        }
    }
}