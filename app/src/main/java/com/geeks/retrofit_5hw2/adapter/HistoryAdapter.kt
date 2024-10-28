package com.geeks.retrofit_5hw2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geeks.retrofit_5hw2.databinding.ItemHistoryBinding
import com.geeks.retrofit_5hw2.room.LoveEntity
import com.geeks.retrofit_5hw2.ui.fragments.loveCalculator.HistoryFragment

class HistoryAdapter(private val onItemLongClickListener: HistoryFragment)
    : ListAdapter<LoveEntity, HistoryAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(historyEntity: LoveEntity) = with(binding) {
            historyEntity.apply {
                tvFname.text = firstName
                tvSname.text = secondName
                tvPercent.text = percentage
            }
            root.setOnLongClickListener {
                onItemLongClickListener.onItemLongClicked(historyEntity)
                true
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<LoveEntity>() {
        override fun areItemsTheSame(oldItem: LoveEntity, newItem: LoveEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LoveEntity, newItem: LoveEntity): Boolean {
            return oldItem.firstName == newItem.firstName
                    && oldItem.secondName == newItem.secondName
                    && oldItem.percentage == newItem.percentage
        }
    }
    interface OnItemLongClickListener {
        fun onItemLongClicked(historyEntity: LoveEntity)
    }
}