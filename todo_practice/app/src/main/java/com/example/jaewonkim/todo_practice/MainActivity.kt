package com.example.jaewonkim.todo_practice

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.appsdeveloperblog.firebasetodo.Constants
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

lateinit var mDataBase: DatabaseReference
var toDoItemList: MutableList<ToDoItem>? = null
lateinit var adapter: ToDoItemAdapter
private var listViewItems: ListView? = null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fab = findViewById<View>(R.id.add_btn) as Button

        add_btn.setOnClickListener {
            addNewItemDialog()
        }

        mDataBase = FirebaseDatabase.getInstance().reference
        toDoItemList = mutableListOf<ToDoItem>()
        adapter = ToDoItemAdapter(this, toDoItemList!!)
        listViewItems!!.setAdapter(adapter)
        mDataBase.orderByKey().addListenerForSingleValueEvent(itemListener)
    }

    var itemListener: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            addDataToList(dataSnapshot)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }
    }

    private fun addDataToList(dataSnapshot: DataSnapshot) {
        val items = dataSnapshot.children.iterator()

        if(items.hasNext()) {
            val toDoListIndex = items.next()
            val itemsIterator = toDoListIndex.children.iterator()

            while(itemsIterator.hasNext()) {
                val currentItem = itemsIterator.next()
                val todoItem = ToDoItem.create()

                val map = currentItem.getValue() as HashMap<String, Any>

                todoItem.objectId = currentItem.key
                todoItem.done = map.get("done") as Boolean?
                todoItem.itemText = map.get("itemText") as String?

                toDoItemList!!.add(todoItem);
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun addNewItemDialog() {
        val alert = AlertDialog.Builder(this)

        val itemEditText = EditText(this)
        alert.setMessage("Add New Item")
        alert.setTitle("Ender To Do Item Text")

        alert.setView(itemEditText)

        alert.setPositiveButton("Submit") {dialog, positiveButton ->
            val todoItem = ToDoItem.create()
            todoItem.itemText = itemEditText.text.toString()
            todoItem.done = false

            val newItem = mDataBase.child(Constants.FIREBASE_ITEM).push()
            todoItem.objectId = newItem.key

            newItem.setValue(todoItem)

            dialog.dismiss()
            Toast.makeText(this, todoItem.objectId + "번 항목으로 저장되었습니다.", Toast.LENGTH_LONG).show()
        }

        alert.show()
    }
}
