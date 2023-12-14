package menu.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CoachTest {
    @Test
    fun `코치 이름이 2자보다 짧은 경우 예외를 던짐`() {
        val name = "a"
        assertThrows<IllegalArgumentException> {
            Coach(name = name)
        }
    }

    @Test
    fun `코치 이름이 5자보다 긴 경우 예외를 던짐`() {
        val name = "123456"
        assertThrows<IllegalArgumentException> {
            Coach(name = name)
        }
    }

    @Test
    fun `코치 이름 길이이 올바른 경우 정상적으로 생성됨`() {
        val name = "1234"
        assertDoesNotThrow {
            Coach(name = name)
        }
    }

    @Test
    fun `못먹는 음식을 2개를 초과한 경우 예외를 던짐`() {
        val menus = listOf("규동", "우동", "미소시루")
        val coach = Coach(name = "name")
        assertThrows<IllegalArgumentException> {
            coach.addUneatableMenuNames(menus)
        }
    }

    @Test
    fun `먹을 수 없는 음식으로 canEat 함수를 호출하면 false를 반환`() {
        val menus = listOf("규동", "우동")
        val coach = Coach("name")
        coach.addUneatableMenuNames(menus)

        val canEat = coach.canEat("우동")

        assert(!canEat)
    }

    @Test
    fun `먹을 수 있는 음식으로 canEat 함수를 호출하면 true를 반환`() {
        val menus = listOf("규동", "우동")
        val coach = Coach("name")
        coach.addUneatableMenuNames(menus)

        val canEat = coach.canEat("라면")

        assert(canEat)
    }
}