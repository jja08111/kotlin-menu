package menu

import menu.model.allMenus
import menu.view.CannotEatInputStage
import menu.view.CoachNameInputStage
import menu.view.MenuViewModel
import menu.view.ResultStage
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MenuViewModelTest {

    private lateinit var viewModel: MenuViewModel

    private val uiState get() = viewModel.uiState.value

    @BeforeEach
    fun setUp() {
        viewModel = MenuViewModel()
    }

    @Test
    fun `should uiState is CoachNameInputStage when starting`() {
        val uiState = viewModel.uiState.value
        assert(uiState.stage is CoachNameInputStage)
    }

    @Test
    fun `should move uiState to CannotEatInputStage when names is valid`() {
        viewModel.handleUserNames("토비,만두")
        assert(uiState.stage is CannotEatInputStage)
    }

    @Test
    fun `should have error when name is too long`() {
        viewModel.handleUserNames("긴이름이에요,토비")
        assert(uiState.errorMessage != null)
        assert(uiState.stage is CoachNameInputStage)
    }

    @Test
    fun `should have error when name is too short`() {
        viewModel.handleUserNames("김,토비")
        assert(uiState.errorMessage != null)
        assert(uiState.stage is CoachNameInputStage)
    }

    @Test
    fun `should have error when person count is over five`() {
        viewModel.handleUserNames("스티,토비,만두,어쩌구,저쩌구,스키")
        assert(uiState.errorMessage != null)
        assert(uiState.stage is CoachNameInputStage)
    }

    @Test
    fun `should have error when person count is lower than two`() {
        viewModel.handleUserNames("스티")
        assert(uiState.errorMessage != null)
        assert(uiState.stage is CoachNameInputStage)
    }

}