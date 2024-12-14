package com.example.listify

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var inputText: EditText
    private val dataList = mutableListOf<String>()
    private val adapter = Adapter(dataList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Инициализация EditText с корректным ID
        inputText = findViewById(R.id.editTextText)

        // Убедитесь, что id элемента корректен в XML и он отображается на экране
        inputText.setText("Текст для теста") // Установите тестовый текст для проверки

        // Настройка RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Настройка отступов для системных панелей
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Получаем отступы для системных панелей и применяем их
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Настройка кнопок
        findViewById<Button>(R.id.button_input).setOnClickListener { saveTextToList() }
        findViewById<Button>(R.id.button_home).setOnClickListener { /* Логика для button_home */ }
        findViewById<Button>(R.id.button_secondScreen).setOnClickListener{
            val intent = Intent(this, SecondScreen::class.java)
            startActivity(intent)
        }
    }

    private fun saveTextToList() {
        val text = inputText.text.toString()
        if (text.isNotEmpty()) {
            dataList.add(text) // Добавляем текст в список
            adapter.notifyDataSetChanged() // Уведомляем адаптер об изменении данных
            inputText.text.clear() // Очищаем поле ввода
        }
    }

    private fun localSave() {
        // Реализация будет позже
    }

    
}
