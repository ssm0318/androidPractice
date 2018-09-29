package com.example.jaewonkim.todo_app

interface ItemRowListner {
    fun modifyItemState(itemObjectId: Int, isDone: Boolean)
    fun modifyItemContent(itemObjectId: Int, itemTitle: String, itemDescription: String)
    fun onItemDelete(itemObjectId: Int)
}