package com.demo.cl.app.presentation.ui.adapter

import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.demo.cl.app.presentation.entity.CityModel
import com.demo.cl.clean_architecture.R

class CityAdapterBind: ListAdapter<CityModel, DataBoundViewHolder<ItemCityBindBinding>>(object:DiffUtil.ItemCallback<CityModel>(){
    override fun areItemsTheSame(oldItem: CityModel?, newItem: CityModel?): Boolean {
       return oldItem?.uid==newItem?.uid
    }

    override fun areContentsTheSame(oldItem: CityModel?, newItem: CityModel?): Boolean {
        return oldItem?.city==newItem?.city
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<ItemCityBindBinding> {
        val binding = DataBindingUtil.inflate<ItemCityBindBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_city_bind,
                parent,
                false
        )
        return DataBoundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<ItemCityBindBinding>, position: Int) {
            holder.binding.city=getItem(position)
            holder.binding.executePendingBindings()
    }
}