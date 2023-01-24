package menu.view

import camp.nextstep.edu.missionutils.Randoms
import menu.core.MutableObservable
import menu.core.Observable
import menu.model.Category
import menu.model.Weekday
import menu.model.allMenus
import menu.util.pickRandomCategory

class MenuViewModel {

    private val _uiState = MutableObservable(MenuUiState())
    val uiState: Observable<MenuUiState> = _uiState

    fun handleUserNames(names: List<String>) {
        if (!checkNameInput(names)) {
            return
        }
        reduce {
            val coaches = names.map { name -> Coach(name = name) }
            it.copy(stage = CannotEatInputStage(currentCoachIndex = 0, coaches = coaches))
        }
    }

    fun handleCannotEatMenus(menus: List<String>, stage: CannotEatInputStage) {
        if (menus.isNotEmpty() && !checkMenuInput(menus)) {
            return
        }
        reduce {
            if (stage.isLastCoach) {
                it.copy(stage = generateResult(stage))
            } else {
                it.copy(stage = stage.copyForNextCoach(menus))
            }
        }
    }

    private fun generateResult(stage: CannotEatInputStage): ResultStage {
        val categories = generateCategories()
        val suggestions = generateSuggestions(stage, categories)

        return ResultStage(
            categories = categories,
            coaches = stage.coaches.map { it.name },
            suggestions = suggestions
        )
    }

    private fun generateCategories(): List<Category> {
        val targetSize = Weekday.values().size
        val categories = mutableListOf<Category>()

        while (categories.size < targetSize) {
            val category = pickRandomCategory()
            if (categories.count { it == category } == 2) {
                continue
            }
            categories += category
        }
        return categories
    }

    private fun generateSuggestions(
        stage: CannotEatInputStage,
        categories: List<Category>
    ): List<List<String>> {
        val suggestions = List(stage.coaches.size) { mutableListOf<String>() }

        categories.forEach { category ->
            stage.coaches.forEachIndexed { index, coach ->
                val eatableMenus = category.menus.filter { menu ->
                    coach.canEat(menu) && !suggestions[index].contains(menu)
                }
                val menu = Randoms.shuffle(eatableMenus)[0]
                suggestions[index].add(menu)
            }
        }
        return suggestions
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
            throw IllegalArgumentException("코치의 이름은 최소 2글자, 최대 4글자입니다.")
        }
    }

    private fun checkCoachCount(count: Int) {
        if (count < 2 || count > 5) {
            throw IllegalArgumentException("코치는 최소 2명, 최대 5명까지 식사를 함께 해야합니다.")
        }
    }

    private fun checkMenuInput(menus: List<String>): Boolean {
        return try {
            menus.forEach(::checkMenuName)
            checkCannotEatMenuCount(menus.size)
            true
        } catch (e: IllegalArgumentException) {
            reduce { it.copy(errorMessage = e.message!!) }
            false
        }
    }

    private fun checkMenuName(name: String) {
        if (!allMenus.contains(name)) {
            throw IllegalArgumentException("해당 메뉴는 존재하지 않습니다.")
        }
    }

    private fun checkCannotEatMenuCount(count: Int) {
        if (count < 0 || count > 2) {
            throw IllegalArgumentException("각 코치는 최소 0개, 최대 2개의 못 먹는 메뉴가 있어야 합니다.")
        }
    }

    private fun reduce(reducer: (MenuUiState) -> MenuUiState) {
        _uiState.value = reducer.invoke(uiState.value)
    }

    fun errorMessageShown() {
        reduce { it.copy(errorMessage = null) }
    }
}