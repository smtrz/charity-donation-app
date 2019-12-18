package com.tahir.omiseassignment.Modules

import com.tahir.omiseassignment.Repository.AppRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {


    @Provides
    @Singleton
    fun provideRepository(): AppRepository {

        return AppRepository()
    }
}