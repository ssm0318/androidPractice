package com.example.jaewonkim.todo_app

class ToDoItem {
    companion object Factory {
        fun create(): ToDoItem = ToDoItem()
    }
    var objectId: Int? = null
    var itemTitle: String? = null
    var itemDescription: String? = null
    var done: Boolean? = false
}