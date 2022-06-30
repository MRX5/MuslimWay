package com.example.muslimway.utils.extensions

import android.app.Activity
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

fun Fragment.showLongToast(msg:String){
    Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
}

fun Fragment.showShortToast(msg:String){
    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
}