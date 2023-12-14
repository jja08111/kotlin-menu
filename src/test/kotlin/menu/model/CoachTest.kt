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
}