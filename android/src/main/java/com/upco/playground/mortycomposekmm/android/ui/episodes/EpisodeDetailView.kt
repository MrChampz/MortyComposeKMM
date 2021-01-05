package com.upco.playground.mortycomposekmm.android.ui.episodes

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.upco.playground.mortycomposekmm.shared.fragment.EpisodeDetail
import dev.chrisbanes.accompanist.coil.CoilImage
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodeDetailView(episodeId: String, popBack: () -> Unit) {
    val episodeListViewModel = getViewModel<EpisodeListViewModel>()
    val (episode, setEpisode) = remember { mutableStateOf<EpisodeDetail?>(null) }

    LaunchedEffect(episodeId) {
        setEpisode(episodeListViewModel.getEpisode(episodeId))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(episode?.name ?: "") },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        }
    ) {
        Surface(color = Color.LightGray) {
            ScrollableColumn(modifier = Modifier.padding(top = 16.dp)) {
                episode?.let {

                    Text(
                        "Characters",
                        style = MaterialTheme.typography.h5,
                        color = AmbientContentColor.current,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )

                    Surface(color = Color.White) {
                        EpisodeCharactersList(episode)
                    }
                }
            }
        }
    }
}

@Composable
private fun EpisodeCharactersList(episode: EpisodeDetail) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        episode.characters?.let { characterList ->
            characterList.filterNotNull().forEach { character ->
                Row(modifier = Modifier.padding(vertical = 8.dp)) {

                    Surface(
                        modifier = Modifier.preferredSize(28.dp),
                        shape = CircleShape,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                    ) {
                        character.image?.let {
                            CoilImage(
                                data = it,
                                modifier = Modifier.preferredSize(28.dp),
                                requestBuilder = {
                                    transformations(CircleCropTransformation())
                                }
                            )
                        }
                    }

                    Text(
                        character.name ?: "",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    )
                }
                Divider()
            }
        }
    }
}