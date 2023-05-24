package com.example.myworkmanagerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PeriodicWorkMangerLogs :
    ListAdapter<String, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }) {

    inner class DateViewHolder(private val inflate: View) : RecyclerView.ViewHolder(inflate) {
        fun bindData(date: String?, position: Int) {
            inflate.findViewById<TextView>(R.id.date_text).text = "$position) ${date?.trim()}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DateViewHolder).bindData(getItem(position), position)
    }
}