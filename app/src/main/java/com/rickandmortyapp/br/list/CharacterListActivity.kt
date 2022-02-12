package com.rickandmortyapp.br.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.rickandmortyapp.br.common.StateScreen
import com.rickandmortyapp.br.detail.DetailActivity
import com.rickandmortyapp.br.list.presentation.ListContract
import com.rickandmortyapp.br.list.presentation.ListPresenter
import com.rickandmortyapp.br.model.Character
import com.rickandmortyapp.br.theme.RickAndMortyAPPTheme

class CharacterListActivity : ComponentActivity(), ListContract.View {

    override lateinit var presenter: ListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ListPresenter(this, this)
        presenter.getList()

        setContent {
            RickAndMortyAPPTheme {
                StateScreen(presenter.isLoading) {
                    CharacterList(list = presenter.characters, startDetail = ::startDetailActivity)
                }
            }
        }
    }

    override fun showError(error: Throwable) {
        Log.i("Erro!", error.localizedMessage)
    }

    override fun startDetailActivity(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
            .putExtra(ID, id)
        startActivity(intent)
    }
}

@Composable
fun CharacterItem(character: Character, startDetail: (Int) -> Unit) {
    Row {
        Column(
            modifier = Modifier
                .clickable(onClick = { startDetail(character.id) })
        ) {
            Image(
                painter = rememberImagePainter(character.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp, 200.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterList(list: List<Character>, startDetail: (Int) -> Unit) {
    LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 100.dp)) {
        items(list) { item ->
            CharacterItem(character = item, startDetail = startDetail)
        }
    }
}

const val ID = "id"