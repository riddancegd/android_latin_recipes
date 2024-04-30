
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.yaperecipes.data.model.Recipe
import com.example.yaperecipes.data.repository.RecipeRepository
import com.example.yaperecipes.models.fakeRecipe
import com.example.yaperecipes.ui.home.HomeViewModel
import com.example.yaperecipes.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var homeViewModel: HomeViewModel
    private val repository = mockk<RecipeRepository>()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchRecipes returns a list of recipes successfully`() = runTest {
        //Given
        val recipes = listOf(fakeRecipe, fakeRecipe)
        val recipeFlow = flowOf(Resource.Success(recipes))

        //When
        coEvery { repository.getRecipes() } returns recipeFlow
        homeViewModel = HomeViewModel(repository)

        //Then
        assert(homeViewModel.recipes.value is Resource.Success<List<Recipe>>)
    }

    @Test
    fun `fetchRecipes returns an error state`() = runTest {
        //Given
        val message = "Error fetching recipes"
        val recipeFlow = flowOf<Resource<List<Recipe>>>(Resource.Error(message))

        //When
        coEvery { repository.getRecipes() } returns recipeFlow
        homeViewModel = HomeViewModel(repository)

        //Then
        assert(homeViewModel.recipes.value is Resource.Error)
    }


}