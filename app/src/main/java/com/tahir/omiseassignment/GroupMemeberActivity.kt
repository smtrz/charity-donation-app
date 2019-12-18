/*
package com.tahir.omiseassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.tahir.omiseassignment.Adapters.MemberAdapter
import com.tahir.omiseassignment.Components.App
import com.tahir.omiseassignment.Models.Groups
import com.tahir.omiseassignment.Models.Members
import com.tahir.testkotlinproject.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject

class GroupMemeberActivity : AppCompatActivity() {
    @Inject
    lateinit var retrofit: Retrofit
    @Inject
    lateinit var gson: Gson
    lateinit var adapter: MemberAdapter
    internal var list: List<Members>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.app.appLevelComponent.inject(this)

        val groupObject = intent.getStringExtra("data")

        val model:Groups = gson.fromJson(groupObject, Groups::class.java)

        adapter = MemberAdapter(applicationContext, model.members)
        rv_repos?.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rv_repos?.setAdapter(adapter)

    }


}
*/
