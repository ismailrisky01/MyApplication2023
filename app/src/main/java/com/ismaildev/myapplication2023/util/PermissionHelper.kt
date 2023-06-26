package com.ismaildev.myapplication2023.util

import android.Manifest
import android.Manifest.permission
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionHelper {
    fun checkPermissionAll(activity: Activity):Boolean {
       return if (VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val result = ContextCompat.checkSelfPermission(
                activity,
                permission.READ_EXTERNAL_STORAGE
            )
            val result2 =
                ContextCompat.checkSelfPermission(activity, permission.CAMERA)
            result == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED
        } else {
            val result = ContextCompat.checkSelfPermission(
                activity,
                permission.READ_EXTERNAL_STORAGE
            )
            val result1 = ContextCompat.checkSelfPermission(
                activity,
                permission.WRITE_EXTERNAL_STORAGE
            )
            val result2 =
                ContextCompat.checkSelfPermission(activity, permission.CAMERA)
            result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED
        }
    }
    fun requestPermissionCamera(context: Activity){
        ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.CAMERA),1)
    }
    fun requestPermissionAll(context: Activity){
        ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE),1)
    }
}