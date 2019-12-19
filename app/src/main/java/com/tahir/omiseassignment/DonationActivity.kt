package com.tahir.omiseassignment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.omise.android.api.Client
import co.omise.android.api.RequestListener
import co.omise.android.models.CardParam
import co.omise.android.models.Token
import com.google.gson.Gson
import com.tahir.omiseassignment.Components.App
import com.tahir.omiseassignment.Helpers.UIHelper
import com.tahir.omiseassignment.Models.Donation
import com.tahir.omiseassignment.Models.DonationResponse
import com.tahir.omiseassignment.Models.data
import com.tahir.omiseassignment.ViewModels.DonationActivityViewModel
import kotlinx.android.synthetic.main.donation_screen.*
import javax.inject.Inject

class DonationActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var gson: Gson

    lateinit var viewModel: DonationActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donation_screen)
        init()
    }

    fun init() {
        App.app.appLevelComponent.inject(this)

        viewModel = ViewModelProviders.of(this).get(DonationActivityViewModel::class.java)


        pay.setOnClickListener(this)
        val groupObject = intent.getStringExtra("charity")

        val charity: data = gson.fromJson(groupObject, data::class.java)
        edtHolderName.setText(charity.name)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.pay -> {
        /*        if (TextUtils.isEmpty(edtHolderName.text.toString()) || edtHolderName.text.toString().length <= 2) {
                    UIHelper.showSnackToast(v, "Please enter a valid name")
                    return;
                }
                if (TextUtils.isEmpty(edtAmount.text.toString()) || edtAmount.text.toString().toInt() == 0) {
                    UIHelper.showSnackToast(v, "Please enter a valid amount")
                    return;
                }
                if (TextUtils.isEmpty(edtCardNumber.text.toString()) || edtCardNumber.text.toString().length < 13) {
                    UIHelper.showSnackToast(v, "please enter a valid card number")
                    return;
                }*/
                val client = Client("pkey_test_5i9j6twpmps1pkss4xe")
                val cardParam = CardParam(
                    name = "JOHN Doe",
                    number = "4111111111111111",
                    expirationMonth = 10,
                    expirationYear = 2020,
                    securityCode = "123"
                )
                val request = Token.CreateTokenRequestBuilder(cardParam).build()
                client.send(request, object : RequestListener<Token> {
                    override fun onRequestSucceed(model: Token) {
                        // you've got a Token!
                        val d = Donation(
                            edtHolderName.text.toString(),
                            model.id!!,
                            1000
                        )
                        call_donation(d)
                    }

                    override fun onRequestFailed(throwable: Throwable) {
                        // something bad happened
                    }
                })

            }

        }


    }

    fun call_donation(donation: Donation) {

        viewModel.postDonation(donation).observe(this,
            Observer<DonationResponse> { donationResult ->

                Log.d("###Result", donationResult.error_message)
                // bindAdapter(charities)

            })
    }

}