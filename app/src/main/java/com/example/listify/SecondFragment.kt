package com.example.listify

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondFragment : Fragment(R.layout.fragment_second) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var itemViewModel: ItemViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)

        // Инициализация адаптера с передачей коллбэка для удаления
        adapter = Adapter(mutableListOf()) { item ->
            itemViewModel.delete(item) // Удаление элемента из ViewModel и базы данных
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Получаем ViewModel
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        // Наблюдаем за изменениями в базе данных
        itemViewModel.allItems.observe(viewLifecycleOwner) { items ->
            adapter.updateData(items)
        }
    }
}
