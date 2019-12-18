package com.tahir.omiseassignment.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tahir.omiseassignment.Components.App
import com.tahir.omiseassignment.Interfaces.EndpointsInterface
import com.tahir.omiseassignment.Models.BaseClass
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

    init {
        App.app.appLevelComponent.inject(this)

    }
    fun getallArticles(): LiveData<BaseClass> {
        getData()
        return charityData

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

                    /* dataLoading.value = false

                     if (response.isSuccessful) {
                         //purging items
                         deleteAllitems()
                         // storing the date
                         Sp_Get_Store_Data.storeStringData(now.toString(), "data-time", c)



                         insertItems(response.body())*/
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

}