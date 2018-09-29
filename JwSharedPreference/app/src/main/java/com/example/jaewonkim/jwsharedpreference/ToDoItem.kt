package com.example.jaewonkim.jwsharedpreference

class ToDoItem {
    companion object Factory {
        fun create(): ToDoItem = ToDoItem()
    }

    var objectId: Int? = null
    var itemText: String? = null
    var done: Boolean? = false
}