package com.tahir.omiseassignment.Interfaces


import com.tahir.omiseassignment.Models.BaseClass
import com.tahir.omiseassignment.Models.Donation
import com.tahir.omiseassignment.Models.DonationResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface EndpointsInterface {
    @GET("charities")
    fun getCharities(

    ): Call<BaseClass>



    @POST("donations")
    fun postDonation(@Body donation: Donation

    ): Call<DonationResponse>

}



