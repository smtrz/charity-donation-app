package com.tahir.omiseassignment.Interfaces


import androidx.lifecycle.LiveData
import com.tahir.omiseassignment.Models.BaseClass
import retrofit2.Call
import retrofit2.http.GET


interface EndpointsInterface {
    @GET("charities")
    fun getCharities(

    ): Call<BaseClass>

}



