package com.example.corona_ujb.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.corona_ujb.BoardViewModel
import com.example.corona_ujb.customDialog.BoardDialog
import com.example.corona_ujb.R
import com.example.corona_ujb.SharePreferences
import com.google.firebase.messaging.FirebaseMessaging
import com.example.corona_ujb.databinding.FragmentBoardBinding
import kotlin.concurrent.timer

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */

class FragmentBoard : Fragment() {

    private lateinit var vm: BoardViewModel
    private var mLastClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBoardBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_board, container, false)
        val uri = "http://www.busan.go.kr/corona19/index#travelhist".toUri()
        vm = ViewModelProvider(this).get(BoardViewModel::class.java)
        clickListener(binding)

        var clickCheck = vm.clickPeople.value
        vm.clickPeople.observe(viewLifecycleOwner, androidx.lifecycle.Observer { clickPeople ->
            if (clickCheck != clickPeople) {
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                clickCheck = -1
            }
        })

        timer(period = 1000) {
            vm.getNowTime()
        }
        binding.boardViewModel = vm
        binding.lifecycleOwner = this
        return binding.root
    }

    fun clickListener(binding: FragmentBoardBinding) {
        binding.boardRefrash.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime > 3000) {
                vm.getBoard()
                mLastClickTime = SystemClock.elapsedRealtime()
            } else {
                Toast.makeText(context, "???????????? ???????????? ????????????. ????????? ????????? ?????????.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.boardPeople.setOnClickListener {
            val boardDlg: View = layoutInflater.inflate(R.layout.listboard_dialog, null)
            val dlg = BoardDialog(boardDlg, vm)
            dlg.show(childFragmentManager, "boardDlg")
        }

        binding.alertFcm.setOnClickListener {
            alertDialog()
        }
    }

    fun alertDialog() {
        Toast.makeText(context, "???????????? ????????? ????????? ?????? ?????? ?????? ????????? ???????????????", Toast.LENGTH_SHORT).show()
        val selectitem = arrayOf<String>("?????? ?????? ??????", "?????? ?????? ??????")
        var select = SharePreferences.prefs.getAlert("alert", 0)
        val dlg: AlertDialog.Builder = AlertDialog.Builder(context)
        dlg.setTitle("?????????19 ?????? ??????")
        dlg.setSingleChoiceItems(selectitem, select) { dialog, i ->
            when (i) {
                0 -> select = 0
                1 -> select = 1
            }
        }
        dlg.setIcon(R.mipmap.ic_launcher_round)
        dlg.setPositiveButton("??????") { _, _ ->
            SharePreferences.prefs.setAlert("alert", select)
            if (select == 0) {
                FirebaseMessaging.getInstance().subscribeToTopic("alert")
                    .addOnCompleteListener { task ->
                    }
                Toast.makeText(context, "?????? ????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("alert")
                    .addOnCompleteListener { task ->
                    }
                Toast.makeText(context, "?????? ????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
            }

        }
        dlg.setNegativeButton("??????") { _, _ ->
        }
        dlg.show()
    }
}