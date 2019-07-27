package com.vihari.apod.overviewfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vihari.apod.databinding.ApodItemBinding
import com.vihari.apod.domain.ApodProperty

class OverViewAdapter(val onClickListener: OnClickListener) : ListAdapter<ApodProperty,
        OverViewAdapter.ApodPropertyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodPropertyViewHolder {
        return ApodPropertyViewHolder(ApodItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ApodPropertyViewHolder, position: Int) {
        val apodProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(apodProperty)
        }
        holder.bind(apodProperty)
    }


    companion object DiffCallback : DiffUtil.ItemCallback<ApodProperty>() {
        override fun areItemsTheSame(oldItem: ApodProperty, newItem: ApodProperty): Boolean {
        return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ApodProperty, newItem: ApodProperty): Boolean {
                return oldItem == newItem
        }
    }

    class ApodPropertyViewHolder( private var binding: ApodItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            apodProperty: ApodProperty
        ) {
            binding.property = apodProperty
            binding.executePendingBindings()
        }

    }
    class OnClickListener(val clickListener:(apodProperty: ApodProperty) -> Unit) {
        fun onClick(apodProperty: ApodProperty) = clickListener(apodProperty)
    }

}

