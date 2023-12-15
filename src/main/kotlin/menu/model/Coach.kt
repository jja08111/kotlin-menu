package menu.model

data class Coach(
    val name: String,
) {
    private val uneatableMenuNames: MutableList<String> = mutableListOf()

    init {
        require(name.length >= NAME_LENGTH_RANGE.first) {
            "이름은 최소 ${NAME_LENGTH_RANGE.first}자 입니다."
        }
        require(name.length <= NAME_LENGTH_RANGE.last) {
            "이름은 최대 ${NAME_LENGTH_RANGE.last}자 입니다."
        }
    }

    fun addUneatableMenuNames(menus: List<String>) {
        require(menus.size >= UNEATABLE_MENU_LENGTH_RANGE.first) {
            "먹지 못하는 음식은 최소 ${UNEATABLE_MENU_LENGTH_RANGE.first}개 이하를 입력하세요."
        }
        require(menus.size <= UNEATABLE_MENU_LENGTH_RANGE.last) {
            "먹지 못하는 음식은 최대 ${UNEATABLE_MENU_LENGTH_RANGE.last}개 이하를 입력하세요."
        }
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