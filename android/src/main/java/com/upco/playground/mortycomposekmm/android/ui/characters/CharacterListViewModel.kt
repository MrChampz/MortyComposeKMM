package com.upco.playground.mortycomposekmm.android.ui.characters

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.upco.playground.mortycomposekmm.shared.MortyRepository
import com.upco.playground.mortycomposekmm.shared.fragment.CharacterDetail
import kotlinx.coroutines.flow.Flow

class CharacterListViewModel(private val repository: MortyRepository): ViewModel() {

    val characters: Flow<PagingData<CharacterDetail>>
        = Pager(PagingConfig(pageSize = 20)) {
            CharactersDataSource(repository)
          }.flow

    suspend fun getCharacter(characterId: String): CharacterDetail? {
        return repository.getCharacter(characterId)
    }
}