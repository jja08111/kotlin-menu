package menu.view

import camp.nextstep.edu.missionutils.Randoms
import menu.core.MutableObservable
import menu.core.Observable
import menu.model.Category
import menu.model.Weekday
import menu.model.allMenus

class MenuViewModel {

    private val _uiState = MutableObservable(MenuUiState())
    val uiState: Observable<MenuUiState> = _uiState

    fun handleUserNames(input: String) {
        val names = input.split(",")
        if (!checkNameInput(names)) {
            return
        }
        reduce {
            val coaches = names.map { name -> Coach(name = name) }
            it.copy(stage = CannotEatInputStage(coachIndex = 0, coaches = coaches))
        }
    }


    private fun checkNameInput(names: List<String>): Boolean {
        return try {
            names.forEach(::checkCoachName)
            checkCoachCount(names.size)
            true
        } catch (e: IllegalArgumentException) {
            reduce { it.copy(errorMessage = e.message!!) }
            false
        }
    }

    private fun checkCoachName(name: String) {
        if (name.length < 2 || name.length > 4) {
            throw IllegalArgumentException("[ERROR] 코치의 이름은 최소 2글자, 최대 4글자입니다.\n")
        }
    }

    private fun checkCoachCount(count: Int) {
        if (count < 2 || count > 5) {
            throw IllegalArgumentException("[ERROR] 코치는 최소 2명, 최대 5명까지 식사를 함께 해야합니다.\n")
        }
    }

    private fun reduce(reducer: (MenuUiState) -> MenuUiState) {
        _uiState.value = reducer.invoke(uiState.value)
    }

    fun errorMessageShown() {
        reduce { it.copy(errorMessage = null) }
    }
}