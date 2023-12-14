package menu.model

import org.junit.jupiter.api.Test

class WeekdayRecommendTest {
    @Test
    fun `특정 카테고리가 이미 2번 추천된 경우 canAdd는 false를 반환`() {
        val recommender = WeekdayRecommend()
        recommender[Weekday.Monday] = DayRecommend(Category.Chinese, menuRecommends = listOf())
        recommender[Weekday.Tuesday] = DayRecommend(Category.Chinese, menuRecommends = listOf())

        val result = recommender.canAdd(Category.Chinese)

        assert(!result)
    }

    @Test
    fun `특정 카테고리가 2번 이내로 추천된 경우 canAdd는 true를 반환`() {
        val recommender = WeekdayRecommend()
        recommender[Weekday.Monday] = DayRecommend(Category.Chinese, menuRecommends = listOf())
        recommender[Weekday.Tuesday] = DayRecommend(Category.Asian, menuRecommends = listOf())

        val result = recommender.canAdd(Category.Chinese)

        assert(result)
    }

    @Test
    fun `해당 코치에게 이미 추천된 메뉴인 경우 isRecommended는 true를 반환`() {
        val recommender = WeekdayRecommend()
        recommender[Weekday.Monday] = DayRecommend(
            Category.Chinese, menuRecommends = listOf(
                MenuRecommend(coachName = "name", menuName = "menu")
            )
        )

        val result = recommender.isRecommended(coachName = "name", menuName = "menu")

        assert(result)
    }

    @Test
    fun `해당 코치에게 아직 추천되지 않은 메뉴인 경우 isRecommended는 false를 반환`() {
        val recommender = WeekdayRecommend()
        recommender[Weekday.Monday] = DayRecommend(
            Category.Chinese, menuRecommends = listOf(
                MenuRecommend(coachName = "name", menuName = "menu1")
            )
        )
        recommender[Weekday.Tuesday] = DayRecommend(
            Category.Asian, menuRecommends = listOf(
                MenuRecommend(coachName = "name", menuName = "menu2")
            )
        )

        val result = recommender.isRecommended(coachName = "name", menuName = "menu3")

        assert(!result)
    }
}