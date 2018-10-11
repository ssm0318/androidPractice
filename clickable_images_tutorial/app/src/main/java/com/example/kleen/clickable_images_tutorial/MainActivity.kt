package com.example.kleen.clickable_images_tutorial

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        val intent = Intent(this@MainActivity, OrderActivity::class.java)
        startActivity(intent)
    }

    fun displayToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    //Shows a message that the donut image was clicked.
    fun showDonutOrder(view: View) {
        displayToast(getString(R.string.donut_order_message))
    }

     //Shows a message that the ice cream sandwich image was clicked.
    fun showIceCreamOrder(view: View) {
        displayToast(getString(R.string.ice_cream_order_message))
    }

     //Shows a message that the froyo image was clicked.
    fun showFroyoOrder(view: View) {
        displayToast(getString(R.string.froyo_order_message))
    }
}
