package com.tahir.omiseassignment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.tahir.omiseassignment.Components.App
import com.tahir.omiseassignment.Helpers.UIHelper
import com.tahir.omiseassignment.Models.DonationResponse
import com.tahir.omiseassignment.Models.data
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
        ).observe(this,
            Observer<DonationResponse> { dr ->
                UIHelper.showAlertDialog(
                    "Thankyou for the contribution. ",
                    "Payment Successfull",
                    this@DonationActivity
                )

            })

    }

}