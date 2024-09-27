package com.example.newproject180.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newproject180.Model.CategoryModel
import com.example.newproject180.databinding.ViewholderCategoryBinding

class CategoryAdapter(val items:MutableList<CategoryModel>):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private lateinit var context: Context
    inner class ViewHolder(val binding:ViewholderCategoryBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        context=parent.context
        val binding=ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleCat.text=item.title
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.picCat)
    }

    override fun getItemCount(): Int =items.size

}