package com.upco.playground.mortycomposekmm.android.ui.characters

import androidx.paging.PagingSource
import com.upco.playground.mortycomposekmm.shared.MortyRepository
import com.upco.playground.mortycomposekmm.shared.fragment.CharacterDetail

class CharactersDataSource(
    private val repository: MortyRepository
): PagingSource<Int, CharacterDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDetail> {
        val pageNumber = params.key ?: 0

        val charactersResponse = repository.getCharacters(pageNumber)
        val characters = charactersResponse?.resultsFilterNotNull()?.map {
            it.fragments.characterDetail
        }

        val prevKey = if (pageNumber > 0) pageNumber - 1 else null
        val nextKey = charactersResponse?.info?.next
        return LoadResult.Page(data = characters ?: emptyList(), prevKey, nextKey)
    }
}