package com.rysanek.currencyconverter.utils

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.rysanek.currencyconverter.R

fun AppCompatActivity.setUpSystemWindow(){
    val navBarColor = ContextCompat.getColor(this.applicationContext, R.color.navigation_bar_color)
    val statusBarColor = ContextCompat.getColor(this.applicationContext, R.color.status_bar_color)
    
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
        this.window.setDecorFitsSystemWindows(false)
        this.window.navigationBarColor = navBarColor
        this.window.statusBarColor = statusBarColor
    }
}

fun Fragment.showSnackBar(message: String){
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        .setAction("OK"){}
        .show()
}

fun Activity.hideKeyboard(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.hide(WindowInsets.Type.ime())
    }
}