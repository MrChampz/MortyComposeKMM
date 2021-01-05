package com.upco.playground.mortycomposekmm.android.ui.episodes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.upco.playground.mortycomposekmm.shared.fragment.EpisodeDetail
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodeListView(
    bottomBar: @Composable () -> Unit,
    episodeSelected: (episode: EpisodeDetail) -> Unit
) {
    val episodeListViewModel = getViewModel<EpisodeListViewModel>()
    val lazyEpisodeList = episodeListViewModel.episodes.collectAsLazyPagingItems()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Episodes") }) },
        bottomBar = bottomBar
    ) {
        LazyColumn(contentPadding = it) {
            items(lazyEpisodeList) { episode ->
                episode?.let {
                    EpisodeListRowView(episode, episodeSelected)
                }
            }
        }
    }
}