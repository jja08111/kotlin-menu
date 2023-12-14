package menu.model

data class Coach(
    val name: String,
) {
    private val uneatableMenuNames: MutableList<String> = mutableListOf()

    init {
        require(name.length in NAME_LENGTH_RANGE)
    }

    fun addUneatableMenuNames(menus: List<String>) {
        require(menus.size in UNEATABLE_MENU_LENGTH_RANGE)
        uneatableMenuNames.addAll(menus)
    }

    fun canEat(menuName: String): Boolean {
        return !uneatableMenuNames.contains(menuName)
    }

    companion object {
        private val NAME_LENGTH_RANGE = 2..4
        private val UNEATABLE_MENU_LENGTH_RANGE = 0..2
    }
}