package menu.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DayRecommendTest {

    @Test
    fun `getMenuBy 함수는 coach가 존재하지 않으면 예외를 던짐`() {
        val dayRecommend = DayRecommend(
            category = Category.Chinese,
            menuRecommends = listOf(
                MenuRecommend(coachName = "name", menuName = "menuName")
            )
        )

        assertThrows<IllegalStateException> {
            dayRecommend.getMenuBy("wow")
        }
    }

    @Test
    fun `getMenuBy 함수는 해당 coach에게 추천된 메뉴 이름을 반환`() {
        val dayRecommend = DayRecommend(
            category = Category.Chinese,
            menuRecommends = listOf(
                MenuRecommend(coachName = "name", menuName = "menuName")
            )
        )

        val menu = dayRecommend.getMenuBy("name")

        assert(menu == "menuName")
    }

    @Test
    fun `isRecommended 함수는 이미 추천된 메뉴인 경우 true를 반환`() {
        val dayRecommend = DayRecommend(
            category = Category.Chinese,
            menuRecommends = listOf(
                MenuRecommend(coachName = "name", menuName = "menuName")
            )
        )

        val isRecommended = dayRecommend.isRecommended(
            coachName = "name",
            menuName = "menuName"
        )

        assert(isRecommended)
    }

    @Test
    fun `isRecommended 함수는 추천된 메뉴가 아닌 경우 false를 반환`() {
        val dayRecommend = DayRecommend(
            category = Category.Chinese,
            menuRecommends = listOf(
                MenuRecommend(coachName = "name", menuName = "menuName")
            )
        )

        val isRecommended = dayRecommend.isRecommended(
            coachName = "name",
            menuName = "menu"
        )

        assert(!isRecommended)
    }
}