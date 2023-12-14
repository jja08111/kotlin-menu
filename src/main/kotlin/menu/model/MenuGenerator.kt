package menu.model

interface MenuGenerator {
    fun generate(category: Category): String
}