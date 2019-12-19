package com.tahir.omiseassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahir.omiseassignment.Adapters.CharityAdapter
import com.tahir.omiseassignment.Models.BaseClass
import com.tahir.omiseassignment.ViewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var retrofit: Retrofit

    lateinit var adapter: CharityAdapter

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init() {

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.callCharityAPI().observe(this,
            Observer<BaseClass> { charities ->
                bindAdapter(charities)

            })

    }

    fun bindAdapter(charities: BaseClass) {

        rv_repos?.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        adapter = CharityAdapter(applicationContext, charities.data)
        rv_repos?.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                LinearLayoutManager.HORIZONTAL
            )
        )
        rv_repos?.setAdapter(adapter)

    }

}
