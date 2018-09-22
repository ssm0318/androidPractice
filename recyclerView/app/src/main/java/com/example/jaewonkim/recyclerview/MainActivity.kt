package com.example.jaewonkim.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jw_recyclerView.adapter = MainRecyclerViewAdapter()
        jw_recyclerView.layoutManager = GridLayoutManger(this, 3)
    }
    class MainRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        //각각의 아이템의 디자인 레이아웃을 불러오는 부분
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_layout, parent, false)

            return CustomViewHolder(view)
        }

        class CustomViewHolder(view: View?) : RecyclerView.ViewHolder(){
            var imageview : ImageView? = null
            init {
                imageview = view.findViewById(R.id.layoutItem_imageView)
            }
        }

        //아이템의 갯수를 카운터 하는 부분
        override fun getItemCount(): Int {
            return 4
        }

        //각각의 아이템에 데이터를 바인딩 시켜주는 기능
        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

        }

    }
}


