import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.petadopt.ui.PetAppBar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petadopt.data.PetGender
import com.example.petadopt.ui.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    petID: Int,
    onAdoptClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(),
    onUpClick: () -> Unit = { }
) {
    val pet = viewModel.getPet(petID)
    val gender = if (pet.gender == PetGender.MALE) "Male" else "Female"

    Scaffold(
        topBar = {
            PetAppBar(
                title = "Details",
                canNavigateBack = true,
                onUpClick = onUpClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            Image(
                painter = painterResource(pet.imageId),
                contentDescription = pet.name,
                contentScale = ContentScale.FillWidth
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = modifier.padding(6.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = pet.name,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Button(onClick = onAdoptClick) {
                        Text("Adopt Me!")
                    }
                }
                Text(
                    text = "Gender: $gender",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Age: ${pet.age}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = pet.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}