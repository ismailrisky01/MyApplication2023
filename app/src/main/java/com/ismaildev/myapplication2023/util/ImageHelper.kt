package com.ismaildev.myapplication2023.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageHelper {

    @Throws(IllegalArgumentException::class)
    fun convert(base64Str: String): Bitmap? {
        val decodedBytes = Base64.decode(
            base64Str.substring(base64Str.indexOf(",") + 1),
            Base64.DEFAULT
        )
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    fun convert(bitmap: Bitmap): String? {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }

    fun compressPhoto(photoPath: String?): File? {
        val bitmap = BitmapFactory.decodeFile(photoPath)
        val f = File(photoPath)
        val width = bitmap.width / 4
        val height = bitmap.height / 4
        val bmpStream = ByteArrayOutputStream()
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 60, bmpStream)
        val fo: FileOutputStream
        return try {
            fo = FileOutputStream(f.absolutePath, false)
            fo.write(bmpStream.toByteArray())
            fo.flush()
            fo.close()
            File(f.absolutePath)
        } catch (e: IOException) {
            Log.d("ErrorCompress", e.toString())
            null
        }
    }

    fun compressPhotoWithExif(photoPath: String): File? {
        val bitmap = BitmapFactory.decodeFile(photoPath)
        val f = File(photoPath)
        val width = bitmap.width
        val height = bitmap.height
        var exif: ExifInterface? = null
        try {
            exif = ExifInterface(photoPath)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val rotation =
            exif!!.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        val rotationInDegrees = exifToDegrees(rotation)
        val matrix = Matrix()
        if (rotation != 0) {
            matrix.preRotate(rotationInDegrees.toFloat())
        }
        val adjustedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
        val bmpStream = ByteArrayOutputStream()
        adjustedBitmap.compress(Bitmap.CompressFormat.JPEG, 50, bmpStream)
        val fo: FileOutputStream
        return try {
            fo = FileOutputStream(f.absolutePath, false)
            fo.write(bmpStream.toByteArray())
            fo.flush()
            fo.close()
            File(f.absolutePath)
        } catch (e: IOException) {
            Log.d("ErrorCompress", e.toString())
            null
        }
    }


    private fun exifToDegrees(exifOrientation: Int): Int {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270
        }
        return 0
    }
}