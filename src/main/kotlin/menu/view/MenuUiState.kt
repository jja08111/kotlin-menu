package menu.view

import menu.model.Category

data class MenuUiState(
    val stage: MenuStage = CoachNameInputStage,
    val errorMessage: String? = null
)

sealed class MenuStage

object CoachNameInputStage : MenuStage()

data class CannotEatInputStage(
) : MenuStage() {
}

class ResultStage(
) : MenuStage()
data class Coach(
) {
}