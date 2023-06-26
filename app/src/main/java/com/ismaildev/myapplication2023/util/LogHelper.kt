package com.ismaildev.myapplication2023.util

import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment


fun mylog(message:Any){
    Log.d("This Log", message.toString())
}
fun mylog(message:Any,activity:Activity){
    Log.d("This Log at ${activity.localClassName} ", message.toString())
}
fun mylog(message:Any,activity:Activity,laneNumber:Int=0){
    Log.d("This Log at ${activity.localClassName} lane Number :$laneNumber", message.toString())
}
fun mylog(message:Any,fragment: Fragment){
    Log.d("This Log at ${fragment.activity?.localClassName}", message.toString())
}