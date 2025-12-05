package com.example.pokekotlinclass.commons.di

import androidx.lifecycle.ViewModel
import com.example.pokekotlinclass.commons.network.qualifiers.PokeApi
import com.example.pokekotlinclass.data.repository.PokeRepository
import com.example.pokekotlinclass.data.repository.PokeRepositoryImpl
import com.example.pokekotlinclass.data.repository.PokeService
import com.example.pokekotlinclass.domain.interactors.PokeInteractor
import com.example.pokekotlinclass.domain.interactors.PokeInteractorImpl
import com.example.pokekotlinclass.presentation.viewmodel.PokeViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

//@Module(includes = [PokeViewModelModule::class, PokeContributes::class])
//abstract class PokeModule {
//    @ContributesAndroidInjector
//    abstract fun provideMainActivity(): MainActivity
//}

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class PokeViewModelModule {
//    @Binds
//    @IntoMap
//    @ViewModelKey(PokeViewModel::class)
//    abstract fun bindPokeViewModel(viewModel: PokeViewModel): ViewModel
//}

@Module
@InstallIn(SingletonComponent::class)
abstract class PokeContributes {

    @Binds
    abstract fun bindPokeRepository(repositoryImpl: PokeRepositoryImpl): PokeRepository

    @Binds
    abstract fun bindPokeInteractor(interactorImpl: PokeInteractorImpl): PokeInteractor

    companion object {
        @Provides
        fun provideServiceService(@PokeApi retrofit: Retrofit): PokeService =
            retrofit.create(PokeService::class.java)
    }
}