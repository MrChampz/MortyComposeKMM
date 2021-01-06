package com.upco.playground.mortycomposekmm.android.ui.locations

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.upco.playground.mortycomposekmm.shared.MortyRepository
import com.upco.playground.mortycomposekmm.shared.fragment.LocationDetail
import kotlinx.coroutines.flow.Flow

class LocationListViewModel(
    private val repository: MortyRepository
): ViewModel() {
    val locations: Flow<PagingData<LocationDetail>> =
        Pager(PagingConfig(pageSize = 20)) {
            LocationsDataSource(repository)
        }.flow

    suspend fun getLocation(locationId: String): LocationDetail? {
        return repository.getLocation(locationId)
    }
}