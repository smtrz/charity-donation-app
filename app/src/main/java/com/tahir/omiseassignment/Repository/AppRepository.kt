package com.tahir.omiseassignment.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tahir.omiseassignment.Components.App
import com.tahir.omiseassignment.Interfaces.EndpointsInterface
import com.tahir.omiseassignment.Models.BaseClass
import com.tahir.omiseassignment.Models.Donation
import com.tahir.omiseassignment.Models.DonationResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class AppRepository {
    @Inject
    lateinit var retrofit: Retrofit
    internal var dataLoading = MutableLiveData<Boolean>()
    internal var charityData = MutableLiveData<BaseClass>()
    internal var donationData = MutableLiveData<DonationResponse>()
   // internal lateinit var donationres :DonationResponse
    init {
        App.app.appLevelComponent.inject(this)

    }

    fun getallCharities(): LiveData<BaseClass> {
        getData()
        return charityData

    }

    fun performDonation(donation: Donation):LiveData<DonationResponse>
    {
        donate(donation)
        return donationData
    }


    fun getData() {


        dataLoading.value = true
        //  pd.show();
        val endpoints = retrofit!!.create(EndpointsInterface::class.java)
        endpoints.getCharities().enqueue(object : Callback<BaseClass> {
            override fun onResponse(
                call: Call<BaseClass>,
                response: Response<BaseClass>
            ) {
                dataLoading.value = false
                if (response.isSuccessful) {

                    charityData.postValue(response.body())


                } else {
                }
            }

            override fun onFailure(call: Call<BaseClass>, t: Throwable) {
                dataLoading.value = false

            }
        })
    }

    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return dataLoading

    }


    fun donate(donation:Donation) {


        dataLoading.value = true
        //  pd.show();
        val endpoints = retrofit!!.create(EndpointsInterface::class.java)
        endpoints.postDonation(donation).enqueue(object : Callback<DonationResponse> {
            override fun onResponse(
                call: Call<DonationResponse>,
                response: Response<DonationResponse>
            ) {
                dataLoading.value = false
                if (response.isSuccessful) {

                  //  donationData.postValue(response.body())


                } else {
                }

              /*  donationres.error_code = response.body()?.error_code
                donationres.statusCode = response.code()
                donationres.statusCode = response.code()*/

                donationData.postValue(response.body())
            }

            override fun onFailure(call: Call<DonationResponse>, t: Throwable) {
                dataLoading.value = false

            }
        })
    }
}