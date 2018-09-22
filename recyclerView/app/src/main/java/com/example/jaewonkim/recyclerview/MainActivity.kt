package com.example.jaewonkim.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
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
        jw_recyclerView.layoutManager = GridLayoutManager(this, 3)
    }
    class MainRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var images = arrayOf(R.drawable.rail0, R.drawable.rail1, R.drawable.rail2, R.drawable.rail3)

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_layout, parent, false)
        }

        override fun getItemCount(): Int {
            return 4
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            var view = holder as CustomViewHolder
            view!!.imageView!!.setImageResource(images[position])
        }

        class CustomViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
            var imageView : ImageView? = null
            init {
                imageView = view!!.findViewById(R.id.layoutItem_imageView)
            }
        }

//        var images = arrayOf(R.drawable.rail0, R.drawable.rail1, R.drawable.rail2, R.drawable.rail3)
//
//        //각각의 아이템의 디자인 레이아웃을 불러오는 부분
//        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
//            var view = LayoutInflater.from(p0!!.context).inflate(R.layout.item_layout, p0,false)
//            var params = view.layoutParams
//            params.width = p0.measuredWidth / 3
//            params.height = p0.measuredHeight / 3
//            view.layoutParams = params
//
//            return CustomViewHolder(view)
//        }
//
//        //각각의 아이템에 데이터를 바인딩 시켜주는 기능
//        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
//            var view = p0 as CustomViewHolder
//            view!!.imageview!!.setImageResource(images[p1])
//        }
//
//        class CustomViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
//
//            var imageview : ImageView? = null
//            init {
//                imageview = view!!.findViewById(R.id.layoutItem_imageView)
//            }
//        }
//
//        //아이템의 갯수를 카운터 하는 부분
//        override fun getItemCount(): Int {
//            return 4
//        }
    }
}


