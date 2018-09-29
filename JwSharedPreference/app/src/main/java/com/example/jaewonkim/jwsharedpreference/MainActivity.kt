package com.example.jaewonkim.jwsharedpreference

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*

var idx = 0

class MainActivity : AppCompatActivity() {
    private var listNotes = ArrayList<ToDoItem>()
    private var listView = findViewById<ListView>(R.id.list_items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<View>(R.id.btn_add) as Button

        fab.setOnClickListener { view ->
            addNewItemDialog()
        }

        listView = findViewById<ListView>(R.id.list_items)
        val listItems = arrayOfNulls<String>(listNotes.size)
        for(i in 0 until listNotes.size) {
            val text = listNotes[i].itemText
            listItems[i] = text
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter
    }

    private fun addNewItemDialog() {
        val alert = AlertDialog.Builder(this)

        val itemEditText = EditText(this)
        alert.setMessage("새로운 할 일 추가")
        alert.setTitle("할 일을 입력하세요.")

        alert.setView(itemEditText)

        alert.setPositiveButton("저장") { dialog, positiveButton ->
            val todoItem = ToDoItem.create()
            todoItem.itemText = itemEditText.text.toString()
            todoItem.done = false
            todoItem.objectId = idx++

            listNotes.add(todoItem)

            dialog.dismiss()
            Toast.makeText(this, "새로운 할 일이 추가되었습니다.", Toast.LENGTH_LONG).show()
        }

        alert.show()
    }
}
