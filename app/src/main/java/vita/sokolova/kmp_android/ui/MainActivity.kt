package vita.sokolova.kmp_android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import vita.sokolova.kmp_android.ui.entities.MovieListScreenState
import vita.sokolova.kmp_android.ui.theme.SearchWithPaginationTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.platform.AndroidPlatform

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchWithPaginationTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val searchQuery = viewModel.searchQuery.collectAsState("")
                    val moviesLazyItems = viewModel.movies.collectAsState(initial = MovieListScreenState.EmptyQuery)
                    MainScreenContent(
                        searchQuery = searchQuery.value,
                        screenState = moviesLazyItems.value,
                        onTextChanged = { viewModel.updateQuery(it) },
                        onClearClick = { viewModel.onClearButtonClick() })
                }
            }
        }
    }
}