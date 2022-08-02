package com.example.ui_sample.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ui_sample.databinding.ItemHomeGalleryBinding

class HomeGalleryPageAdapter :
    RecyclerView.Adapter<HomeGalleryPageAdapter.PageViewHolder>() {
    var list = ArrayList<GalleyData>()


    fun upDateDataSet(data: List<GalleyData>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }


    inner class PageViewHolder(val itemBinding: ItemHomeGalleryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: GalleyData) {
            itemBinding.apply {
                Glide.with(itemBinding.root.context)
                    .load(data.imageResource)
                    .into(imgGalleyCover)

                txtGalleryTitle.text = data.title
                txtGalleryBody.text = data.body
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = ItemHomeGalleryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PageViewHolder(view)

    }


    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(list[position])
    }
}