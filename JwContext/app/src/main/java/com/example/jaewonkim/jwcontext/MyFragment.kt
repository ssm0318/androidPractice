package com.example.jaewonkim.jwcontext

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

//MyFragment와 myfragment.xml을 연결시켜준 것.
class MyFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.myfragment, container, false)

        //activity 적어줘서 부모 view인 main activity에서 context 받아옴.
        Toast.makeText(activity, "토스트메시지 입니다.", Toast.LENGTH_LONG).show()

        return view
    }
}