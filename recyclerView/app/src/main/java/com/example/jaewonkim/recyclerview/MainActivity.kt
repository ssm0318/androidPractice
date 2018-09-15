package com.example.jaewonkim.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    class MainRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        //각각의 아이템에 데이터를 바인딩 시켜주는 기능
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        //각각의 아이템의 디자인 레이아웃을 불러오는 부분
        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        //아이템의 갯수를 카운터 하는 부분
        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}
