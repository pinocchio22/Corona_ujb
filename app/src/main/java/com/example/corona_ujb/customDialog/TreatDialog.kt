package com.example.corona_ujb.customDialog


import android.app.Dialog
import android.os.Bundle

import android.view.View
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.treat_dialog.view.*

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */

class TreatDialog(v: View, img: Int): DialogFragment() {
    private val v = v
    private val img = img

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val maindlgBuilder: androidx.appcompat.app.AlertDialog.Builder =
            androidx.appcompat.app.AlertDialog.Builder(    // 메인 다이얼로그
                context!!
            )
        maindlgBuilder.setView(v)
        val dlg = maindlgBuilder.create()
        v.treat_ok.setOnClickListener {
            dlg.cancel()
        }
        v.treat_img.setImageResource(img)

        return dlg
    }

}

