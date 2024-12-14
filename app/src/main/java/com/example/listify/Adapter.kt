package com.example.listify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class Adapter(
    private var items: MutableList<Item>,
    private val onDeleteClick: (Item) -> Unit // Коллбэк для удаления
) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.itemText)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.itemText.text = item.text

        // Обработчик нажатия на крестик
        holder.deleteButton.setOnClickListener {
            onDeleteClick(item) // Вызов коллбэка для удаления
        }
    }

    override fun getItemCount(): Int = items.size

    // Метод для обновления списка
    fun updateData(newItems: List<Item>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
