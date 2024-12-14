package com.example.listify

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listify.R

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val itemText: TextView = itemView.findViewById(R.id.itemText)
}
