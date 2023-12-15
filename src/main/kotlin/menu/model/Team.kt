package menu.model

data class Team(
    private val coaches: List<Coach>
) {
    init {
        require(coaches.size >= COACH_SIZE_RANGE.first) {
            "코치는 최소 ${COACH_SIZE_RANGE.first}명 이상 입력해야 합니다."
        }
        require(coaches.size <= COACH_SIZE_RANGE.last) {
            "코치는 최대 ${COACH_SIZE_RANGE.last}명 이하 입력해야 합니다."
        }
    }

    val coachNames: List<String>
        get() = coaches.map { it.name }

    fun addUneatableMenuNamesBy(coachName: String, menus: List<String>) {
        val coach = coaches.find { it.name == coachName } ?: return
        coach.addUneatableMenuNames(menus)
    }

    fun canEatMenu(coachName: String, menuName: String): Boolean {
        val coach = coaches.find { it.name == coachName }
        checkNotNull(coach)
        return coach.canEat(menuName)
    }

    companion object {
        private val COACH_SIZE_RANGE = 2..5
    }
}