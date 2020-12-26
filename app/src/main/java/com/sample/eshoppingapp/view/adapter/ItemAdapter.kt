package com.sample.eshoppingapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.eshoppingapp.R
import com.sample.eshoppingapp.model.Items
import com.squareup.picasso.Picasso

public class ItemAdapter(private val mItems: List<Items>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItem: ImageView = itemView.findViewById(R.id.iv_item)
        val tvItemName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvItemPrice: TextView = itemView.findViewById(R.id.tv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.tvItemName.text = mItems[position].name
        holder.tvItemPrice.text = mItems[position].price.toString()
        Picasso.get()
            .load(mItems[position].imageUrl)
            .into(holder.ivItem)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}