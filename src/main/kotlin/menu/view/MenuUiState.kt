package menu.view

import menu.model.Category

data class MenuUiState(
    val stage: MenuStage = CoachNameInputStage,
    val errorMessage: String? = null
)

sealed class MenuStage

object CoachNameInputStage : MenuStage()

data class CannotEatInputStage(
    val coachIndex: Int,
    val coaches: List<Coach>
) : MenuStage() {

    private val currentCoach: Coach
        get() = coaches[coachIndex]

    val currentCoachName: String
        get() = currentCoach.name

    val isLastCoach: Boolean
        get() = coaches.size - 1 == coachIndex

    fun copyForNextCoach(cannotEatMenus: List<String>): CannotEatInputStage {
        check(!isLastCoach)
        val newCoaches = coaches.toMutableList()
        newCoaches[coachIndex] = currentCoach.copy(cannotEatMenus = cannotEatMenus)

        return CannotEatInputStage(
            coachIndex = coachIndex + 1,
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