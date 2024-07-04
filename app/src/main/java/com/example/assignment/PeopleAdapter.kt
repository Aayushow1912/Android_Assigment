package com.example.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PeopleAdapter(var mList: List<Item>): RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

      inner  class PeopleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
           val logo : ImageView = itemView.findViewById(R.id.logo_IV)
           val titleTv : TextView = itemView.findViewById(R.id.tV2)
           val loc : TextView = itemView.findViewById(R.id.Tv)
           val pos : TextView = itemView.findViewById(R.id.tV3)

     }

    fun setFiltereedList(mList: List<Item>){
        this.mList = mList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
          val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item , parent , false)
          return PeopleViewHolder(view)

    }
    override fun getItemCount(): Int {
       return mList.size
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.logo.setImageResource(mList[position].picture)
        holder.titleTv.text = mList[position].name
        holder.loc.text = mList[position].category
        holder.pos.text = mList[position].price
    }

}

