package com.example.listify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listify.R

class Adapter(private val dataList: MutableList<String>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    // Вложенный класс для ViewHolder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.itemText) // ID TextView в layout
    }

    // Создаём новый ViewHolder, используя item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false) // Используй свой item layout
        return ViewHolder(view)
    }

    // Привязываем данные к ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataList[position]
    }

    // Возвращаем размер списка
    override fun getItemCount(): Int = dataList.size
}
