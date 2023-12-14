package menu.model

data class Team(
    private val coaches: List<Coach>
) {
    init {
        require(coaches.size in COACH_SIZE_RANGE)
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