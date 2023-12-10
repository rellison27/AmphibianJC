package com.example.amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.model.Amphibian
import com.example.amphibians.network.FakeAmphibianDataSource
import com.example.amphibians.ui.screen.AmphibianViewModel
import com.example.amphibians.ui.theme.AmphibiansTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            AmphibiansTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val amphibianViewModel: AmphibianViewModel =
                        viewModel(factory = AmphibianViewModel.Factory)
                    AmphibiansScreen(amphibians = amphibianViewModel.amphibianUiState)
                }
            }
        }
    }
}

@Composable
fun AmphibiansScreen(amphibians: List<Amphibian>, modifier: Modifier = Modifier)
{

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items( items = amphibians, key = {amphibian -> amphibian.id}) {amphibian ->
            AmphibianCard(
                name = amphibian.name,
                type = amphibian.type,
                description = amphibian.description,
                imgSrc = amphibian.imgSrc,
                modifier = modifier
            )
        }
    }

}

@Composable
fun AmphibianCard(
    name: String,
    type: String,
    description: String,
    imgSrc: String,
    modifier: Modifier
){
    val header = "$name ($type)"

    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Text(text = header, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imgSrc)
                    .build(),
                contentDescription = name,
                placeholder = painterResource(R.drawable.loading_img),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = description, modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview()
{
    AmphibiansTheme {
        AmphibiansScreen(amphibians = FakeAmphibianDataSource.mockData)
    }
}