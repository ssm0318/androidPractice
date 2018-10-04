package com.example.jaewonkim.todo_app

import android.graphics.Paint
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemRowListener {
    //할 일들을 가지고 있는 array 비스무리한 것 (array 보다 자유롭게 변경 가능한 형태의 list)
    private var items: MutableList<ToDoItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_listview)
        val fab = findViewById<View>(R.id.floatingActionButton3) as FloatingActionButton

        //list view 에 데이터 뿌려주는 adapter 지정.
        listView.adapter = MyCustomAdapter(this, items)

        //새로운 할 일을 추가하는 버튼을 누르면
        fab.setOnClickListener {
            //'새로운 할 일을 추가해보세요'라는 안내문이 사라지고
            textView.visibility = View.INVISIBLE
            //할 일 추가를 위한 팝업이 뜬다.
            addNewItemDialog()
        }
    }

    //새로운 할 일 추가
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

    //완료 여부 상태의 변경
    override fun modifyItemState(itemObjectId: Int, isDone: Boolean) {
        val currItem = items[itemObjectId]
        val listView = findViewById<ListView>(R.id.main_listview)
        currItem.done = isDone

        var tTag = "title" + itemObjectId.toString()
        var dTag = "description" + itemObjectId.toString()

        var t = listView.findViewWithTag<TextView>(tTag)
        var d = listView.findViewWithTag<TextView>(dTag)

        //완료했다고 체크되면 제목과 설명 텍스트를 strike through
        if(items[itemObjectId].done == true) {
            t.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
            d.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
        } else { //다시 누르면 strike through 없앰
            t.setPaintFlags(0)
            d.setPaintFlags(0)
        }
        //변경 시 바로 view 에 반영되도록 변경사항이 있음을 adapter에 알려줌.
        (listView.adapter as MyCustomAdapter).notifyDataSetChanged()
    }

    //할 일 내용 수정
    override fun modifyItemContent(itemObjectId: Int, itemTitle: String, itemDescription: String) {
        addEditItemDialog(itemObjectId)
    }

    //할 일 삭제
    override fun onItemDelete(itemObjectId: Int) {
        confirmDelete(itemObjectId)
    }

    //할 일 수정 함수 (할 일 추가와 같은 로직)
    private fun addEditItemDialog(itemObjectId: Int) {
        val alert = AlertDialog.Builder(this)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 0, 50, 0)

        val itemEditTitle = EditText(this)
        val itemEditDescription = EditText(this)

        val currItem = items[itemObjectId]

        // 기존 내용 보여주기
        itemEditTitle.setText(currItem.itemTitle)
        itemEditDescription.setText(currItem.itemDescription)

        layout.addView(itemEditTitle)
        layout.addView(itemEditDescription)
        alert.setMessage("기존 할 일 수정")
        alert.setTitle("수정할 정보를 입력하세요")

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

    //할 일 삭제 함수
    private fun confirmDelete(itemObjectId: Int) {
        val alert = AlertDialog.Builder(this)
        val listView = findViewById<ListView>(R.id.main_listview)

        alert.setMessage("삭제하시겠습니까?")
        alert.setPositiveButton("예") { dialog, which ->
            items.removeAt(itemObjectId)
            Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_LONG).show()
            dialog.dismiss()
            //삭제된 할 일이 없어진 상태로 view 가 변경되도록 adapter 에 변경사항이 있음을 알려줌.
            (listView.adapter as MyCustomAdapter).notifyDataSetChanged()
        }

        alert.setNegativeButton("아니오") { dialog, which ->
            dialog.dismiss()
        }

        alert.show()
    }
}
