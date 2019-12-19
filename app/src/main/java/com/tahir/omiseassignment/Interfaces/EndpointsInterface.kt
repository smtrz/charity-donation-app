package com.tahir.omiseassignment.Interfaces


import com.tahir.omiseassignment.Models.BaseClass
import com.tahir.omiseassignment.Models.Donation
import com.tahir.omiseassignment.Models.DonationResponse
import com.tahir.omiseassignment.Models.data
import retrofit2.Call
import retrofit2.http.*


interface EndpointsInterface {
    @GET("charities")
    fun getCharities(

    ): Call<BaseClass>

/*

    @POST("donations")
    fun postDonation(
        @Body donation: Donation

    ): Call<DonationResponse>
*/

    @FormUrlEncoded
    @POST("charges")
    fun createCharge(

        @Header("Authorization") header: String, @Field("amount") amount: Int, @Field("currency") currency: String, @Field(
            "card"
        ) card: String

    ): Call<DonationResponse>


}



