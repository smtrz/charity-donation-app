package com.tahir.omiseassignment.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tahir.omiseassignment.AppConstant
import com.tahir.omiseassignment.Components.App
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
    @Inject
    @field:Named("omise_token")
    lateinit var retrofit_omise_token: Retrofit


    internal var dataLoading = MutableLiveData<Boolean>()
    internal var charityData = MutableLiveData<BaseClass>()
    internal var donationData = MutableLiveData<DonationResponse>()
    internal var paymentCodeData = MutableLiveData<Int>()
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
    ) {
        doPaymentProcess(name, cardnumber, "10", "2020", amount)
    }


    fun getData() {


        dataLoading.value = true

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

    fun getDonationResponseData(): MutableLiveData<DonationResponse> {


        return donationData
    }


    fun getPaymentCodesData(): MutableLiveData<Int> {
        return paymentCodeData
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

                    val updated_response: DonationResponse = response.body()!!

                    updated_response.status_code = response.code()

                    donationData.postValue(updated_response)


                }

                override fun onFailure(call: Call<DonationResponse>, t: Throwable) {
                    dataLoading.value = false

                }
            })
    }


    fun doPaymentProcess(
        cardName: String,
        cardNumber: String,
        cardExpMonth: String,
        cardExpyear: String, amount: String
    ) {

        var basic_auth = Credentials.basic(AppConstant.OMISE_PKEY, "")

        dataLoading.value = true
        val endpoints = retrofit_omise_token!!.create(EndpointsInterface::class.java)
        endpoints.createToken(
            basic_auth, cardName,
            cardNumber,
            cardExpMonth,
            cardExpyear
        )
            .enqueue(object : Callback<Map<String, Object>> {
                override fun onResponse(
                    call: Call<Map<String, Object>>,
                    response: Response<Map<String, Object>>
                ) {
                    dataLoading.value = false

                    val token: String? = response.body()!!.get("id").toString()


                    val d = Donation(
                        cardName,
                        token!!,
                        amount.toInt()
                    )
                    donate(d)

                }

                override fun onFailure(call: Call<Map<String, Object>>, t: Throwable) {
                    dataLoading.value = false

                }
            })
    }


}