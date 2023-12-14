package menu.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class TeamTest {
    @Test
    fun `코치가 2명 미만인 경우 예외를 던짐`() {
        val coaches = listOf(Coach("123"))
        assertThrows<IllegalArgumentException> {
            Team(coaches)
        }
    }

    @Test
    fun `코치가 5명 초과인 경우 예외를 던짐`() {
        val coaches = mutableListOf<Coach>()
        repeat(6) {
            coaches.add(Coach("name"))
        }
        assertThrows<IllegalArgumentException> {
            Team(coaches)
        }
    }

    @Test
    fun `코치가 2~5명 사이인 경우 정상적으로 생성됨`() {
        val coaches = mutableListOf<Coach>()
        repeat(5) {
            coaches.add(Coach("name"))
        }
        assertDoesNotThrow {
            Team(coaches)
        }
    }
}