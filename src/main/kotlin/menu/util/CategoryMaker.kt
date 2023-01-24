package menu.util

import camp.nextstep.edu.missionutils.Randoms
import menu.model.Category

private val categories = Category.values()

fun pickRandomCategory(): Category {
    return  categories[Randoms.pickNumberInRange(1, categories.size) - 1]
}