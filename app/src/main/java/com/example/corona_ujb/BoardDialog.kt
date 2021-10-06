package com.example.corona_ujb

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.listboard_dialog.view.*

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */

class BoardDialog(v: View, vm: BoardViewModel): DialogFragment() {
    private val v = v
    private val vm = vm

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val maindlgBuilder: androidx.appcompat.app.AlertDialog.Builder =
            androidx.appcompat.app.AlertDialog.Builder(    // 메인 다이얼로그
                context!!
            )
        maindlgBuilder.setView(v)
        val dlg = maindlgBuilder.create()
        v.board_people_ok.setOnClickListener {
            dlg.cancel()
        }

        if(vm.checkGetPeople == 0 && !vm.peopleList.value.isNullOrEmpty())
            vm.checkGetPeople = 1
        else
            vm.getUjbNum()
        vm.init(v.board_RecycleView, vm.getAdapter())
        vm.peopleList.observe(this, Observer { list ->
            vm.getAdapter().addItem(list!!)
        })

        return dlg
    }

}
