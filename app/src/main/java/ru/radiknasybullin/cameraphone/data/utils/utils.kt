package ru.radiknasybullin.cameraphone.data.utils

import android.app.Activity
import android.widget.Toast

fun Activity.showLongToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
fun Activity.showShortToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}