package com.example.jaewonkim.todo_app

import android.graphics.Paint
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemRowListner {
    private var items: MutableList<ToDoItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_listview)
        val fab = findViewById<View>(R.id.floatingActionButton3) as FloatingActionButton

        listView.adapter = MyCustomAdapter(this, items)

        fab.setOnClickListener { view ->
            textView.visibility = View.INVISIBLE
            addNewItemDialog()
        }
    }

    private fun addNewItemDialog() {
        val alert = AlertDialog.Builder(this)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 0, 50, 0)

        val itemEditTitle = EditText(this)
        val itemEditDescription = EditText(this)

        itemEditTitle.hint = "제목을 입력하세요"
        itemEditDescription.hint = "상세내용을 입력하세요"

        layout.addView(itemEditTitle)
        layout.addView(itemEditDescription)
        alert.setMessage("새로운 할 일 추가")
        alert.setTitle("할 일을 입력하세요")

        alert.setView(layout)

        alert.setPositiveButton("제출") { dialog, positiveButton ->
            val todoItem = ToDoItem.create()
            todoItem.itemTitle = itemEditTitle.text.toString()
            todoItem.itemDescription = itemEditDescription.text.toString()
            todoItem.done = false

            items.add(todoItem)

            dialog.dismiss()

            Toast.makeText(this, "성공적으로 저장되었습니다.", Toast.LENGTH_LONG).show()
        }

        alert.show()
    }

    override fun modifyItemState(itemObjectId: Int, isDone: Boolean) {
        val currItem = items[itemObjectId]
        val listView = findViewById<ListView>(R.id.main_listview)
        currItem.done = isDone

        var tTag = "title" + itemObjectId.toString()
        var dTag = "description" + itemObjectId.toString()

        var t = listView.findViewWithTag<TextView>(tTag)
        var d = listView.findViewWithTag<TextView>(dTag)

        if(items[itemObjectId].done == true) {
            t.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
            d.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
        } else {
            t.setPaintFlags(0)
            d.setPaintFlags(0)
        }
        (listView.adapter as MyCustomAdapter).notifyDataSetChanged()
    }

    override fun modifyItemContent(itemObjectId: Int, itemTitle: String, itemDescription: String) {
        val editBtn = findViewById<ImageButton>(R.id.imageButton6) as ImageButton
        println("I'm here")

        addEditItemDialog(itemObjectId)
    }

    override fun onItemDelete(itemObjectId: Int) {
        val delBtn = findViewById<ImageButton>(R.id.imageButton5) as ImageButton

        confirmDelete(itemObjectId)
    }

    private fun addEditItemDialog(itemObjectId: Int) {
        val alert = AlertDialog.Builder(this)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 0, 50, 0)

        val itemEditTitle = EditText(this)
        val itemEditDescription = EditText(this)

        val currItem = items[itemObjectId]

        itemEditTitle.setText(currItem.itemTitle)
        itemEditDescription.setText(currItem.itemDescription)

        layout.addView(itemEditTitle)
        layout.addView(itemEditDescription)
        alert.setMessage("새로운 할 일 추가")
        alert.setTitle("할 일을 입력하세요")

        alert.setView(layout)

        alert.setPositiveButton("수정") { dialog, positiveButton ->
            currItem.itemTitle = itemEditTitle.text.toString()
            currItem.itemDescription = itemEditDescription.text.toString()
            currItem.done = false

            items[itemObjectId] = currItem

            dialog.dismiss()

            Toast.makeText(this, "성공적으로 수정되었습니다.", Toast.LENGTH_LONG).show()
        }

        alert.show()
    }

    private fun confirmDelete(itemObjectId: Int) {
        val alert = AlertDialog.Builder(this)
        val listView = findViewById<ListView>(R.id.main_listview)

        alert.setMessage("삭제하시겠습니까?")
        alert.setPositiveButton("예") { dialog, which ->
            items.removeAt(itemObjectId)
            Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_LONG).show()
            dialog.dismiss()
            (listView.adapter as MyCustomAdapter).notifyDataSetChanged()
        }

        alert.setNegativeButton("아니오") { dialog, which ->
            dialog.dismiss()
        }

        alert.show()
    }
}
