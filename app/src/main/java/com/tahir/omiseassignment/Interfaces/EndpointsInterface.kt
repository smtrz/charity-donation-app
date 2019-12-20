package com.tahir.omiseassignment.Interfaces


import com.tahir.omiseassignment.Models.BaseClass
import com.tahir.omiseassignment.Models.DonationResponse
import com.tahir.omiseassignment.Models.TokenResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface EndpointsInterface {
    @GET("charities")
    fun getCharities(

    ): Call<BaseClass>


    @FormUrlEncoded
    @POST("charges")
    fun createCharge(

        @Header("Authorization") header: String, @Field("amount") amount: Int, @Field("currency") currency: String, @Field(
            "card"
        ) card: String

    ): Call<DonationResponse>


    @FormUrlEncoded
    @POST("tokens")
    fun createToken(

        @Header("Authorization") header: String, @Field("card[name]") cardName: String,
        @Field("card[number]") cardNumber: String,
        @Field("card[expiration_month]") cardExpMonth: String,
        @Field("card[expiration_year]") cardExpyear: String

    ): Call<TokenResponse>

}



