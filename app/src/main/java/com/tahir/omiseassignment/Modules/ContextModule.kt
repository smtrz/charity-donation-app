package com.tahir.omiseassignment.Modules

import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val c: Context) {


    @Provides
    @Singleton
    fun provideContext(): Context {

        return c
    }
}
