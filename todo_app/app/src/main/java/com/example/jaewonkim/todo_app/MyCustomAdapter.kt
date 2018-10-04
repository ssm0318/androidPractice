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
    private val mContext: Context //이것의 필요성에 대한 설명이 필요합니당...1
    private var rowListner: ItemRowListener = context as ItemRowListener //각 행 (할 일)에 대한 변동사항에 귀를 기울임

    //이것의 필요성에 대한 설명이 필요합니당...2
    init {
        mContext = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mContext)

        val view: View
        val vh: ListRowHolder
        //받아 온 view 에서 보여줄게 없을 경우
        if(convertView == null) {
            view = layoutInflater.inflate(R.layout.main_row, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh  //설명 필요...1
        } else { //기존의 view 에서 사용할 수 있는게 있으면
            view = convertView
            vh = view.tag as ListRowHolder //설명 필요...2
        }

        //각 row 의 제목 뿌려줌
        val titleTextView = view.findViewById<TextView>(R.id.title_listview)
        val idx = position + 1
        titleTextView.text = "#" + idx.toString() + " " + items.get(position).itemTitle
        titleTextView.tag = "title" + position.toString()

        //각 row 의 상세정보 뿌려줌
        val descriptionTextView = view.findViewById<TextView>(R.id.description_listview)
        descriptionTextView.text = items.get(position).itemDescription
        descriptionTextView.tag = "description" + position.toString()

        val editTextBtn = view.findViewById<ImageButton>(R.id.imageButton6)
        editTextBtn.tag = position //몇 번째 index 의 할 일에 대한 버튼인지 저장.

        val delTextBtn = view.findViewById<ImageButton>(R.id.imageButton5)
        delTextBtn.tag = position //몇 번째 index 의 할 일에 대한 버튼인지 저장.

        val checkbox = view.findViewById<CheckBox>(R.id.checkBox)
        checkbox.tag = position //몇 번째 index 의 할 일에 대한 버튼인지 저장.

        vh.isDone.setOnClickListener {
            val idx = vh.isDone.getTag() as Int  //몇 번째 index 의 할 일에 대한 버튼인지 불러옴.
            rowListner.modifyItemState(idx, !items[idx].done!!) //해당 번호의 할 일 완료 여부 상태 변경
        }

        vh.edit.setOnClickListener {
            val idx = vh.edit.getTag() as Int //몇 번째 index 의 할 일에 대한 버튼인지 불러옴.
            rowListner.modifyItemContent(idx, items[idx].itemTitle.toString(), items[idx].itemDescription.toString()) //해당 번호의 할 일 내용 변경
        }

        vh.del.setOnClickListener {
            val idx = vh.del.getTag() as Int //몇 번째 index 의 할 일에 대한 버튼인지 불러옴.
            rowListner.onItemDelete(idx) //해당 번호의 할 일 삭제
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