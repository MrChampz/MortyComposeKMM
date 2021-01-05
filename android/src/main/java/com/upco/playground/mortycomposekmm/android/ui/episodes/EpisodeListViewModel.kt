package com.upco.playground.mortycomposekmm.android.ui.episodes

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.upco.playground.mortycomposekmm.shared.MortyRepository
import com.upco.playground.mortycomposekmm.shared.fragment.EpisodeDetail
import kotlinx.coroutines.flow.Flow

class EpisodeListViewModel(
    private val repository: MortyRepository
): ViewModel() {

    val episodes: Flow<PagingData<EpisodeDetail>>
        = Pager(PagingConfig(pageSize = 20)) {
            EpisodesDataSource(repository)
          }.flow

    suspend fun getEpisode(episodeId: String): EpisodeDetail? {
        return repository.getEpisode(episodeId)
    }
}