package menu.view

class MenuView(
    private val inputView: MenuInputView = MenuInputView,
    private val outputView: MenuOutputView = MenuOutputView,
    private val viewModel: MenuViewModel = MenuViewModel()
) {
    init {
        viewModel.uiState.observe(::updateUi)
        outputView.printHeader()
        inputUserNames()
    }

    private fun inputUserNames() {
        outputView.printInputUsers()
        val names = inputView.inputCoachNames()
        viewModel.handleUserNames(names)
    }

    private fun inputCannotEatFoods(stage: CannotEatInputStage) {
        outputView.printInputCannotEatMenus(stage.currentCoachName)
        val menus = inputView.inputCannotEatMenus()
        viewModel.handleCannotEatMenus(menus = menus, stage = stage)
    }

    private fun printResult(stage: ResultStage) {
        outputView.printResult(
            categories = stage.categories,
            coaches = stage.coaches,
            suggestions = stage.suggestions
        )
    }

    private fun updateUi(uiState: MenuUiState) {
        if (uiState.errorMessage != null) {
            outputView.printError(uiState.errorMessage)
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