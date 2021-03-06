package com.example.corona_ujb

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */

class SplashActivity : AppCompatActivity() {

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(SharePreferences.prefs.getAlert("splash", 0) == 0) {
            FirebaseMessaging.getInstance().subscribeToTopic("alert")
                .addOnCompleteListener { task -> }
            SharePreferences.prefs.setAlert("splash", 1)
        }
        Thread.sleep(500)
        NetworkCheck()
    }

    fun NetworkIsValid(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = cm.activeNetwork ?: return false
            val actNw = cm.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            cm.run {
                cm.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }

    fun NetworkCheck() {
        if (NetworkIsValid(this)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val dlg: AlertDialog.Builder = AlertDialog.Builder(this)
            dlg.setTitle("??????") //??????
            dlg.setMessage("??? ?????? ????????? ????????? ???????????????.\n???????????? ????????? ??????????????????.") // ?????????
            dlg.setCancelable(false)
            dlg.setPositiveButton("?????????", DialogInterface.OnClickListener { dialog, which ->
                if (NetworkIsValid(this)) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    NetworkCheck()
                }
            })
            dlg.setNegativeButton("??????", DialogInterface.OnClickListener { dialog, which ->
                finish()
            })
            dlg.show()
        }
    }
}
