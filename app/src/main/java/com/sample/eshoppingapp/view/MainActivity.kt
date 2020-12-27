package com.sample.eshoppingapp.view

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.eshoppingapp.R
import com.sample.eshoppingapp.databinding.ActivityMainBinding
import com.sample.eshoppingapp.model.Item
import com.sample.eshoppingapp.view.adapter.ItemAdapter
import com.sample.eshoppingapp.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener {

    private lateinit var context: Context
    private lateinit var itemsAdapter: ItemAdapter
    private lateinit var itemViewModel: ItemViewModel
    private var totalAmount: Double = 00.00
    private lateinit var mItems: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = this@MainActivity
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        itemsAdapter = ItemAdapter()
        itemsAdapter.setOnItemClickListener(this)
        binding.rvItems.adapter = itemsAdapter
        binding.rvItems.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.tvAmount.text = "₹ $totalAmount"

        itemViewModel.getItems(context)!!.observe(this, Observer {

            if (it == null) {
                Toast.makeText(context, "Please try again later", Toast.LENGTH_LONG).show()
            } else {
                mItems = it.toList() as ArrayList<Item>
                itemsAdapter.setItems(it.toList())
            }
        })
    }

    override fun onQuantityChange(item: Item, position: Int) {
        mItems[position] = item

        for (i: Item in mItems) {
            if (i.quantity > 0) {
                val totalPrice = (i.price * i.quantity)
                val gstAmount = totalPrice * (i.gstPercentage / 100)
                totalAmount += totalPrice + gstAmount
            }
        }

        tv_amount.text = "₹ $totalAmount"
    }
}