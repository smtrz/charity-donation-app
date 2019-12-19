package com.tahir.omiseassignment.ViewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tahir.omiseassignment.Components.App
import com.tahir.omiseassignment.Models.DonationResponse
import com.tahir.omiseassignment.Repository.AppRepository
import javax.inject.Inject

class DonationActivityViewModel(application: Application) : AndroidViewModel(application) {

    @Inject

    lateinit var repo: AppRepository

    @Inject
    lateinit var context: Context


    init {


        App.app.appLevelComponent.inject(this)

    }

    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return repo!!.ifDataIsloading()

    }
    // just refresh the data based on the result.

    fun postDonation(name: String, cardnumber: String, amount: String): LiveData<DonationResponse> {
        return repo!!.performDonation(name, cardnumber, amount)

    }


}

