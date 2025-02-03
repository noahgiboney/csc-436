import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.petadopt.data.Pet
import com.example.petadopt.ui.viewmodel.ListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petadopt.ui.PetAppBar

@Composable
fun ListScreen(
    onImageClick: (Pet) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            PetAppBar(
                title = "Find a Friend",
                onUpClick = { }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(0.dp),
            modifier = modifier.padding(innerPadding)
        ) {

            items(viewModel.petList) { pet ->
                Image(
                    painter = painterResource(id = pet.imageId),
                    contentDescription = "${pet.type} ${pet.gender}",
                    modifier = Modifier.clickable(
                        onClick = { onImageClick(pet) },
                        onClickLabel = "Select the pet"
                    )
                )
            }
        }
    }
}