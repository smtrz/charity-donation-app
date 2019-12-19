package com.tahir.omiseassignment.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.omise.android.api.Client
import co.omise.android.api.RequestListener
import co.omise.android.models.CardParam
import co.omise.android.models.Token
import com.tahir.omiseassignment.Components.App
import com.tahir.omiseassignment.Configurations.AppConstant
import com.tahir.omiseassignment.Enums.Codes
import com.tahir.omiseassignment.Helpers.UIHelper
import com.tahir.omiseassignment.Interfaces.EndpointsInterface
import com.tahir.omiseassignment.Models.BaseClass
import com.tahir.omiseassignment.Models.Donation
import com.tahir.omiseassignment.Models.DonationResponse
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class AppRepository {


    @Inject
    @field:Named("charity")
    lateinit var retrofitcharity: Retrofit
    @Inject
    @field:Named("omise")
    lateinit var retrofit_omise: Retrofit
    internal var dataLoading = MutableLiveData<Boolean>()
    internal var charityData = MutableLiveData<BaseClass>()
    internal var donationData = MutableLiveData<DonationResponse>()
    @Inject
    lateinit var context: Context

    init {
        App.app.appLevelComponent.inject(this)

    }

    fun getallCharities(): LiveData<BaseClass> {
        getData()
        return charityData

    }

    fun performDonation(
        name: String,
        cardnumber: String,
        amount: String
    ): LiveData<DonationResponse> {
        doPaymentProcess(name, cardnumber, amount)
        return donationData
    }


    fun getData() {


        dataLoading.value = true
        //  pd.show();
        val endpoints = retrofitcharity!!.create(EndpointsInterface::class.java)
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


    fun donate(donation: Donation) {

        var basic_auth = Credentials.basic(AppConstant.OMISE_SKEY, "")

        dataLoading.value = true
        //  pd.show();
        val endpoints = retrofit_omise!!.create(EndpointsInterface::class.java)
        endpoints.createCharge(basic_auth, donation.amount, "thb", donation.token)
            .enqueue(object : Callback<DonationResponse> {
                override fun onResponse(
                    call: Call<DonationResponse>,
                    response: Response<DonationResponse>
                ) {
                    dataLoading.value = false
                    when (response.code()) {
                        200 ->
                            when (response.body()?.status) {
                                Codes.failed.toString() ->
                                    UIHelper.showShortToastInCenter(
                                        context,
                                        response.body()?.failure_message!!
                                    )
                                // send data to the viewmodel and activity if the payment is successful.

                                Codes.successful.toString() ->
                                    donationData.postValue(response.body())

                            }


                        401
                        ->
                            UIHelper.showShortToastInCenter(context, "Unauthoized")
                        404 ->
                            // Token not found
                            UIHelper.showShortToastInCenter(
                                context,
                                "Token for the transaction doesnot exist."
                            )
                        400 ->
                            // Token not found
                            when (response.body()?.code) {
                                Codes.invalid_charge.toString() ->
                                    UIHelper.showShortToastInCenter(context, "Invalid charge")


                                Codes.used_token.toString() ->
                                    UIHelper.showShortToastInCenter(
                                        context,
                                        "Token is already used"
                                    )
                            }


                    }


                }

                override fun onFailure(call: Call<DonationResponse>, t: Throwable) {
                    dataLoading.value = false

                }
            })
    }

    fun doPaymentProcess(
        name: String,
        cardNumber: String,
        amount: String
    ) {
        val client = Client(AppConstant.OMISE_PKEY)
        val cardParam = CardParam(
            name = name,
            number = cardNumber,
            expirationMonth = 10,
            expirationYear = 2020,
            securityCode = "123"
        )
        val request = Token.CreateTokenRequestBuilder(cardParam).build()
        client.send(request, object : RequestListener<Token> {
            override fun onRequestSucceed(model: Token) {
                // you've got a Token!
                val d = Donation(
                    name,
                    model.id!!,
                    amount.toInt()
                )
                donate(d)
            }

            override fun onRequestFailed(throwable: Throwable) {

                UIHelper.showLongToastInCenter(context, throwable.message.toString())

            }
        })
    }

}