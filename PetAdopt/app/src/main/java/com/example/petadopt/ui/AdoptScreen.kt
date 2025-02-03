import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.petadopt.ui.PetAppBar
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petadopt.data.Pet
import com.example.petadopt.ui.viewmodel.AdoptViewModel

@Composable
fun AdoptScreen(
    petID: Int,
    modifier: Modifier = Modifier,
    viewModel: AdoptViewModel = viewModel(),
    onUpClick: () -> Unit = { }
) {
    val pet = viewModel.getPet(petID)
    val context = LocalContext.current

    Scaffold(
        topBar = {
            PetAppBar(
                title = "Thank You!",
                onUpClick = onUpClick,
                canNavigateBack = true
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(pet.imageId),
                    contentDescription = pet.name,
                    modifier = modifier.size(150.dp)
                )
                Text(
                    text = "Thank you for adopting ${pet.name}!",
                    modifier = modifier.padding(horizontal = 28.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
            Text(
                text = "Please pick up your new family member during business hours.",
                modifier = modifier.padding(6.dp),
            )
            Button(
                onClick = { sharePet(context, pet) },
                modifier = modifier.padding(6.dp)
            ) {
                Icon(Icons.Default.Share, null)
                Text("Share", modifier = modifier.padding(start = 8.dp))
            }
        }
    }
}

fun sharePet(context: Context, pet: Pet) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Meet ${pet.name}!")
        putExtra(Intent.EXTRA_TEXT, "I've adopted ${pet.name}!")
    }

    context.startActivity(Intent.createChooser(intent, "Pet Adoption"))
}
