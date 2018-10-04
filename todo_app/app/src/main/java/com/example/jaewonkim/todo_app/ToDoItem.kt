package com.example.jaewonkim.todo_app

//할 일 아이템 class
class ToDoItem {
    //Java의 static method에 해당
    companion object Factory {
        fun create(): ToDoItem = ToDoItem()
    }
    var objectId: Int? = null
    var itemTitle: String? = null
    var itemDescription: String? = null
    var done: Boolean? = false  //완료여부
}