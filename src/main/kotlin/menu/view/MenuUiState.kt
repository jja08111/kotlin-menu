package menu.view

import menu.model.Category

data class MenuUiState(
    val stage: MenuStage = CoachNameInputStage,
    val errorMessage: String? = null
)

sealed class MenuStage

object CoachNameInputStage : MenuStage()

data class CannotEatInputStage(
    val currentCoachIndex: Int,
    val coaches: List<Coach>
) : MenuStage() {

    private val currentCoach: Coach
        get() = coaches[currentCoachIndex]

    val currentCoachName: String
        get() = currentCoach.name

    val isLastCoach: Boolean
        get() = coaches.size - 1 == currentCoachIndex

    fun copyForNextCoach(cannotEatMenus: List<String>): CannotEatInputStage {
        check(!isLastCoach)
        val newCoaches = coaches.toMutableList()
        newCoaches[currentCoachIndex] = currentCoach.copy(cannotEatMenus = cannotEatMenus)

        return CannotEatInputStage(
            currentCoachIndex = currentCoachIndex + 1,
            coaches = newCoaches
        )
    }
}

class ResultStage(
    val categories: List<Category>,
    val coaches: List<String>,
    val suggestions: List<List<String>>
) : MenuStage()

data class Coach(
    val name: String,
    val cannotEatMenus: List<String> = emptyList()
) {
    fun canEat(menu: String): Boolean = !cannotEatMenus.contains(menu)
}