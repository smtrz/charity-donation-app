package com.tahir.omiseassignment.Activities

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.tahir.omiseassignment.Components.App
import com.tahir.omiseassignment.Enums.Codes
import com.tahir.omiseassignment.Helpers.UIHelper
import com.tahir.omiseassignment.Models.DonationResponse
import com.tahir.omiseassignment.Models.data
import com.tahir.omiseassignment.R
import com.tahir.omiseassignment.ViewModels.DonationActivityViewModel
import kotlinx.android.synthetic.main.donation_screen.*
import javax.inject.Inject

class DonationActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var context: Context
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

        viewModel.getDonationResponse().observe(
            this,
            object : Observer<DonationResponse> {
                override fun onChanged(response: DonationResponse?) {

                    if (response != null) {

                        when (response.status_code) {
                            200 ->

                                when (response.status) {
                                    Codes.failed.toString() ->
                                        UIHelper.showShortToastInCenter(
                                            context,
                                            response.failure_message!!
                                        )


                                    Codes.successful.toString() ->


                                        UIHelper.showAlertDialog(
                                            "Thankyou for the contribution. ",
                                            "Payment successful " + response.status_code,
                                            this@DonationActivity
                                        )


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
                                when (response?.code) {
                                    Codes.invalid_charge.toString() ->
                                        UIHelper.showShortToastInCenter(context, "Invalid charge")


                                    Codes.used_token.toString() ->

                                        UIHelper.showShortToastInCenter(
                                            context,
                                            "Token is already used"
                                        )
                                }


                        }
                        // because we will come back to this activity for a new transaction therefore we need not to get hold of the data

                        viewModel.getDonationResponse().postValue(null)

                    }

                }
            })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.pay -> {
                UIHelper.hideSoftKeyboard(this@DonationActivity, this.currentFocus)


                if (TextUtils.isEmpty(edtHolderName.text.toString()) || edtHolderName.text.toString().length <= 2) {
                    UIHelper.showSnackToast(v, "Please enter a valid name")
                    return;
                }
                if (TextUtils.isEmpty(edtAmount.text.toString()) || edtAmount.text.toString().toInt() == 0) {
                    UIHelper.showSnackToast(v, "Please enter a valid amount")
                    return;
                }

                if (edtAmount.text.toString().toInt() < 2000) {
                    UIHelper.showSnackToast(v, "Amount should be atmost 2000")
                    return;
                }
                if (edtAmount.text.toString().toInt() > 1000000) {
                    UIHelper.showSnackToast(v, "Amount should be under 1000000")
                    return;
                }
                if (TextUtils.isEmpty(edtCardNumber.text.toString()) || edtCardNumber.text.toString().length < 13) {
                    UIHelper.showSnackToast(v, "please enter a valid card number")
                    return;
                }

                postDonation();
            }
        }


    }

    fun postDonation() {
        viewModel.postDonation(
            edtHolderName.text.toString(),
            edtCardNumber.text.toString(),
            edtAmount.text.toString()
        )


    }


}