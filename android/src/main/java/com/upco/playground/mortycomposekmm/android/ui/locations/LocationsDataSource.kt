package com.upco.playground.mortycomposekmm.android.ui.locations

import androidx.paging.PagingSource
import com.upco.playground.mortycomposekmm.shared.MortyRepository
import com.upco.playground.mortycomposekmm.shared.fragment.LocationDetail

class LocationsDataSource(
    private val repository: MortyRepository
): PagingSource<Int, LocationDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationDetail> {
        val pageNumber = params.key ?: 0

        val locationsResponse = repository.getLocations(pageNumber)
        val locations = locationsResponse?.resultsFilterNotNull()?.map {
            it.fragments.locationDetail
        }

        val prevKey = if (pageNumber > 0) pageNumber - 1 else null
        val nextKey = locationsResponse?.info?.next
        return LoadResult.Page(data = locations ?: emptyList(), prevKey, nextKey)
    }
}