package com.upco.playground.mortycomposekmm.android.ui.locations

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.upco.playground.mortycomposekmm.shared.fragment.LocationDetail
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationListView(
    bottomBar: @Composable () -> Unit,
    locationSelected: (location: LocationDetail) -> Unit
) {
    val locationListViewModel = getViewModel<LocationListViewModel>()
    val lazyLocationList = locationListViewModel.locations.collectAsLazyPagingItems()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Locations") }) },
        bottomBar = bottomBar
    ) {
        LazyColumn(contentPadding = it) {
            items(lazyLocationList) { location ->
                location?.let {
                    LocationListRowView(location, locationSelected)
                }
            }
        }
    }
}