package com.tahir.omiseassignment.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tahir.omiseassignment.Components.App
import com.tahir.omiseassignment.Models.BaseClass
import com.tahir.omiseassignment.Models.Donation
import com.tahir.omiseassignment.Models.DonationResponse
import com.tahir.omiseassignment.Repository.AppRepository
import okhttp3.ResponseBody
import javax.inject.Inject

class DonationActivityViewModel(application: Application) : AndroidViewModel(application) {

    @Inject

    lateinit var repo: AppRepository


    init {


        App.app.appLevelComponent.inject(this)
    }

    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return repo!!.ifDataIsloading()

    }
    // just refresh the data based on the result.

    fun postDonation(donation: Donation): LiveData<DonationResponse> {
        return repo!!.performDonation(donation)

    }


}
