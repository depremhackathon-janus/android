package com.janus.deprem.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.telephony.SmsManager
import android.widget.Toast
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.janus.deprem.R
import com.janus.deprem.data.PersonInfo

fun Context.startActivityWithName(clazz: Class<out AppCompatActivity>, newTask: Boolean = false) {
    val intent = Intent(this, clazz)
    if (newTask) {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}

fun Context.bitmapDescriptorFromVector(vectorResId: Int): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(this, vectorResId)
    vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
    val bitmap =
        Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}

fun Context.startNavigation(latLng: LatLng): Boolean {
    val gmmIntentUri = Uri.parse("google.navigation:mode=d&q=${latLng.latitude},${latLng.longitude}")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    val mapAvailable = mapIntent.resolveActivity(packageManager) != null

    if (mapAvailable) {
        startActivity(mapIntent)
    } else {
        Toast.makeText(this,getString(R.string.error_no_maps_app_available), Toast.LENGTH_LONG).show()
    }
    return mapAvailable
}

fun Context.sendSMS(personInfo: PersonInfo) {
    try {
        val smsManager: SmsManager = SmsManager.getDefault()
        smsManager.sendTextMessage("+12029329036", null, personInfo.toJson().toString(), null, null)
        Toast.makeText(this, "Durumunuz Bildirildi", Toast.LENGTH_LONG).show()
    } catch (ex: Exception) {
        Toast.makeText(this, ex.message.toString(), Toast.LENGTH_LONG).show()
        ex.printStackTrace()
    }
}