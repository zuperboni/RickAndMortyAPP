package com.rickandmortyapp.br.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.rickandmortyapp.br.common.StateScreen
import com.rickandmortyapp.br.detail.presentation.DetailContract
import com.rickandmortyapp.br.detail.presentation.DetailPresenter
import com.rickandmortyapp.br.list.ID
import com.rickandmortyapp.br.model.Character
import com.rickandmortyapp.br.theme.RickAndMortyAPPTheme

class DetailActivity : ComponentActivity(), DetailContract.View {
    override lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra(ID, -1)
        presenter = DetailPresenter(this, this)
        presenter.getDetails(id)

        setContent {
            RickAndMortyAPPTheme {
                StateScreen(isLoading = presenter.isLoading) {
                    presenter.character?.let { DetailCard(character = it) }
                }
            }
        }
    }

    override fun showError(error: Throwable) {
        Log.i("Erro!", error.localizedMessage)
    }
}

@Composable
fun DetailCard(character: Character) {
    Column {
        Image(
            painter = rememberImagePainter(character.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )

        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.subtitle1,
                fontSize = 24.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Canvas(modifier = Modifier
                    .size(13.dp)
                    .padding(end = 4.dp), onDraw = {
                    drawCircle(color = character.statusIndicator)
                })
                Text(
                    text = character.status.uppercase(),
                    style = MaterialTheme.typography.body2,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Specie:",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = character.species,
                style = MaterialTheme.typography.caption
            )
            Text(
                text = "Last known location:",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = character.location.name,
                style = MaterialTheme.typography.caption
            )
            Text(
                text = "First seen in:",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = character.origin.name,
                style = MaterialTheme.typography.caption
            )
        }
    }
}