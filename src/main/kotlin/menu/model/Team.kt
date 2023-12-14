package menu.model

class Team(
    private val coaches: List<Coach>
) {
    init {
        require(coaches.size in COACH_SIZE_RANGE)
    }

    companion object {
        private val COACH_SIZE_RANGE = 2..5
    }
}