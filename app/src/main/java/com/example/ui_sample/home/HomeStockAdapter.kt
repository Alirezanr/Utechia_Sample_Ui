package com.example.ui_sample.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_sample.R
import com.example.ui_sample.databinding.ItemHomeGalleryBinding
import com.example.ui_sample.databinding.ItemHomeStockBinding
import com.example.ui_sample.util.toPriceFormat

class HomeStockAdapter(
    val onStockItemClicked: (StockData) -> Unit
) :
    RecyclerView.Adapter<HomeStockAdapter.PageViewHolder>() {
    var list = ArrayList<StockData>()


    fun upDateDataSet(data: List<StockData>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }


    inner class PageViewHolder(private val itemBinding: ItemHomeStockBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: StockData, isLastItem: Boolean) {
            itemBinding.apply {
                if (isLastItem) {
                    viewSeparator.visibility = View.GONE
                }

                txtStockName.text = data.stockName


                txtStockPrice.text = String.format(
                    this.root.context.getString(R.string.lira),
                    data.stockPrice.toPriceFormat()
                )


                this.root.setOnClickListener {
                    onStockItemClicked(data)
                }

            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = ItemHomeStockBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PageViewHolder(view)

    }


    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(list[position], list.lastIndex == position)
    }
}