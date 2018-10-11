package com.example.jaewonkim.jwasynctask

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var async = object : AsyncTask<Int, Int, String>() {

            //백그라운드로 값이 연산되는 부분
            override fun doInBackground(vararg params: Int?): String {
                var position = params[0]!!
                while(position < 100) {
                    Thread.sleep(1000)
                    position = position + 1
                    publishProgress(position)
                }

                return "다운로드가 끝났습니다."
            }

            //중간 값이 출력되는 부분
            override fun onProgressUpdate(vararg values: Int?) {
                super.onProgressUpdate(*values)
                textView_main.setText(values[0].toString() + "%")
            }

            //최종 값이 출력되는 부분
            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                textView_main.setText(result)
            }
        }
        async.execute(20)
    }
}
