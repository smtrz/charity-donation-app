/*
package com.tahir.omiseassignment.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.tahir.omiseassignment.Helpers.DownloadImageTask
import com.tahir.omiseassignment.Models.Members
import com.tahir.testkotlinproject.R
import kotlinx.android.synthetic.main.member_list_item.view.*
import kotlinx.android.synthetic.main.repo_list_item.view.cardView
import kotlinx.android.synthetic.main.repo_list_item.view.image
import kotlinx.android.synthetic.main.repo_list_item.view.name


class MemberAdapter(
    var context: Context,
    var members: List<Members>?
)

    : RecyclerView.Adapter<MemberAdapter.NewsViewHolder>() {


    fun loadItems(newItems: List<Members>?) {

        members = newItems

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.member_list_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        try {

            var url: String =
                "http://download.runtastic.com/meetandcode/mobile_and_web_2016/images/members/" + members!![position].member_id + ".png"
            val entityMember = members!![position]
            holder.name!!.text =
                entityMember.member_first_name + " " + entityMember.member_last_name

            if (entityMember.member_distance_covered == 0.0) {
                holder.txtPaceValue!!.text = "0 min/km"
            } else {
                holder.txtPaceValue!!.text =
                    (entityMember.member_active_minutes / entityMember.member_distance_covered).toString() + " min/km"
            }




            DownloadImageTask(holder.img!!).execute(url);



            holder.cardView!!.setOnClickListener {

            }


        } catch (e: Exception) {
            //eat this one.

        }

    }

    override fun getItemCount(): Int {
        return if (members != null) {
            members!!.size
        } else {

            0
        }

    }


    class NewsViewHolder
        (itemView: View) : RecyclerView.ViewHolder(itemView) {


        internal var name: TextView? = itemView.name
        internal var img: ImageView? = itemView.image
        internal var txtPaceValue: TextView? = itemView.txtPaceValue

        internal var cardView: CardView? = itemView.cardView

    }


}*/
