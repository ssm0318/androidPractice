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

        val view: View
        val vh: ListRowHolder
        if(convertView == null) {
            view = layoutInflater.inflate(R.layout.main_row, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        val titleTextView = view.findViewById<TextView>(R.id.title_listview)
        val idx = position + 1
        titleTextView.text = "#" + idx.toString() + " " + items.get(position).itemTitle
        titleTextView.tag = "title" + position.toString()

        val descriptionTextView = view.findViewById<TextView>(R.id.description_listview)
        descriptionTextView.text = items.get(position).itemDescription
        descriptionTextView.tag = "description" + position.toString()

        val editTextBtn = view.findViewById<ImageButton>(R.id.imageButton6)
        editTextBtn.tag = position

        val delTextBtn = view.findViewById<ImageButton>(R.id.imageButton5)
        delTextBtn.tag = position

        val checkbox = view.findViewById<CheckBox>(R.id.checkBox)
        checkbox.tag = position

        vh.isDone.setOnClickListener {
            val idx = vh.isDone.getTag() as Int
            rowListner.modifyItemState(idx, !items[idx].done!!)
        }

        vh.edit.setOnClickListener {
            val idx = vh.edit.getTag() as Int
            rowListner.modifyItemContent(idx, items[idx].itemTitle.toString(), items[idx].itemDescription.toString())
        }

        vh.del.setOnClickListener {
            val idx = vh.del.getTag() as Int
            rowListner.onItemDelete(idx)
        }

        return view
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

    private class ListRowHolder(row: View?) {
        val isDone: CheckBox = row!!.findViewById<CheckBox>(R.id.checkBox) as CheckBox
        val edit: ImageButton = row!!.findViewById<ImageButton>(R.id.imageButton6) as ImageButton
        val del: ImageButton = row!!.findViewById<ImageButton>(R.id.imageButton5) as ImageButton
    }
}