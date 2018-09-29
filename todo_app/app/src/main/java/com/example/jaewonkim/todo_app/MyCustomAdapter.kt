package com.example.jaewonkim.todo_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView

class MyCustomAdapter(context: Context, private val items: MutableList<ToDoItem>): BaseAdapter() {
    private val mContext: Context
    private var rowListner: ItemRowListner = context as ItemRowListner

    init {
        mContext = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val rowMain = layoutInflater.inflate(R.layout.main_row, parent, false)

        val titleTextView = rowMain.findViewById<TextView>(R.id.title_listview)
        val idx = position + 1
        titleTextView.text = "#" + idx.toString() + " " + items.get(position).itemTitle

        val descriptionTextView = rowMain.findViewById<TextView>(R.id.description_listview)
        descriptionTextView.text = items.get(position).itemDescription

        val editTextBtn = rowMain.findViewById<ImageButton>(R.id.imageButton6)
        editTextBtn.tag = position

        val delTextBtn = rowMain.findViewById<ImageButton>(R.id.imageButton5)
        delTextBtn.tag = position

        val checkbox = rowMain.findViewById<CheckBox>(R.id.checkBox)
        checkbox.tag = position

        checkbox.setOnClickListener {
            val idx = checkbox.getTag() as Int
            rowListner.modifyItemState(idx, !items[idx].done!!)
        }

        editTextBtn.setOnClickListener {
            val idx = editTextBtn.getTag() as Int
            rowListner.modifyItemContent(idx, items[idx].itemTitle.toString(), items[idx].itemDescription.toString())
        }

        delTextBtn.setOnClickListener {
            val idx = delTextBtn.getTag() as Int
            rowListner.onItemDelete(idx)
        }

        return rowMain
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}