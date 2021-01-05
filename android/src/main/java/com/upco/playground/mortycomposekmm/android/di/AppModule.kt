package com.upco.playground.mortycomposekmm.android.di

import com.upco.playground.mortycomposekmm.android.ui.characters.CharacterListViewModel
import com.upco.playground.mortycomposekmm.shared.MortyRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mortyAppModule = module {
    viewModel { CharacterListViewModel(get()) }
    //viewModel { EpisodesListViewModel(get()) }
    //viewModel { LocationsListViewModel(get()) }

    single { MortyRepository() }
}

// Gather all app modules
val appModule = listOf(mortyAppModule)