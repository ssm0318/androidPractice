package com.example.jaewonkim.todo_app

//각각의 row에 대한 삭제/수정/완료여부표시를 관리.
//MyCustomAdaper에서 rowListener object라고 선언을 했기 때문에 이처럼 작동하는 것임.
interface ItemRowListener {
    fun modifyItemState(itemObjectId: Int, isDone: Boolean) //완료 여부
    fun modifyItemContent(itemObjectId: Int, itemTitle: String, itemDescription: String) //수정
    fun onItemDelete(itemObjectId: Int) //삭제
}