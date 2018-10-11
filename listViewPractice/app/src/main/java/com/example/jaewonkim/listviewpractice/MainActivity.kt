package com.example.jaewonkim.listviewpractice

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_listview)

        listView.adapter = MyCustomAdapter(this)
    }

    private class MyCustomAdapter(context: Context): BaseAdapter() {
        private val mContext: Context
        private val names = arrayListOf<String>(
                "Donald Trump", "Steve Jobs", "Tim Cook"
        )

        init {
            mContext = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.main_row, parent, false)

            val nameTextView = rowMain.findViewById<TextView>(R.id.name_textView)
            nameTextView.text = names.get(position)

            val positionTextView = rowMain.findViewById<TextView>(R.id.position_textview)
            positionTextView.text = "Row Number: $position"

            return rowMain
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return names.size
        }

    }
}
