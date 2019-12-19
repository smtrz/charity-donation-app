package com.tahir.omiseassignment.Components


import com.tahir.omiseassignment.Adapters.CharityAdapter
import com.tahir.omiseassignment.DonationActivity
/*
import com.tahir.omiseassignment.GroupMemeberActivity
*/
import com.tahir.omiseassignment.MainActivity
import com.tahir.omiseassignment.Modules.ContextModule
import com.tahir.omiseassignment.Modules.NetModule
import com.tahir.omiseassignment.Modules.RepositoryModule
import com.tahir.omiseassignment.Repository.AppRepository
import com.tahir.omiseassignment.ViewModels.DonationActivityViewModel
import com.tahir.omiseassignment.ViewModels.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, NetModule::class, RepositoryModule::class])

@Singleton
interface AppLevelComponent {
    fun inject(ma: MainActivity)
    fun inject(ma: CharityAdapter)
    fun inject(mv: MainActivityViewModel)
    fun inject(ar: AppRepository)
    fun inject(ar: DonationActivity)
    fun inject(ar: DonationActivityViewModel)


}

