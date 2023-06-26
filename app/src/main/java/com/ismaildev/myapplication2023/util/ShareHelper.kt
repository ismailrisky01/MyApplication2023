package com.ismaildev.myapplication2023.util

import android.content.Context
import android.content.Intent
import android.widget.Toast

class ShareHelper {
    fun shareToWhatsApp(context: Context,message:String){
        // Creating intent with action send
        val intent = Intent(Intent.ACTION_SEND)

        // Setting Intent type
        intent.type = "text/plain"

        // Setting whatsapp package name
        intent.setPackage("com.whatsapp")

        // Give your message here
        intent.putExtra(Intent.EXTRA_TEXT, message)

        // Checking whether whatsapp is installed or not
        if (intent.resolveActivity(context.packageManager) == null) {
            Toast.makeText(context,
                "Please install whatsapp first.",
                Toast.LENGTH_SHORT).show()
            return
        }

        // Starting Whatsapp
        context.startActivity(intent)
    }
}