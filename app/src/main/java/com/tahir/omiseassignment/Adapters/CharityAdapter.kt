package com.tahir.omiseassignment.Adapters

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tahir.omiseassignment.Activities.DonationActivity
import com.tahir.omiseassignment.Components.App
import com.tahir.omiseassignment.Models.data
import com.tahir.omiseassignment.R
import kotlinx.android.synthetic.main.charity_list_item.view.*
import javax.inject.Inject


class CharityAdapter(
    var context: Context,
    var charities: List<data>?


)

    : RecyclerView.Adapter<CharityAdapter.GrpViewHolder>() {
    @Inject
    lateinit var gson: Gson

    init {
        App.app.appLevelComponent.inject(this)
    }

    fun loadItems(newItems: List<data>?) {

        charities = newItems

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrpViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.charity_list_item, parent, false)
        return GrpViewHolder(view)
    }

    override fun onBindViewHolder(holder: GrpViewHolder, position: Int) {
        try {


            holder.name!!.text = charities?.get(position)?.name

            holder.heading!!.text =
                charities!!.get(position).id.toString()

            Picasso.get().load(charities!!.get(position).logo_url).into(holder.img)

            holder.cardView!!.setOnClickListener {
                val i: Intent = Intent(context, DonationActivity::class.java)
                i.setFlags(FLAG_ACTIVITY_NEW_TASK)
                i.putExtra("charity", gson.toJson(charities!![position]))
                context.startActivity(i)

            }


        } catch (e: Exception) {
            //eat this one.

        }

    }

    override fun getItemCount(): Int {
        return if (charities != null) {
            charities!!.size
        } else {

            0
        }

    }


    class GrpViewHolder
        (itemView: View) : RecyclerView.ViewHolder(itemView) {


        internal var name: TextView? = itemView.name
        internal var img: ImageView? = itemView.image
        internal var heading: TextView? = itemView.heading

        internal var cardView: CardView? = itemView.cardView

    }


}