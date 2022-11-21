package com.example.internet.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.internet.NetworkConnectivityObserver
import com.example.internet.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    lateinit var connectivityObserver: ConnectivityObserver
    lateinit var tv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alertDialog()
        tv =findViewById(R.id.tvStatus)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        connectivityObserver.observe().onEach {
            tv.text = it.toString()
            if(it.toString()=="Lost" ||it.toString()=="Losing"||it.toString()=="Unavailable"){
                alert()
            }
        }.launchIn(MainScope())

    }
    fun alertDialog(){
        val activity : AppCompatActivity = MainActivity()
        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor


    }
    fun alert (){
        val builder = AlertDialog.Builder(this , TRIM_MEMORY_BACKGROUND)
        builder.setTitle("Android Alert")
        builder.setMessage("there is no Internet")

        builder.setPositiveButton("OK") { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.yes, Toast.LENGTH_SHORT).show()
        }

        builder.show()

    }

}