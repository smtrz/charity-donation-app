package com.tahir.omiseassignment.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tahir.omiseassignment.Configurations.App
import com.tahir.omiseassignment.Models.BaseClass
import com.tahir.omiseassignment.Repository.AppRepository
import javax.inject.Inject

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    @Inject

    lateinit var repo: AppRepository


    init {


        App.app.appLevelComponent.inject(this)
    }

    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return repo!!.ifDataIsloading()

    }

    fun callCharityAPI(): LiveData<BaseClass> {
        return repo!!.getallCharities()

    }


}

