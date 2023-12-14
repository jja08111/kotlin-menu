package menu.model

import camp.nextstep.edu.missionutils.Randoms

class RandomCategoryGenerator : CategoryGenerator {
    override fun generate(): Category {
        val randomIndex = Randoms.pickNumberInRange(1, 5) - 1
        return categories[randomIndex]
    }
}