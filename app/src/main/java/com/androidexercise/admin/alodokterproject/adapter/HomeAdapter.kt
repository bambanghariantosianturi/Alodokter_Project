package com.androidexercise.admin.alodokterproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidexercise.admin.alodokterproject.R
import com.androidexercise.admin.alodokterproject.datalocal.Items
import com.bumptech.glide.Glide

class HomeAdapter(private val list: List<Items>) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val items = list[position]
        holder.onBind(items)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tv_title)
        private val img: ImageView = view.findViewById(R.id.iv_image)

        fun onBind(items: Items) {
            tvTitle.text = items.name

            Glide.with(itemView).load(items.image).into(img)
        }
    }
}