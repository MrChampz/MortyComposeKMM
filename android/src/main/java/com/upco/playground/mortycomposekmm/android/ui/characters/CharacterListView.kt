package com.upco.playground.mortycomposekmm.android.ui.characters

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.upco.playground.mortycomposekmm.shared.fragment.CharacterDetail
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterListView(
    bottomBar: @Composable () -> Unit,
    characterSelected: (character: CharacterDetail) -> Unit
) {
    val characterListViewModel = getViewModel<CharacterListViewModel>()
    val lazyCharacterList = characterListViewModel.characters.collectAsLazyPagingItems()
    
    Scaffold(
        topBar = { TopAppBar(title = { Text("Characters") }) },
        bottomBar = bottomBar
    ) {
        LazyColumn(contentPadding = it) {
            items(lazyCharacterList) { character ->
                character?.let {
                    CharacterListRowView(character, characterSelected)
                }
            }
        }
    }
}