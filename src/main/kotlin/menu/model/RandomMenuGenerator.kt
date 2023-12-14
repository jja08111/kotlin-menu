package menu.model

import camp.nextstep.edu.missionutils.Randoms

class RandomMenuGenerator : MenuGenerator {
    override fun generate(category: Category): String {
        return Randoms.shuffle(category.menus)[0]
    }
}