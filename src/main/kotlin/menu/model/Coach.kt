package menu.model

class Coach(
    private val name: String
) {
    init {
        require(name.length in NAME_LENGTH_RANGE)
    }

    companion object {
        private val NAME_LENGTH_RANGE = 2..4
    }
}