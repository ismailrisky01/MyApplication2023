package com.ismaildev.myapplication2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ismaildev.myapplication2023.data.retrofit.ApiService
import com.ismaildev.myapplication2023.util.NotificationHelper
import com.ismaildev.myapplication2023.util.mylog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    var name:String="Hallo"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mylog("Hallo Word",this)
        name
//        NotificationHelper().showNotification(this,"Hallo","Word")

        GlobalScope.launch {
            mylog("Coba get")
            try {
                ApiService.getService().getMovie()

            }catch (exception:Exception){
                mylog(exception.message.toString())
            }
        }

    }
}