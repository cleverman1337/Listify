package com.example.listify

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var inputText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var itemViewModel: ItemViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация элементов интерфейса
        inputText = view.findViewById(R.id.editTextText)
        recyclerView = view.findViewById(R.id.recyclerView)
        val buttonInput = view.findViewById<ImageButton>(R.id.button_input)

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

        // Обработчик для кнопки
        buttonInput.setOnClickListener { saveTextToList() }
    }

    private fun saveTextToList() {
        val text = inputText.text.toString()
        if (text.isNotEmpty()) {
            val item = Item(text = text)
            itemViewModel.insert(item) // Сохраняем данные в базе
            inputText.text.clear() // Очищаем поле ввода
        }
    }
}
