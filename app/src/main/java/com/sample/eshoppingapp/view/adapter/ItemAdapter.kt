package com.sample.eshoppingapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.eshoppingapp.R
import com.sample.eshoppingapp.model.Item
import com.squareup.picasso.Picasso

public class ItemAdapter() :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var mItems: List<Item> = ArrayList()
    private lateinit var mListener: OnItemClickListener

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItem: ImageView = itemView.findViewById(R.id.iv_item)
        val tvItemName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvItemPrice: TextView = itemView.findViewById(R.id.tv_price)
        val btnAdd: Button = itemView.findViewById(R.id.btn_add)
        val tvPlus: TextView = itemView.findViewById(R.id.tv_plus)
        val tvMinus: TextView = itemView.findViewById(R.id.tv_minus)
        val tvQty: TextView = itemView.findViewById(R.id.tv_qty)
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
            .placeholder(R.drawable.ic_product_image_thumbnail)
            .error(R.drawable.ic_product_image_thumbnail)
            .resize(400, 400)
            .into(holder.ivItem)

        holder.btnAdd.setOnClickListener {
            it.visibility = View.GONE
            holder.tvMinus.visibility = View.VISIBLE
            holder.tvPlus.visibility = View.VISIBLE
            holder.tvQty.visibility = View.VISIBLE

            holder.tvMinus.isEnabled = false
            holder.tvPlus.isEnabled = true

            val qty: Int = holder.tvQty.text.toString().toInt()
            mItems[position].quantity = qty
            mListener.onQuantityChange(mItems[position], position)
        }

        holder.tvPlus.setOnClickListener {
            var qty: Int = holder.tvQty.text.toString().toInt()
            if (qty < 5) {
                qty += 1
                holder.tvQty.text = qty.toString()

                if (qty == 5) {
                    holder.tvPlus.isEnabled = false
                }

                holder.tvMinus.isEnabled = true
                mItems[position].quantity = qty
                mListener.onQuantityChange(mItems[position], position)
            }
        }

        holder.tvMinus.setOnClickListener {
            var qty: Int = holder.tvQty.text.toString().toInt()
            if (qty > 1) {
                qty -= 1
                holder.tvQty.text = qty.toString()

                if (qty == 1) {
                    holder.tvMinus.isEnabled = false
                }

                holder.tvPlus.isEnabled = true
                mItems[position].quantity = qty
                mListener.onQuantityChange(mItems[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun setItems(items: List<Item>) {
        mItems = emptyList()
        mItems = items
        notifyDataSetChanged()
    }

    interface OnItemClickListener {

        fun onQuantityChange(item: Item, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mListener = onItemClickListener
    }
}